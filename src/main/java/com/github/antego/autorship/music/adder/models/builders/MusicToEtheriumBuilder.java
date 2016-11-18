package com.github.antego.autorship.music.adder.models.builders;

import com.github.antego.autorship.music.adder.models.Account;
import com.github.antego.autorship.music.adder.models.MusicToEtheriumData;

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
        Account account = new Account();
        account.setAddress(address);
        account.setPrivateKey(privateKey);

        MusicToEtheriumData data = new MusicToEtheriumData();
        data.setHash(hash);
        data.setAccount(account);

        return data;
    }
}
