package com.github.antego.autorship.music.adder.models;

public class MusicToEtheriumData {

    private String hash;
    private Account account;

    public MusicToEtheriumData() {
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
