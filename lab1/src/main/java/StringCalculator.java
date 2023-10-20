public class StringCalculator {
    public int add(String numbers) {
        int sum = 0;
        String[] numbersArray = numbers.split(",");
        for (String number : numbersArray) {
            sum += Integer.parseInt(number);
        }

        return sum;
    }

    public static void main(String[] args) {
        String numbers = "1,2,3";
        System.out.println("Answer is :" + new StringCalculator().add(numbers));
    }
}