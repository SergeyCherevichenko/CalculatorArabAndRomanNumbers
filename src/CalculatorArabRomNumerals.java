import java.util.Arrays;
import java.util.Scanner;

import static java.util.Arrays.*;


public class CalculatorArabRomNumerals {

    Scanner scan;
    String[] romanNumerals = {"", "I", "II", "III", "IV","V", "VI", "VII", "VIII", "IX", "X"};
    String[] op = {"+", "-", "/", "*"};

    StringBuilder stringBuilder;

    String[] input;

    public CalculatorArabRomNumerals() {
        this.scan = new Scanner(System.in);
        this.input = new String[3];
        stringBuilder = new StringBuilder();

    }
    // Метод для работы по ТЗ(добавил для условия ТЗ и работы метода в классе Main)
    // Проверка принятой строки
    String [] input1(String userInput) throws Exception{
        System.out.println("Это калькулятор версии 1.0. Калькулятор может складывать, вычитать, делить и " +
                "умножать римские и арабские цифры (отдельно для каждой системы счисления) [1 -10] или [I - X]" +
                "\nВведите свое выражение в формате" +
                " число действие число (например 3 - 4 или X + I):  ");

        boolean str = false;
        userInput = userInput.replaceAll("[-+*/]", " $0 ").replace("  ", " ").trim();
        String[] user = userInput.split(" ");
        System.out.println(userInput);
        if (user.length < 3) throw new Exception("Строка не является математической операцией");
        if(user.length>3) throw new Exception("Формат математической операции не удовлетворяет заданию!");
        for (int i = 0; i < op.length; i++) {
            if (user[1].equals(op[i])) str = true;
        }
        if (!str) throw new Exception("Не верно записана операция");

        boolean roman1 = false;
        boolean roman2 = false;
        for (int i = 0; i < romanNumerals.length; i++) {
            if (user[0].equals(romanNumerals[i])) roman1 = true;
            if (user[2].equals(romanNumerals[i])) roman2 = true;
        }
        if (!roman1 && roman2 || roman1 && !roman2)
            throw new Exception("Используются одновременно две системы счисления!");

        if (!roman1 && !roman2) {
            try {
                int a = Integer.parseInt(user[0]);
                int b = Integer.parseInt(user[2]);
                if (a < 1 || a > 10 || b < 1 || b > 10) throw new Exception("Числа не в диапазоне от [1,10]");
            } catch (NumberFormatException e) {
                System.out.println("Не верный формат чисел");
            }

        }
        return user;
    }


    // Метод для работы с консолью(сканером)
    // Проверка введенной строки
    String[] input() throws Exception{
        System.out.println("Это калькулятор версии 1.0. Калькулятор может складывать, вычитать, делить и " +
                "умножать римские и арабские цифры(отдельно для каждой системы счисления)  [1 -10] или [I - X]" +
                "\nВведите свое выражение в формате" +
                "число действие число (например 3 - 4 или X + I):  ");
        String userInput;
        boolean str = false;
        userInput = scan.nextLine();
        userInput = userInput.replaceAll("[-+*/]", " $0 ").replace("  ", " ").trim();
        String[] user = userInput.split(" ");
        System.out.println(userInput);

        if (user.length < 3) throw new Exception("Строка не является математической операцией");
        if(user.length>3) throw new Exception("Формат математической операции не удовлетворяет заданию!");
        for (int i = 0; i < op.length; i++) {
            if (user[1].equals(op[i])) str = true;
        }
        if (!str) throw new Exception("Не верно записана операция");

        boolean roman1 = false;
        boolean roman2 = false;
        for (int i = 0; i < romanNumerals.length; i++) {
            if (user[0].equals(romanNumerals[i])) roman1 = true;
            if (user[2].equals(romanNumerals[i])) roman2 = true;
        }
        if (!roman1 && roman2 || roman1 && !roman2)
            throw new Exception("Используются одновременно две системы счисления!");

        if (!roman1 && !roman2) {
            try {
                int a = Integer.parseInt(user[0]);
                int b = Integer.parseInt(user[2]);
                if (a < 1 || a > 10 || b < 1 || b > 10) throw new Exception("Числа не в диапозоне от [1,10]");
            } catch (NumberFormatException e) {
                System.out.println("Не верный формат чисел");
            }

        }
        return user;
    }
    // Метод калькулятор
    String calc(String[] exp) throws Exception {
        try {
            int a = Integer.parseInt(exp[0]);
            int b = Integer.parseInt(exp[2]);
            char c = exp[1].charAt(0);
            return calcArab(a, b, c);
        } catch (Exception e) {

            return calcRom(exp[0], exp[2], exp[1].charAt(0));
        }

    }
    // Калькулятор для работы с арабскими цифрами
    String calcArab(int a, int b, char c) {
        int res = 0;
        if (c == '+') {
            res = a + b;
        } else if (c == '-') {
            res = a - b;
        } else if (c == '*') {
            res = a * b;
        } else {
            res = a / b;
        }
        return String.valueOf(res);

    }
    // Калькулятор для работы с римскими цифрами, есть не много отличия в реализации с арабскими
    // (иначе можно было бы использовать только метод с арабскими и просто потом передать в конвертер
    String calcRom(String rom1, String rom2, char c) throws Exception{
        int a = 0;
        int b = 0;
        int res = 0;
        for (int i = 0; i < romanNumerals.length; i++) {
            if (rom1.equals(romanNumerals[i]))a = i;
            if (rom2.equals(romanNumerals[i]))  b = i;
        }
        if (c == '+') {
            res = a + b;
        } else if (c == '*') {
            res = a * b;
        } else if (c == '/') {
            if (a < b) throw new Exception("Римские цифры не могут быть = 0");
            else res = a / b;
        } else {
            if (a < b || a == b) throw new Exception ("В римской системе нет отрицательных чисел!");
            else res = a / b;
        }
        return convertFromArabToRom(res);
    }

    // Конвертер. По ТЗ римское число не может быть больше 100 в этом калькуляторе поэтому реализовал так
    String convertFromArabToRom(int res) {
        if (res == 100) stringBuilder.append("C");
        if(res > 11 && res < 100){
                int res1 = res / 10;
                if (res1 == 5) stringBuilder.append("L");
                else if (res1 == 4) stringBuilder.append("XL");
                else if (res1 == 9) stringBuilder.append("XC");
                else if (res1 < 4) {
                    for (int i = 0; i < res1; i++) {
                        stringBuilder.append("X");
                        }
                    } else {
                        stringBuilder.append("L");
                        for (int i = 5; i < res1; i++){
                            stringBuilder.append("X");
                        }
                    }
                int res1010 = res % 10;
                for (int i = 0; i < romanNumerals.length; i++) {
                    if (res1010 == i) stringBuilder.append(romanNumerals[i]);
                }
            }
        if (res < 11){
            for (int i = 0; i < romanNumerals.length; i++) {
                if (res == i) stringBuilder.append(romanNumerals[i]);
            }
        }
        return stringBuilder.toString();
    }
}








