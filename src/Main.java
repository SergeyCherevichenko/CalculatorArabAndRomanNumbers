import java.util.Arrays;

public class Main {

    // Метод по ТЗ
    public static String calc(String input) throws Exception {

        CalculatorArabRomNumerals calculatorArabRomNumerals = new CalculatorArabRomNumerals();
        return calculatorArabRomNumerals.calc(calculatorArabRomNumerals.input1(input));
    }
        public static void main(String[] args) throws Exception{
        while (true) {
            CalculatorArabRomNumerals calculatorArabRomNumerals = new CalculatorArabRomNumerals();
            System.out.println(calculatorArabRomNumerals.calc(calculatorArabRomNumerals.input()));
        }
        //  Или mожно использовать этот метод по ТЗ
        //    System.out.println(calc("3+2"));

        }
}