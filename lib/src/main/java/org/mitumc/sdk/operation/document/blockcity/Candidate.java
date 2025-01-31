package org.mitumc.sdk.operation.document.blockcity;

import java.util.HashMap;

import org.mitumc.sdk.Constant;
import org.mitumc.sdk.exception.NumberRangeException;
import org.mitumc.sdk.interfaces.BytesConvertible;
import org.mitumc.sdk.interfaces.HashMapConvertible;
import org.mitumc.sdk.key.Address;
import org.mitumc.sdk.util.BigInt;
import org.mitumc.sdk.util.Hint;
import org.mitumc.sdk.util.Util;

public class Candidate implements BytesConvertible, HashMapConvertible {
    private Hint hint;
    private Address address;
    private String nickname;
    private String manifest;
    private BigInt count;

    Candidate(String address, String nickname, String manifest, int count) {
        assertManifest(manifest);
        this.hint = Hint.get(Constant.MBC_VOTING_CANDIDATE);
        this.address = Address.get(address);
        this.nickname = nickname;
        this.manifest = manifest;
        this.count = BigInt.fromInt(count);
    }

    private static void assertManifest(String manifest) {
        if (manifest.length() > 100) {
            throw new NumberRangeException(Util.errMsg(
                    "manifest length is too long - now, manifest(" + manifest.length() + ") > 100", Util.getName()));
        }
    }

    @Override
    public byte[] toBytes() {
        byte[] baddress = this.address.toBytes();
        byte[] bnickname = this.nickname.getBytes();
        byte[] bmanifest = this.manifest.getBytes();
        byte[] bcount = this.count.toBytes();
        return Util.concatByteArray(baddress, bnickname, bmanifest, bcount);
    }

    @Override
    public HashMap<String, Object> toDict() {
        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("_hint", this.hint.getHint());
        hashMap.put("address", this.address.getAddress());
        hashMap.put("nickname", this.nickname);
        hashMap.put("manifest", this.manifest);
        hashMap.put("count", Integer.parseInt(this.count.getValue()));

        return hashMap;
    }
}
