package org.example.stream;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Метод toMap () является статическим методом класса Collectors,
 * который возвращает Collector, который накапливает элементы в Map,
 * ключи и значения которых являются результатом применения предоставленных
 * функций отображения к элементам ввода.
 * !Внимание! Ключи являются уникальными, и если в любом случае ключи дублируются,
 * то при выполнении операции сбора создается исключение IllegalStateException.
 *
 * Существуют 3 перегруженных toMap метода
 * 1) toMap(Function keyMapper, Function valueMapper)
 * 2)toMap(Function keyMapper, Function valueMapper, BinaryOperator mergeFunction)
 * 3)toMap(Function keyMapper, Function valueMapper, BinaryOperator mergeFunction, Supplier mapSupplier)
 *
 * keyMapper — функция для ключей
 * valueMapper — функция для значений
 * mergeFunction — необходимо, если есть одинаковые ключи, то как отображать значения
 * mapSupplier — функция, которая возвращает необходимую мапу(TreeMap/LinkedHashMap/HashMap и т.п.)
 *
 * Полезные ссылки:
 * http://espressocode.top/collectors-tomap-method-in-java-with-examples/
 * https://user12vv.wordpress.com/2019/05/07/java-8-%D1%80%D0%B0%D0%B7%D0%B1%D0%BE%D1%80-collectors-tomap/
 */

public class ToMap {

    public static void main(String[] args) {
        /**
         * Преобразование списка животных в мапу,
         * где ключ - классификация,
         * значение - карточка животного
         * toMap(Animal::getClassification, a -> a, (a, b) -> b)
         * Animal::getClassification - ключ
         * a -> a, (a, b) -> b - значение
         * a -> a - вернуть карточку Animal animals
         * (a, b) -> b - функция - условие отбора, для исключение дублирования карточек,
         * аналогично return a.equals(b) ? a : b;
         * где a и b - карточка Animal animals
         */
        List<Animal> animals = Main.getAnimals();
        Map<Classification, Animal> animalMap = animals.stream()
                .collect(Collectors.toMap(Animal::getClassification, a -> a, (a, b) -> b));
        /**
         * Вывод мапы на печать с помощью .forEach()
         */
        animalMap.forEach(((classification, animals1) -> {
            System.out.println(classification);
            System.out.println(animals1);
        }));

    }
}
