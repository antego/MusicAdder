package com.github.antego.autorship.music.adder.services.impl;

import com.github.antego.autorship.music.adder.models.exceptions.MusicProcessingException;
import com.github.antego.autorship.music.adder.services.FileToHash;
import com.google.common.hash.Hashing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service("music-service")
public class MusicFileToHashImpl implements FileToHash {

    private static final Logger log = LoggerFactory.getLogger(MusicFileToHashImpl.class);

    @Override
    public String getHash(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            String hash = Hashing.sha256()
                    .hashBytes(bytes)
                    .toString();
            log.info("getHash from file '{}' -> done", file.getName());
            return hash;
        } catch (IOException e) {
            log.error("getHash from file '{}'-> error", file.getName(), e);
            throw new MusicProcessingException(e.getMessage());
        }
    }
}
