package io.github.socraticphoenix.randores.mod.definition;

import io.github.socraticphoenix.randores.api.Randores;

import java.awt.Color;
import java.util.Random;

public class RandoresNameGenerator {

    private static final String[] POST_DICTIONARY = {"ite", "ium", "anite", "enite", "agnum", "onite", "onium", "on", "inium", "ide", "ine", "alt", "el", "er", "inc", "ymium", "ury", "uth", "ogen", "arbon", "eter", "ay"};
    private static final String[] VOWEL_DICTIONARY = {"a", "a", "a", "e", "e", "e", "e", "i", "i", "u", "u", "o", "o", "y"};
    private static final String[] CONSONANT_DICTIONARY = {"w", "r", "t", "p", "s", "d", "f", "x", "g", "h", "j", "k", "l", "z", "c", "v", "b", "n", "m", "sh", "ch", "ph", "th", "qu"};


    public static String name(long seed) {
        return name(new Random(seed));
    }

    private static String name(Random random) {
        String res = "";

        if (random.nextInt(30) == 0) {
            res += VOWEL_DICTIONARY[random.nextInt(VOWEL_DICTIONARY.length)];
        }

        int consLen = random.nextInt(1) + 2;
        for (int i = 0; i < consLen; i++) {
            res += CONSONANT_DICTIONARY[random.nextInt(CONSONANT_DICTIONARY.length)];
            if (i < consLen - 1) {
                res += VOWEL_DICTIONARY[random.nextInt(VOWEL_DICTIONARY.length)];
                if (random.nextInt(10) == 0) {
                    res += VOWEL_DICTIONARY[random.nextInt(VOWEL_DICTIONARY.length)];
                    if (random.nextInt(30) == 0) {
                        res += VOWEL_DICTIONARY[random.nextInt(VOWEL_DICTIONARY.length)];
                    }
                }
            }
        }

        res += POST_DICTIONARY[random.nextInt(POST_DICTIONARY.length)];
        res = Character.toUpperCase(res.charAt(0)) + res.substring(1);
        return res;
    }


}
