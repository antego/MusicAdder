package com.github.antego.autorship.music.adder.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.antego.autorship.music.adder.models.InputData;
import com.github.antego.autorship.music.adder.models.exceptions.FileDeserializableException;
import com.github.antego.autorship.music.adder.services.DeserializableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class KeyFileDeserializableService implements DeserializableService {

    private static final Logger log = LoggerFactory.getLogger(KeyFileDeserializableService.class);

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public InputData deserializable(MultipartFile file) {

        try {
            InputData inputData = mapper.readValue(file.getInputStream(), InputData.class);

            log.info("deserializable file '{}' -> done", file.getName());
            return inputData;
        } catch (Exception e) {
            log.error("deserializable file '{}'-> error", file.getName(), e);
            throw new FileDeserializableException(e.getMessage());
        }
    }
}
