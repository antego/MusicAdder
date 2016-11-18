package com.github.antego.autorship.music.adder.controllers;

import com.github.antego.autorship.music.adder.models.exceptions.IncorrectFileFormatException;
import com.github.antego.autorship.music.adder.models.InputData;
import com.github.antego.autorship.music.adder.models.MusicToEtheriumData;
import com.github.antego.autorship.music.adder.models.builders.MusicToEtheriumBuilder;
import com.github.antego.autorship.music.adder.services.FileToHash;
import com.github.antego.autorship.music.adder.services.RestClient;
import com.github.antego.autorship.music.adder.services.impl.KeyFileDeserializableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/adder/music/")
public class WorkWithMusicController {

    private static final Logger log = LoggerFactory.getLogger(WorkWithMusicController.class);

    private static final String MP3_END_FILE = ".mp3";
    private static final String JSON_END_FILE = ".json";

    @Value("${app.etherium.addpath}")
    private String addMusicUri;

    @Value("${app.etherium.addpath.type.request}")
    private String requestMethod;

    private final FileToHash musicFileToHash;

    private final RestClient<MusicToEtheriumData> client;

    private final KeyFileDeserializableService deserializableService;

    @Autowired
    public WorkWithMusicController(@Qualifier("music-service") FileToHash musicFileToHash,
                                   @Qualifier("etherium-service") RestClient<MusicToEtheriumData> client,
                                   KeyFileDeserializableService deserializableService) {
        this.musicFileToHash = musicFileToHash;
        this.client = client;
        this.deserializableService = deserializableService;
    }

    @PostMapping("/add-bad")
    public ResponseEntity uploadData(@RequestParam("file") MultipartFile file, @RequestParam("key-file") MultipartFile keyFile,
                                     Model model) {
        String fileName = keyFile.getOriginalFilename();
        if (!fileName.endsWith(JSON_END_FILE)) {
            log.error("uploadData -> wrong key-file format {}", fileName);
            throw new IncorrectFileFormatException("incorrect file format");
        }

        InputData data = deserializableService.deserializable(keyFile);

        return uploadData(file, data, model);
    }

    @PostMapping("/add-well")
    public ResponseEntity uploadData(@RequestParam("file") MultipartFile file, @RequestBody InputData data, Model model) {
        String fileName = file.getOriginalFilename();
        if (!fileName.endsWith(MP3_END_FILE)) {
            log.error("uploadData -> wrong file format {}", fileName);
            throw new IncorrectFileFormatException("incorrect file format");
        }

        String hash = musicFileToHash.getHash(file);
        MusicToEtheriumData toEtheriumData = MusicToEtheriumBuilder.getBuilder()
                .addHash(hash)
                .addAddress(data.getAddress())
                .addPrivateKey(data.getPrivateKey())
                .build();


        String status = client.request(HttpMethod.valueOf(requestMethod.toUpperCase()), addMusicUri, toEtheriumData);
        log.info("uploadData -> done. Result = '{}'", status);
        return ResponseEntity.ok(status);
    }
}
