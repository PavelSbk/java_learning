package org.example.regex;

import javax.swing.plaf.PanelUI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Pattern - класс содержащий регулярные выражения
 * Matches - класс использует паттерн для операций над текстом
 *
 * Применение:
 * - для поиска паттерна в тексте
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

    public static void main(String[] args) {
     String string = "Hello! I've just sent you a file to joe@gmail.com\n" +
             "so we can keep int touch. My e-mail is buba@yandex.ru";
     emailFinder(string);
     emailName(string);
    }
}
