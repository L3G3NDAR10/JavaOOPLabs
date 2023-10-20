public class StringCalculator {
    public int add(String numbers) {
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
        String numbers = "1,2,3,\n4";
        System.out.println("Answer is :" + new StringCalculator().add(numbers));
    }
}