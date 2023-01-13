package org.example.stream;

import java.util.Arrays;
import java.util.List;

public class MapMethod {

    public static void main(String[] args) {
        /**
         * map()
         * map(Function<? super T,? extends R> mapper)
         * Returns a stream consisting of the results of applying
         * the given function to the elements of this stream.
         */
        List<String> list = Arrays.asList("Ab", "Bcd", "Cdefg", "De");
        list.stream()
                .map(element -> element.length())
                .forEach(System.out::println);
        list.stream().forEach(System.out::println);
        /**
         * !Важно!
         * map() не изменяет коллекцию List<String> list
         * list.stream()
         * - создание stream() на коллекции List<String> list
         * .map(element -> element.length())
         * - с каждым element stream() сопоставляется его длина element.length()
         * и на выходе из map() получается преобразованный stream(),
         * состоящий из длин element.length() элементов коллекции List<String> list
         * "Ab" => 2
         * "Bcd" => 3
         * "Cdefg" => 5
         * "De" => 2
         * Т.е. map() возвращает поток данных, обработанный
         * согласно переданному в него выражению
         * Это происходит благодаря тому, что map() принимает в себя
         * @functionalInterface interface Function<T,R> (принял <String>, а вернул <Integer>)
         */
        int[] arr = {15, 9, 12, 3, 8, 1};
        Arrays.stream(arr)
                .map(e -> e % 3 == 0 ? e / 3 : e)
                .forEach(System.out::println);
        /**
         * Применение stream() к массиву примитивов
         * Arrays.stream(arr) -
         * - вызов потока на массиве int[] (класс Arrays имеет метод stream(int[] array))
         * .map(e -> e % 3 == 0 ? e / 3 : e)
         * - возвращаемый поток данных, обработанный с помощью лямбда-выражения
         * .forEach(System.out::println)
         * - построчный вывод каждого элемента
         */
    }
}
