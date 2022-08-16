package org.mitumc.sdk.util;

import java.util.regex.Pattern;

import org.mitumc.sdk.Constant;

public class RegExp {
    public static final String EXP_ADDRESS = Constant.MC_ADDRESS;
    public static final String EXP_PRIVATE_KEY = Constant.KEY_PRIVATE;
    public static final String EXP_PUBLIC_KEY = Constant.KEY_PUBLIC;
    public static final String EXP_USER_DATA = Constant.MBC_USER_DATA;
    public static final String EXP_LAND_DATA = Constant.MBC_LAND_DATA;
    public static final String EXP_VOTE_DATA = Constant.MBC_VOTE_DATA;
    public static final String EXP_HISTORY_DATA = Constant.MBC_HISTORY_DATA;
    public static final String EXP_BLOCKSIGN_DATA = Constant.MBS_DOCUMENT_DATA;

    public static void assertLongEnough(String target) throws Exception {
        if(target.length() < 3) {
            throw new Exception(Util.errMsg("target string is too short", Util.getName()));
        }
    }

    public static void assertAddress(String address) throws Exception {
        assertLongEnough(address);
        if(!address.substring(address.length() - 3).equals(EXP_ADDRESS)) {
            throw new Exception(Util.errMsg("invalid type suffix", Util.getName()));
        }
        if(!Pattern.matches("^[a-zA-Z0-9]*$", address.substring(0, address.length() - 3))) {
            throw new Exception(Util.errMsg("invalid format", Util.getName()));
        }
    }

    public static void assertKey(String key) throws Exception{
        assertLongEnough(key);
        String suffix = key.substring(key.length() - 3);
        if(!(suffix.equals(EXP_PRIVATE_KEY) || suffix.equals(EXP_PUBLIC_KEY))) {
            throw new Exception(Util.errMsg("invalid type suffix", Util.getName()));
        }
        if(!Pattern.matches("^[a-zA-Z0-9]*$", key.substring(0, key.length() - 3))) {
            throw new Exception(Util.errMsg("invalid format", Util.getName()));
        }
    }

    public static void assertPrivateKey(String key) throws Exception {
        assertLongEnough(key);
        if(!key.substring(key.length() - 3).equals(EXP_PRIVATE_KEY)) {
            throw new Exception(Util.errMsg("invalid type suffix", Util.getName()));
        }
        if(!Pattern.matches("^[a-zA-Z0-9]*$", key.substring(0, key.length() - 3))) {
            throw new Exception(Util.errMsg("invalid format", Util.getName()));
        }
    }

    public static void assertPublicKey(String key) throws Exception {
        assertLongEnough(key);
        if(!key.substring(key.length() - 3).equals(EXP_PUBLIC_KEY)) {
            throw new Exception(Util.errMsg("invalid type suffix", Util.getName()));
        }
        if(!Pattern.matches("^[a-zA-Z0-9]*$", key.substring(0, key.length() - 3))) {
            throw new Exception(Util.errMsg("invalid format", Util.getName()));
        }
    }

    public static void assertBlockSignDocumentId(String id) throws Exception {
        assertLongEnough(id);
        String suffix = id.substring(id.length() - 3);
        if(!suffix.equals(EXP_BLOCKSIGN_DATA)) {
            throw new Exception(Util.errMsg("invalid type suffix", Util.getName()));
        }
    }

    public static void assertBlockCityDocumentId(String id) throws Exception {
        assertLongEnough(id);
        String suffix = id.substring(id.length() - 3);
        if(!(suffix.equals(EXP_USER_DATA) || suffix.equals(EXP_LAND_DATA) || suffix.equals(EXP_VOTE_DATA) || suffix.equals(EXP_HISTORY_DATA))) {
            throw new Exception(Util.errMsg("invalid type suffix", Util.getName()));
        }
    }
    
    public static void assertUserData(String id) throws Exception {
        assertLongEnough(id);
        if(!id.substring(id.length() - 3).equals(EXP_USER_DATA)) {
            throw new Exception(Util.errMsg("invalid type suffix", Util.getName()));
        }
    }

    public static void assertLandData(String id) throws Exception {
        assertLongEnough(id);
        if(!id.substring(id.length() - 3).equals(EXP_LAND_DATA)) {
            throw new Exception(Util.errMsg("invalid type suffix", Util.getName()));
        }
    }

    public static void assertVoteData(String id) throws Exception {
        assertLongEnough(id);
        if(!id.substring(id.length() - 3).equals(EXP_VOTE_DATA)) {
            throw new Exception(Util.errMsg("invalid type suffix", Util.getName()));
        }
    }

    public static void assertHistoryData(String id) throws Exception {
        assertLongEnough(id);
        if(!id.substring(id.length() - 3).equals(EXP_HISTORY_DATA)) {
            throw new Exception(Util.errMsg("invalid type suffix", Util.getName()));
        }
    }

    public static void assertNFTID(String id) throws Exception {
        int dash = id.indexOf("-");
        if(dash < 0) {
            throw new Exception(Util.errMsg("no divider(-) in target string", Util.getName()));
        }
        
        assertLongEnough(id.substring(0, dash));
        try {
            Integer.parseInt(id.substring(dash + 1));
        } catch(Exception e) {
            throw new Exception(Util.errMsg("collection idx is not integer", Util.getName()));
        }
    }
}