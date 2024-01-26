package com.example.wordservice.controller;

import com.example.wordservice.model.WordData;
import com.example.wordservice.service.WordCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/word")
public class WordController {

    private final WordCounter wordCounter;

    @Autowired
    public WordController(WordCounter wordCounter) {
        this.wordCounter = wordCounter;
    }

    @PostMapping(value = "/add", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> addWords(@RequestBody WordData wordData) {

        System.out.printf("Adding words : %s%n", wordData.getWordList());

        wordCounter.addWords(wordData);
        return new ResponseEntity<>(String.format("Added words: %s", wordData.getWordList()), HttpStatus.OK);
    }

    @GetMapping(value = "/count")
    public ResponseEntity<String> countWord(@RequestParam String word) {
        Integer wordCount = wordCounter.countWords(word);
        return new ResponseEntity<>(String.format("There are currently %s of the word {%s}", wordCount, word), HttpStatus.OK);
    }
}
