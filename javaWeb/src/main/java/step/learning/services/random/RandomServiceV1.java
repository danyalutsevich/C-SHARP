package step.learning.services.random;

import java.util.Random;

public class RandomServiceV1 implements RandomService {

    private final Random random;

    public RandomServiceV1() {
        this.random = new Random();
    }

    private final char[] HEX_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};


    @Override
    public void seed(String iv) {
        random.setSeed(iv.length());
    }

    @Override
    public String randomHex(int length) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int)(random.nextDouble()*1000)%HEX_CHARS.length;
            sb.append(HEX_CHARS[index]);
        }
        return sb.toString();
    }

}
