package com.github.antego.autorship.music.adder.services;

import com.github.antego.autorship.music.adder.models.InputData;
import org.springframework.web.multipart.MultipartFile;

public interface DeserializableService {

    InputData deserializable(MultipartFile file);
}
