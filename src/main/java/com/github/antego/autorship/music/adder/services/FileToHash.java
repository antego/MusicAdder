package com.github.antego.autorship.music.adder.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileToHash {

    String getHash(MultipartFile file);
}
