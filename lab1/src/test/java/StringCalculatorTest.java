import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringCalculatorTest {

    @Test
    void if_empty_will_return_zero() {
        int expected = 0;
        int actual = new StringCalculator().add("");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void if_number_will_return_number() {
        int expected = 2;
        int actual = new StringCalculator().add("2");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void if_numbers_will_return_sum_2_numbers() {
        int expected = 3;
        int actual = new StringCalculator().add("1,2");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void if_numbers_will_return_sum_several_numbers() {
        int expected = 15;
        int actual = new StringCalculator().add("1,2,3,4,5");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void slash_n_can_be_delimiter() {
        int expected = 6;
        int actual = new StringCalculator().add("1\n2,3");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void custom_delimiter_set_by_user() {
        int expected = 6;
        int actual = new StringCalculator().add("//;\n1\n2;3");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void custom_delimiter_without_slash() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new StringCalculator().add("1;2,3\n4"));
    }

    @Test
    void multiple_delimiters_together_throws_exception() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new StringCalculator().add("1\n,2,3"));
    }

    @Test
    void negative_numbers_throws_exception() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new StringCalculator().add("1,-1,2,-2"));
    }

    @Test
    void numbers_greater_than_1000_are_ignored() {
        int expected = 1001;
        int actual = new StringCalculator().add("1,1000,1001,1002,1003");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void delimiter_can_be_of_any_length() {
        int expected = 6;
        int actual = new StringCalculator().add("//[***]\n1***2***3");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void can_pass_multiple_delimiters() {
        int expected = 6;
        int actual = new StringCalculator().add("//[*][%]\n1*2%3");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void can_handle_multiple_delimiters_longer_than_one_character() {
        int expected = 6;
        int actual = new StringCalculator().add("//[**][%%]\n1**2%%3");

        Assertions.assertEquals(expected, actual);
    }
}