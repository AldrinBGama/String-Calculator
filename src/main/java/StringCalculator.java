import java.util.List;
import java.util.Scanner;
import java.util.Arrays;
import java.util.stream.IntStream;


public class StringCalculator {
    static int add(String string) {
        String delimiter = ",|\n";

        String[] arr = string.split("\n", 2);
        boolean startsWith = string.startsWith("//");

        if (string == null || string.isEmpty())
            return 0;
        if (startsWith) {
            delimiter = arr[0].substring(2);
            string = arr[1];
        }

        List<String> num = Arrays.asList(string.split(delimiter));

        try {
            if (getIntStream(num).anyMatch(n -> n < 0))
                throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: negatives not allowed");
        }

        return getIntStream(num).sum();
    }

    private static IntStream getIntStream(List<String> num) {
        return num.stream().mapToInt(Integer::parseInt).map(n->n%1000);
    }

    public void inputs() {
        Scanner input = new Scanner(System.in);
        String val = input.nextLine();
        System.out.println(add(val));
    }
}
