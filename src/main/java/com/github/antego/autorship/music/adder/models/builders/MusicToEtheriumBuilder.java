package com.github.antego.autorship.music.adder.models.builders;

import com.github.antego.autorship.music.adder.models.MusicToEtheriumData;

import java.math.BigInteger;

public class MusicToEtheriumBuilder {

    private String hash;
    private String address;
    private String privateKey;

    private MusicToEtheriumBuilder() {
    }

    public static MusicToEtheriumBuilder getBuilder() {
        return new MusicToEtheriumBuilder();
    }

    public MusicToEtheriumBuilder addHash(String hash) {
        this.hash = hash;
        return this;
    }

    public MusicToEtheriumBuilder addAddress(String address) {
        this.address = address;
        return this;
    }

    public MusicToEtheriumBuilder addPrivateKey(String privateKey) {
        this.privateKey = privateKey;
        return this;
    }

    public MusicToEtheriumData build() {
        MusicToEtheriumData data = new MusicToEtheriumData();
        data.setHash(hash);
        data.setReceiverAddress(new BigInteger(address));
        data.setSenderKey(new BigInteger(privateKey));

        return data;
    }
}
