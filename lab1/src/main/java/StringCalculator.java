import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class StringCalculator {
    public int add(String numbers) {

        if (numbers.startsWith("//")) {
            int delimiterIndex = numbers.indexOf("\n");
            String delimiter = numbers.substring(2, delimiterIndex);
            numbers = numbers.substring(delimiterIndex + 1);

            if (delimiter.startsWith("[")) {
                delimiter = delimiter.substring(1, delimiter.length() - 1);
                String[] customDelimiter = delimiter.split("]\\[");

                Arrays.sort(customDelimiter, Collections.reverseOrder());

                for (int i = 0; i < customDelimiter.length; i++) {
                    customDelimiter[i] = Pattern.quote(customDelimiter[i]);
                }

                String allDelimiters = String.join("|", customDelimiter);
                numbers = numbers.replaceAll(allDelimiters, ",");
            } else {
                numbers = numbers.replaceAll(Pattern.quote(delimiter), ",");
            }
        }

        int sum = 0;
        String[] numbersArray = numbers.split("[,\n]");
        for (int i = 0; i < numbersArray.length - 1; i++) {
            if (numbersArray[i].isEmpty()) {
                throw new IllegalArgumentException("Multiple delimiters together are not allowed");
            }
        }
        List<Integer> negatives = new ArrayList<>();
        for (String num : numbersArray) {
            if (!num.trim().isEmpty()) {
                int n = Integer.parseInt(num);
                if (n < 0) {
                    negatives.add(n);
                } else if (n <= 1000) {
                    sum += n;
                }
            }
        }
        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("Negatives not allowed: " + negatives);
        }

        return sum;
    }

    public static void main(String[] args) {
        String numbers = "//[**][***][*]\n1,1**1*1***1\n1";
        System.out.println("Answer is :" + new StringCalculator().add(numbers));
    }
}