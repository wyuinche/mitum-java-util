package org.mitumc.sdk;

import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import org.mitumc.sdk.util.Util;

import shadow.com.google.gson.Gson;
import shadow.com.google.gson.GsonBuilder;
import shadow.com.google.gson.JsonObject;

public class JSONParser {

    public static void createJSON(JsonObject target, String fpName) {
        FileWriter writer;

        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer = new FileWriter(fpName);
            gson.toJson(target, writer);
        
            try {
                writer.flush();
                writer.close();
            } catch (Exception e) {
                Util.raiseError("Fail to Flush JSON file writer.");   
                return;
            }
        } catch (Exception e) {
            Util.log("Fail to create JSON file... :(");
            return;
        }
        Util.log("Success to generate JSON file. :D");
    }

    public static void createJSON(HashMap<String, Object> target, String fpName) {
        createJSON(new Gson().toJsonTree(target).getAsJsonObject(), fpName);
    }
}