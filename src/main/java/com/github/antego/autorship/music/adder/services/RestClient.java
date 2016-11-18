package com.github.antego.autorship.music.adder.services;

import org.springframework.http.HttpMethod;

public interface RestClient<T> {

    String REST_URI = "http://%s:%s";

    String request(HttpMethod method, String path, T data);
}
