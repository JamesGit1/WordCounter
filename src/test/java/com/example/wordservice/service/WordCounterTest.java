package com.example.wordservice.service;

import com.example.wordservice.model.WordData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootConfiguration
@ActiveProfiles("test")
class WordCounterTest {

    private static final ArrayList<String> WORD_TO_ADD = new ArrayList<>(List.of("flower"));
    private static final ArrayList<String> WORDS_TO_ADD = new ArrayList<>(Arrays.asList("flower", "flor", "blume"));
    private static final ArrayList<String> WORDS_TO_ADD_NON_ALPHABETIC = new ArrayList<>(Arrays.asList("flower", "flor", "fl$or", "blume"));

    @Test
    void ShouldAddWord() {
        WordCounter wordCounter = new WordCounter();
        WordData wordData = new WordData();

        wordData.setWordList(WORD_TO_ADD);
        wordCounter.addWords(wordData);

        Assertions.assertEquals(WORD_TO_ADD, wordCounter.words.get("flower"));
    }


    @Test
    void ShouldAddWords() {
        WordCounter wordCounter = new WordCounter();
        WordData wordData = new WordData();

        wordData.setWordList(WORDS_TO_ADD);
        wordCounter.addWords(wordData);

        Assertions.assertEquals(WORDS_TO_ADD, wordCounter.words.get("flower"));
    }

    @Test
    void ShouldOnlyAddWordsWithAlphabeticCharacters() {
        WordCounter wordCounter = new WordCounter();
        WordData wordData = new WordData();

        wordData.setWordList(WORDS_TO_ADD_NON_ALPHABETIC);
        wordCounter.addWords(wordData);

        Assertions.assertEquals(WORDS_TO_ADD, wordCounter.words.get("flower"));
    }

    @Test
    void CountCorrectWithOneWord() {
        WordCounter wordCounter = new WordCounter();
        WordData wordData = new WordData();

        wordData.setWordList(WORD_TO_ADD);
        wordCounter.addWords(wordData);

        Assertions.assertEquals(1, wordCounter.countWords("flower"));

    }

    @Test
    void CountCorrectWithOneWordNonEnglish() {
        WordCounter wordCounter = new WordCounter();
        WordData wordData = new WordData();

        wordData.setWordList(WORD_TO_ADD);
        wordCounter.addWords(wordData);

        Assertions.assertEquals(1, wordCounter.countWords("flor"));

    }

    @Test
    void CountCorrectWithThreeWords() {
        WordCounter wordCounter = new WordCounter();
        WordData wordData = new WordData();

        wordData.setWordList(WORDS_TO_ADD);
        wordCounter.addWords(wordData);

        Assertions.assertEquals(3, wordCounter.countWords("flower"));

    }
}
