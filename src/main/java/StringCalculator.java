import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public static int add(String input) {
        StringBuilder deli = new StringBuilder();
        if (input.startsWith("//")) {
            deli.append("\n");
            Matcher m = Pattern.compile("(?<=//).*(?=\n)").matcher(input); //Finding delimiters between // and \n  then pass everything after \n
            while (m.find()) {
                deli.append("[").append(m.group()).append("]");
            }
            input = input.substring(input.indexOf("\n"));
        } else {
            deli.append(",\n");
        }
        return add(input, "[" + deli + "]");
    }

    static int add(String numbers, String deli) {

        String[] arr = numbers.split("[" + deli + "]");
        StringBuilder negativeNumbers = new StringBuilder(); //Tracking negative numbers to be displayed later
        int sum = 0;
        try {
            for (String numberAtIndex : arr) {

                if (!numberAtIndex.trim().isEmpty() && (Character.isDigit(numberAtIndex.charAt(0)) || numberAtIndex.charAt(0) == '-')) {

                    if (!Character.isDigit(numbers.charAt(numbers.length() - 1)))
                        throw new IllegalArgumentException("ERROR: invalid input");

                    if (Integer.parseInt(numberAtIndex.trim()) < 0) {

                        negativeNumbers.append(numberAtIndex.trim()).append(", ");
                    } else if (Integer.parseInt(numberAtIndex.trim()) < 1000) {  //ignores integers greater than or equal to 1000
                        sum += Integer.parseInt(numberAtIndex.trim());
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("ERROR: invalid input");
        }
        if (negativeNumbers.length() > 0) // throw going to be handled by assertThrows
            throw new IllegalArgumentException("\nERROR: negatives not allowed " + negativeNumbers.toString().substring(0, negativeNumbers.length() - 2));
        return sum;
    }

    public void inputs(){
        String[] split = "1,2|3.4$5".split("[,|.]");
        System.out.println(Arrays.toString(split));
        System.out.println ("add(\"\") = " +(add("")));
        System.out.println ("add(\"2,3\") = " +(add("2,3")));
        System.out.println ("add(\"1\\n2,3\") = " +(add("1\n2,3")));
        System.out.println ("add(\"//;\\n1;2\") = " +(add("//;\n1;2")));
        System.out.println ("add(\"//4\\n142\") = " +(add("//4\n142")));
        System.out.println(add("1,-1"));
        System.out.println ("add(\"//[:D][&]\\n:D2&3\") = " +(add("//[:D][&]\n:D2&3")));
        System.out.println("add(\"//[***]\\n1***2***3\") = "+(add("//[***]\n1***2***3")));
    }
}