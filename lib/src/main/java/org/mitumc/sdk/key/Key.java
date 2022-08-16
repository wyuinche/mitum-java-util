package org.mitumc.sdk.key;

import java.util.HashMap;

import org.mitumc.sdk.interfaces.BytesConvertible;
import org.mitumc.sdk.interfaces.HashMapConvertible;
import org.mitumc.sdk.Constant;
import org.mitumc.sdk.util.BigInt;
import org.mitumc.sdk.util.Hint;
import org.mitumc.sdk.util.Util;

public class Key implements BytesConvertible, HashMapConvertible {
    private Hint hint;
    private BaseKey key;
    private BigInt weight;

    private Key(String key, int weight) throws Exception {
        assertWeight(weight);
        this.hint = Hint.get(Constant.MC_KEY);
        this.key = BaseKey.get(key);
        this.weight = new BigInt(Integer.toString(weight));
    }

    private void assertWeight(int weight) throws Exception {
        if (weight < 1 || weight > 100) {
            throw new Exception(Util.errMsg("invalid weight - now " + weight, Util.getName()));
        }
    }

    public static Key get(String key, int weight) throws Exception {
        try {
            return new Key(key, weight);
        } catch (Exception e) {
            throw new Exception(
                    Util.linkErrMsgs(
                            Util.errMsg("failed to create key from key and weight", Util.getName()),
                            e.getMessage()));
        }
    }

    public int getWeight() {
        return Integer.parseInt(this.weight.getValue());
    }

    public String getKey() {
        return this.key.getKey();
    }

    @Deprecated
    public String getRawKey() {
        return this.getKeyWithoutType();
    }

    public String getKeyWithoutType() {
        return this.key.getKeyWithoutType();
    }

    @Override
    public byte[] toBytes() {
        byte[] bkey = this.key.toBytes();
        byte[] bweight = this.weight.toBytes();
        return Util.concatByteArray(bkey, bweight);
    }

    @Override
    public HashMap<String, Object> toDict() {
        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("_hint", this.hint.getHint());
        hashMap.put("weight", Integer.parseInt(this.weight.getValue()));
        hashMap.put("key", this.key.getKey());

        return hashMap;
    }
}