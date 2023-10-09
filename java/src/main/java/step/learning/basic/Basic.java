package step.learning.basic;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

public class Basic {

    public void run() {
        int[][] arr2D = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        for (int[] arr : arr2D) {
            for (int el : arr) {
                System.out.print(el);
            }
            System.out.println();
        }

    }


    public void dictionary() {

        Map<String, String> dict = Map.of(
                "apple", "1f3870be274f6c49b3e31a0c6728957f",
                "coffee", "24eb05d18318ac2db8b2b959315d10f2"
        );

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Word: ");
            String word = scanner.next();
            String translation = dict.get(word);
            System.out.println("Translation: " + translation!=null?translation:"[not found]");
        }


    }


}
