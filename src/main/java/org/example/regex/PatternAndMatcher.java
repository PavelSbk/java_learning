package org.example.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Pattern - класс содержащий регулярные выражения
 * Matches - класс использует паттерн для операций над текстом
 * <p>
 * Применение:
 * - для поиска паттерна в тексте
 *
 * regex в стримах
 * // Java9+
 *         var integers = Pattern.compile("-?\\d+").matcher(source)
 *                 .results()  // Stream<MatchResult>
 *                 .map(MatchResult::group) // Stream<String>
 *                 .map(Integer::valueOf)
 *                 .toArray(Integer[]::new);
 *
 * // Java8
 *         Integer[] integersJ8 = Pattern.compile(",")
 *                 .splitAsStream(source)
 *                 .map(String::trim)
 *                 .map(Integer::valueOf)
 *                 .toArray(Integer[]::new);
 */

public class PatternAndMatcher {

    public static void emailFinder(String string) {
        Pattern email = Pattern.compile("(\\w+)@(gmail|yandex)\\.(com|ru)");
        Matcher matcher = email.matcher(string);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    /**
     * regEx разбит на группы: (\w+)@(gmail|yandex)\.(com|ru)
     * (\w+) - 1я
     * (gmail|yandex) - 2я
     * (com|ru) - 3я
     * Этим можно воспользоваться для вывода необходимой части найденной подстроки
     */
    public static void emailName(String string) {
        Pattern email = Pattern.compile("(\\w+)@(gmail|yandex)\\.(com|ru)");
        Matcher matcher = email.matcher(string);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }
    }

    /**
     * Найти букву в переданной строке
     * выведет на экран номер позиции буквы в строке
     */
    public static void literalInString(String string, char literal) {
        Pattern pattern = Pattern.compile(String.valueOf(literal));
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            System.out.print(matcher.start() + " " + matcher.group() + " ");
        }
        System.out.println("");
    }
    /**
     * Найти слово в переданной строке
     * выведет на экран номер позиции слова в строке
     */
    public static void wordInString(String string, String word) {
        Pattern pattern = Pattern.compile(word); // компилирует паттерн
        Matcher matcher = pattern.matcher(string); // на паттерне вызывается matcher(string)
        while (matcher.find()) { // до тех пор, пока есть буквы в строке
            System.out.print(matcher.start() + " < " + matcher.group() + " > " + matcher.end() + "; ");
        }
    }

    /**
     * Найти regex в переданной строке
     * выведет на экран номер позиции слова в строке
     */
    public static void regexInString(String string, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            System.out.print(matcher.start()
                    + " < " + matcher.group()
                    + " > " + matcher.end()
                    + "; "
                    + System.lineSeparator());
        }
    }

    /**
     * Вернуть List<String> состоящий из строк содержащих только временя ошибки без номера
     * 200 10:56:01
     * 500 10:57:01
     * 400 10:58:01
     * 500 10:58:35
     * 300 10:59:01
     * 500 11:01:02
     * 200 11:02:02
     * 300 11:03:01
     */
    public static List<String> errorTime(String input) {
        List<String> rst = new ArrayList<>();
        Pattern p = Pattern.compile("[0-2][0-9]:[0-5][0-9]:[0-5][0-9]");
        Matcher m = p.matcher(input);
        while (m.find()) {
            System.out.print(m.start()
                    + " < " + m.group()
                    + " > " + m.end()
                    + "; "
                    + System.lineSeparator());
        }
        return rst;
    }

    /**
     * Найти в тексте дату и вывести на экран
     * dd/mm/yy
     */
    public static void findDate(String text) {
        Pattern p = Pattern.compile("\\d{2}[-./]\\d{2}[-./]\\d{2,4}");
        Matcher m = p.matcher(text);
        while (m.find()) {
            System.out.print(m.start()
                    + " < " + m.group()
                    + " > " + m.end()
                    + "; "
                    + System.lineSeparator());
        }
    }

    public static void main(String[] args) {
        String jack = "Jack is a boy.";
        String cat = "About cats and dogs and maybe about a catfish a little.";
        String string = "Hello 21.01.23 i've sent you a file to joe@gmail.com\n"
                +
                "so we can keep in touch. My e-mail is buba@yandex.ru 22-02-2023";
        String error = "200 10:56:01\n"
                + "500 10:57:01\n"
                + "400 10:58:01\n"
                + "500 10:58:35\n"
                + "300 10:59:01\n"
                + "500 11:01:02\n"
                + "200 11:02:02\n"
                + "300 11:03:01";
        findDate(string);
    }
}
