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
        System.out.println(add(""));
        System.out.println(add("1"));
        System.out.println(add("1,1"));
        System.out.println(add("1,2,3,4"));
        System.out.println(add("1\n2,3"));
//        System.out.println(add("//;\\n1;2"));
//        System.out.println(add("//4\\n142"));
    //    System.out.println(add("-1,-2,3,4"));
//        System.out.println(add("//;\n1000,1;2"));
//        System.out.println(add("//***\n1***2***3"));
    }
}
