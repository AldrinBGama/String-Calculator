import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String rerun;

        do {
            StringCalculator stringCalculator = new StringCalculator();
            System.out.println("Please enter numbers");
            stringCalculator.inputs();
            System.out.println("Please type 'yes' to run the program again or 'no' to quit.");
            rerun = input.nextLine();
        } while (rerun.equalsIgnoreCase("yes"));
    }
}
