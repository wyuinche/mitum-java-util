/* Copyright 2021 BitcoinJ
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.mitumc.sdk.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.bitcoinj.core.Base58;

public class Hash {
    private String msg;
    private byte[] target;
    private byte[] sha256Digest;
    private String sha256Hash;
    private byte[] sha3Digest;
    private String sha3Hash;

    public Hash() {
        this("");
    }

    public Hash(byte[] target) {
        setter(target);
    }

    public Hash(String msg) {
        setter(msg);
    }

    private void sha256() {
        try{
            MessageDigest hasher = MessageDigest.getInstance("SHA-256");
            this.sha256Digest = hasher.digest(this.target);
            this.sha256Hash = Base58.encode(this.sha256Digest);
        } catch(NoSuchAlgorithmException e) {
            Util.raiseError("No such algorithm; sha-256");
        }
    }

    private void sha3() {
        try {
            MessageDigest hasher = MessageDigest.getInstance("SHA3-256");
            this.sha3Digest = hasher.digest(this.target);
            this.sha3Hash = Base58.encode(this.sha3Digest);
        } catch(NoSuchAlgorithmException e) {
            Util.raiseError("No such algorithm; sha3-256");
        }
    }

    public void setter(String msg) {
        this.msg = msg;
        this.target = this.msg.getBytes(StandardCharsets.UTF_8);
        sha256();
        sha3();
    }

    public void setter(byte[] target) {
        this.target = target;
        this.msg = new String(this.target);
        sha256();
        sha3();
    }

    public String getMessage() {
        return this.msg;
    }

    public byte[] getTarget() {
        return this.target;
    }

    public byte[] getSha256Digest() {
        return this.sha256Digest;
    }

    public byte[] getSha3Digest() {
        return this.sha3Digest;
    }

    public String getSha256Hash() {
        return this.sha256Hash;
    }

    public String getSha3Hash() {
        return this.sha3Hash;
    }
}