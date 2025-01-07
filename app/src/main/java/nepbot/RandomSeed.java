package nepbot;

import java.util.Random;

public class RandomSeed{
    public static int RandomGenUsingSeed(int seed){
        Random random = new Random();
        int randomInt = random.nextInt(seed);
        return randomInt;
    }

    public static int RandomGenMissingSeed(){
        Random random = new Random();
        int randomInt = random.nextInt();
        return randomInt;
    }

    public static int RandomGenGivenValues(int lowSeed, int highSeed){
        Random random = new Random();
        int randomInt = random.nextInt(highSeed - lowSeed) + lowSeed;
        return randomInt;
    }
}
