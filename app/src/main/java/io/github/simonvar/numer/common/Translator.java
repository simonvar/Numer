package io.github.simonvar.numer.common;

/**
 * Created by semav on 04.09.2016.
 */
public class Translator {

    public static String Translate(String number, String systemFrom, String systemTo) {
        long fromSystem = Long.parseLong(systemFrom);
        String result;

        if (fromSystem == 10) {
            result = fromDecimal(number, systemTo);
        } else {
            result = fromDecimal(toDecimal(number, systemFrom), systemTo);
        }

        return result;
    }

    public static String Calculate(String number1, String number2, String system1, String system2, String resultSystem, int sign) {
        String result = "";

        long number1In10System = Long.parseLong(toDecimal(number1, system1));
        long number2In10System = Long.parseLong(toDecimal(number2, system2));

        long resultIn10System;
        String resultStringIn10;

        switch (sign) {
            case 1:
                resultIn10System = number1In10System + number2In10System;
                resultStringIn10 = Long.toString(resultIn10System);
                result = fromDecimal(resultStringIn10, resultSystem);
                break;
            case 2:
                resultIn10System = number1In10System - number2In10System;
                resultStringIn10 = Long.toString(resultIn10System);
                result = fromDecimal(resultStringIn10, resultSystem);
                break;
            case 3:
                resultIn10System = number1In10System * number2In10System;
                resultStringIn10 = Long.toString(resultIn10System);
                result = fromDecimal(resultStringIn10, resultSystem);
                break;
            case 4:
                resultIn10System = number1In10System / number2In10System;
                resultStringIn10 = Long.toString(resultIn10System);
                result = fromDecimal(resultStringIn10, resultSystem);
                break;

        }

        return result;
    }

    public static int CheckCalculator(String number1, String number2, String system1, String system2, String resultSystem) {
        if (!numberIsCorrect(number1))
            return 1;

        if (!numberIsCorrect(number2))
            return 1;

        //-----

        if (!systemIsCorrect(system1))
            return 2;

        if (!systemIsCorrect(system2))
            return 2;

        if (!systemIsCorrect(resultSystem))
            return 2;

        //-----

        if (!systemIsBigger2(system1) || !systemIsLower36(system1))
            return 3;

        if (!systemIsBigger2(system2) || !systemIsLower36(system2))
            return 3;

        if (!systemIsBigger2(resultSystem) || !systemIsLower36(resultSystem))
            return 3;

        //------
        if (!numberInSystemFrom(number1, system1))
            return 4;
        if (!numberInSystemFrom(number2, system2))
            return 4;


        return 0;
    }

    public static int Check(String number, String systemFrom, String systemTo) {
        if (!numberIsCorrect(number))
            return 1;

        if (!systemIsCorrect(systemFrom))
            return 2;

        if (!systemIsCorrect(systemTo))
            return 3;

        if (!systemIsBigger2(systemFrom) || !systemIsLower36(systemFrom))
            return 4;

        if (!systemIsBigger2(systemTo) || !systemIsLower36(systemTo))
            return 5;

        if (!numberInSystemFrom(number, systemFrom))
            return 6;


        return 0;
    }

    private static boolean numberIsCorrect(String number) {
        //Проверка на лишние символы в числе
        for (int i = 0; i < number.length(); i++) {
            if (!((number.charAt(i) >= '0' && number.charAt(i) <= '9') || (number.charAt(i) >= 'A' && number.charAt(i) <= 'Z'))) {
                return false;
            }
        }
        return true;
    }


    private static boolean systemIsCorrect(String system) {
        for (int i = 0; i < system.length(); i++) {
            if (system.charAt(i) == '.') {
                return false;
            }
        }
        return true;
    }


    private static boolean systemIsBigger2(String system) {
        long systemLong = Long.parseLong(system);
        return systemLong >= 2;
    }

    public static boolean systemIsLower36(String system) {
        long systemLong = Long.parseLong(system);
        return systemLong <= 36;
    }

    private static boolean numberInSystemFrom(String num, String fromSystem) {
        //Нахождение максимума в числе
        int max = 0;

        for (int i = 0; i < num.length(); i++) {
            int m;
            String ch = "";
            ch += num.charAt(i);
            if (num.charAt(i) >= 'A' && num.charAt(i) <= 'Z')
                m = (int) num.charAt(i) - 55;
            else
                m = Integer.parseInt(ch);

            if (m > max)
                max = m;
        }


        Long fromSystemLong = Long.parseLong(fromSystem);

        //Проверка на максимум, не превосходящий основание системы
        return max < fromSystemLong;
    }


    //Перевод в 10 систему счисления
    private static String toDecimal(String num, String fromSystem) {
        long result = 0L;
        String resultStr;
        int i;
        String ch = "";

        long n = Long.parseLong(fromSystem);

        for (i = 0; i < num.length(); i++) {
            ch += num.charAt(num.length() - 1 - i);
            if (num.charAt(num.length() - i - 1) >= 'A' && num.charAt(num.length() - i - 1) <= 'Z') {
                int mul = (int) num.charAt(num.length() - i - 1) - 55;
                result += Math.pow(n, i) * mul;
            } else {
                result += Math.pow(n, i) * Integer.parseInt(ch);
            }

            ch = "";
        }

        resultStr = String.valueOf(result);
        return resultStr;
    }

    //Перевод из 10 системы счисления
    private static String fromDecimal(String numStr, String toSystem) {
        StringBuilder resBuilder = new StringBuilder();
        long num = Long.parseLong(numStr);
        long b;
        long n = Long.parseLong(toSystem);

        while (num != 0) {
            b = num % n;
            if (b >= 10) {
                b += 55L;
                char ch = (char) b;
                resBuilder.append(ch);
            } else {
                resBuilder.append(b);
            }
            num /= n;
        }

        String res = resBuilder.toString();

        if (res.equals(""))
            res = "0";

        return new StringBuilder(res).reverse().toString();
    }

}
