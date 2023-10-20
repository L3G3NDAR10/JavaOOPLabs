import java.util.regex.Pattern;

public class StringCalculator {
    public int add(String numbers) {

        if (numbers.startsWith("//")) {
            int delimiterIndex = numbers.indexOf("\n");
            String delimiter = numbers.substring(2, delimiterIndex);
            numbers = numbers.substring(delimiterIndex + 1);
            numbers = numbers.replaceAll(Pattern.quote(delimiter), ",");
        }

        int sum = 0;
        String[] numbersArray = numbers.split("[,\n]");
        for (int i = 0; i < numbersArray.length - 1; i++) {
            if (numbersArray[i].isEmpty()) {
                throw new IllegalArgumentException("Multiple delimiters together are not allowed");
            }
        }
        for (String number : numbersArray) {
            sum += Integer.parseInt(number);
        }

        return sum;
    }

    public static void main(String[] args) {
        String numbers = "//;\n1,2,3;4";
        System.out.println("Answer is :" + new StringCalculator().add(numbers));
    }
}