package lt.vu.PSK1lab.services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Random;

@ApplicationScoped
public class FestivalNameGenerator implements Serializable {

    private static final int diffBetweenAtoZ = 25;
    private static final int charValueOfa = 97;
    private String lastGeneratedName = "";
    int length = 7;

    char[] vowels = {
            'a', 'e', 'i', 'o', 'u'
    };

    public String getName() {
        try {
            Thread.sleep(3000); // Simulate intensive work
        }
        catch (InterruptedException e) {
        }
        for (;;) {
            Random randomNumberGenerator = new Random(Calendar.getInstance()
                    .getTimeInMillis());

            char[] nameInCharArray = new char[length];

            for (int i = 0; i < length; i++) {
                if (positionIsOdd(i)) {
                    nameInCharArray[i] = getVowel(randomNumberGenerator);
                } else {
                    nameInCharArray[i] = getConsonant(randomNumberGenerator);
                }
            }
            nameInCharArray[0] = (char) Character
                    .toUpperCase(nameInCharArray[0]);

            String currentGeneratedName = new String(nameInCharArray);

            if (!currentGeneratedName.equals(lastGeneratedName)) {
                lastGeneratedName = currentGeneratedName;
                return currentGeneratedName;
            }
        }
    }

    private boolean positionIsOdd(int i) {
        return i % 2 == 0;
    }

    private char getConsonant(Random randomNumberGenerator) {
        for (;;) {
            char currentCharacter = (char) (randomNumberGenerator
                    .nextInt(diffBetweenAtoZ) + charValueOfa);
            if (currentCharacter == 'a' || currentCharacter == 'e'
                    || currentCharacter == 'i' || currentCharacter == 'o'
                    || currentCharacter == 'u')
                continue;
            else
                return currentCharacter;
        }
    }

    private char getVowel(Random randomNumberGenerator) {
        return vowels[randomNumberGenerator.nextInt(vowels.length)];
    }
}