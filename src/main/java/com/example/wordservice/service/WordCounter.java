package com.example.wordservice.service;

import com.example.wordservice.model.WordData;
import com.example.wordservice.util.Translator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WordCounter {
    //Externally provided translation library
    Translator translator = new Translator();

    // English word mapped to Translations
    Map<String, ArrayList<String>> words = new HashMap<>();

    public void addWords(WordData inputWords) {
        inputWords.getWordList().stream().filter(StringUtils::isAlphanumeric)
                .forEach(word -> {
                    String englishWord = translator.translate(word);
                    if (words.containsKey(englishWord)) {
                        ArrayList<String> translations = words.get(englishWord);
                        translations.add(word);
                        words.put(englishWord, translations);
                    } else {
                        words.put(word, new ArrayList<>(Collections.singletonList(word)));
                    }
                });

        System.out.printf("Updated words map : %s%n", words.toString());
    }

    public int countWords(String wordToCount) {
        return words.getOrDefault(translator.translate(wordToCount), new ArrayList<>()).size();
    }
}
