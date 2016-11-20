package com.github.antego.autorship.music.adder.models;

import java.math.BigInteger;

public class MusicToEtheriumData {

    String hash;
    BigInteger senderKey;
    BigInteger receiverAddress;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public BigInteger getSenderKey() {
        return senderKey;
    }

    public void setSenderKey(BigInteger senderKey) {
        this.senderKey = senderKey;
    }

    public BigInteger getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(BigInteger receiverAddress) {
        this.receiverAddress = receiverAddress;
    }
}
