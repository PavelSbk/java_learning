package org.example.regex;

import java.util.Arrays;
import java.util.regex.Matcher;

/**
 * https://regexlib.com/?AspxAutoDetectCookieSupport=1
 * Регулярные выражения (regular expressions, RegExp) – инструмент,
 * который помогает искать строки текста и проверять их на соответствие указанному шаблону, слову или символу.
 * В результате находим данные, которые отобраны согласно правилу, заложенному в выражении.
 *
 * Зачем нужны регулярные выражения?
 * - Сравнение с шаблоном:
 * Регулярные выражения отлично помогают определять, соответствует ли строка тому или иному формату – например,
 * телефонному номеру, адресу электронной почты или номеру кредитной карты.
 * - Замена:
 * При помощи регулярных выражений легко находить и заменять шаблоны в строке.
 * Так, выражение text.replace(/\s+/g, " ") заменяет все пробелы в text, например, " \n\t ", одним пробелом.
 * - Извлечение:
 * При помощи регулярных выражений легко извлекать из шаблона фрагменты информации.
 * Например, name.matches(/^(Mr|Ms|Mrs|Dr)\.?\s/i)[1] извлекает из строки обращение к человеку,
 * например, "Mr" из "Mr. Schropp".
 * - Портируемость:
 * Почти в любом распространенном языке программирования есть своя библиотека регулярных выражений.
 * Синтаксис в основном стандартизирован,
 * поэтому вам не придется переучиваться регулярным выражениям при переходе на новый язык.
 * - Код: Когда пишете код, можно пользоваться регулярными выражениями для поиска информации в файлах.
 * - Четкость и лаконичность:
 * С регулярными выражениями можно выполнять весьма нетривиальные операции, написав минимальный объем кода.
 *
 * Примеры:
 * \\d - одна цифра = [0-9]
 * \\w - одна буква = [a-zA-Z]
 * String a = "1";
 * a.matches("\\d")
 * >> true
 *
 * < + > - один или более
 * String a = "11111";
 * a.matches("\\d+")
 * >> true
 *
 * < * > - ноль или более
 *
 * < - > - для отрицательных чисел
 *
 * < ? > - 0 или 1 символов до
 * String a = "-4521";
 * a.matches("-?\\d*")
 * >> true
 * String a = "+4521";
 * a.matches("-?\\d*") >>> < -? > относится только к минусу и отсутствию минуса
 * >> false
 *
 * < (x|y|z) > - вероятные вещи разделённые | или - одно из
 * String a = "+4521";
 * a.matches("-?\\d*"); или "([-+])?\\d*"
 * >> true
 *
 * \\ - экранирование
 *
 * [a-zA-Z] - описание всех английских букв, аналогично (a|b|...|z|A|B|...|Z)
 * [0-9] - описание всех цифр, аналогично \\d
 * String a = "g4521";
 * a.matches("[a-zA-Z]\\d+");
 * >> true
 * String a = "g1a323w3d4521";
 * a.matches("[a-zA-Z\\d]+\\d+");
 * >> true
 *
 * < . > - любой символ
 *
 * < ^ > - отрицание, исключение ([^abc] - все символы, кроме abc)
 *
 * {2} - два символа до (\\d{2}) - ровно две цифры
 * {2, } - два или более символа (\\d{2, }) - от двух до бесконечности
 * {2, 4} - точное кол-во предыдущих символов (\\d{2, 4}) - от двух до четырёх
 *
 * < $ > - конец строки abc$ abc, endsinabc, 123abc, ...
 */

public class RegEx {
    /**
     * Вернёт true если адрес сайта валиден шаблону http://www...com/ru
     */
    public static boolean urlValidator(String url) {
        String temp = "http://www\\..+\\.(com|ru)";
        return url.matches(temp);
    }

    public static void splitStringByNumbers(String string) {
        String regex = "\\d+";
        System.out.println(Arrays.toString(string.split(regex)));
    }

    public static void splitStringByPoint(String string) {
        String regex = "\\.+";
        System.out.println(Arrays.toString(string.split(regex)));
    }

    public static void replacePointForSlash(String string) {
        System.out.println(string.replace(".", "/"));
    }

    /**
     * Для передачи в параметр regex - необходимо использовать replaceAll() вместо replace()
     */
    public static void replaceNumbers(String string, String replacement) {
        System.out.println(string.replaceAll("\\d+", replacement));
    }

    public static void replaceLiterals(String string, String replacement) {
        System.out.println(string.replaceAll("[a-zA-Z]+", replacement));
    }

    public static void main(String[] args) {
        String url = "http://www.google.com";
        System.out.println(urlValidator(url));
        String st = "Hello1212World899898!";
        splitStringByNumbers(st);
        splitStringByPoint(url);
        replacePointForSlash(url);
        replaceNumbers(st, " ");
        replaceLiterals(st, " ");
    }
}
