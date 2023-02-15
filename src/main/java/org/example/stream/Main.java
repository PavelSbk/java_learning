package org.example.stream;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    /**
     * Стримы позволяют указать - какой результат мы хотим получить, т.е. декларируем,
     * что конкретно мы хотим сделать, не описывая как, этот подход называется декларативным.
     * Сокращение кода ведёт к уменьшению количества возможных ошибок.
     *
     * https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html
     */

    public static void main(String[] args) {
        List<Animal> animals = getAnimals();
        /**
         * Императивный подход (старый)- громоздкий код из-за того, что подробно описывается порядок действий
         */
        List<Animal> predator = new ArrayList<>();
        for (Animal animal : animals) {
            if (animal.getClassification().equals(Classification.PREDATOR)) {
                predator.add(animal);
            }
        }
        predator.forEach(System.out::println);
        /**
         * Декларативный подход (новый) - кода значительно меньше, так как описывается только необходимы результат
         */
        /** Filter
         * Возвращает по условию
         * Пример:
         * - собирает в коллекцию всех хищников
         * */
        System.out.println("====================Filter==========================");
        List<Animal> predators = animals.stream()
                .filter(a -> a.getClassification().equals(Classification.PREDATOR))
                .collect(Collectors.toList());
        predators.forEach(System.out::println);
        /** Sort
         * Сортирует по параметру
         * Пример:
         * - сортировка по возрастанию имени
         * - сортировка по имени по возрастанию, за тем по возрасту
         * - сортировка по убыванию имени
         * */
        System.out.println("=======================Sort=========================");
        animals.forEach(System.out::println);
        System.out.println("=======================SORTED=======================");
        List<Animal> sortedAnimals = animals.stream()
                .sorted(Comparator.comparing(Animal::getName))
                .collect(Collectors.toList());
        sortedAnimals.forEach(System.out::println);
        System.out.println("=============SORTED BY NAME THEN BY AGE=============");
        sortedAnimals = animals.stream()
                .sorted(Comparator.comparing(Animal::getName).thenComparing(Animal::getAge))
                .collect(Collectors.toList());
        sortedAnimals.forEach(System.out::println);
        System.out.println("======================REVERSED=======================");
        sortedAnimals = animals.stream()
                .sorted(Comparator.comparing(Animal::getName).reversed())
                .collect(Collectors.toList());
        sortedAnimals.forEach(System.out::println);
        /** All match
         * Проверяет, что все сущности в коллекции удовлетворяют условию и возвращает boolean
         * Пример:
         * - утверждение: возраст всех животных больше 10
         */
        System.out.println("====================All match========================");
        boolean allMatch = animals.stream()
                .allMatch(animal -> animal.getAge() > 10);
        System.out.println("Действительно ли возраст всех животных больше 10? - " + allMatch);
        /**
         * Any match
         * Проверяет, что хотябы одна сущность в коллекции удовлетворяют условию и возвращает boolean
         * Пример:
         * - утверждение: возраст хотябы одного животноко меньше 10
         */
        System.out.println("====================Any match=========================");
        boolean oneIsMatch = animals.stream()
                .anyMatch(animal -> animal.getAge() < 10);
        System.out.println("Есть ли среди животных кто-то, чей возраст меньше 10? - " + oneIsMatch);
        /**
         * None match
         * Проверяет, что ни одна сущность в коллекции не удовлетворяет условию и возвращает boolean
         * Пример:
         * - утверждение: в коллекции нет Крысы
         */
        System.out.println("====================None match========================");
        boolean noRat = animals.stream()
                .noneMatch(animal -> animal.getName().equals("Крыса"));
        System.out.println("Среди животных нет крыс? - " + noRat);
        /** Max
         * Возращает максимальное значение
         * Пример:
         * - возвращает старшее по возрвсту животное
         */
        System.out.println("=========================Max==========================");
        animals.stream()
                .max(Comparator.comparing(Animal::getAge))
                .ifPresent(System.out::println);
        /**
         * Min
         * Возращает минимальное значение
         * Пример:
         * - возвращает младшее по возрвсту животное
         */
        System.out.println("=========================Min==========================");
        animals.stream()
                .min(Comparator.comparing(Animal::getAge))
                .ifPresent(System.out::println);
        /**
         * Group
         * Группирует коллекцию по параметрам
         */
        System.out.println("=========================Group==========================");
        Map<Classification, List<Animal>> classificationListMap = animals.stream()
                .collect(Collectors.groupingBy(Animal::getClassification));
        classificationListMap.forEach(((classification, animals1) -> {
            System.out.println(classification);
            animals1.forEach(System.out::println);
            System.out.println();
        }));

        /**
         * Chaining
         * Вызов одного метода за другим
         * Пример:
         * - выводит на экран самого старого хищного животного
         */
        System.out.println("=========================Chaining==========================");
        Optional<String> oldestPredator = animals.stream()
                .filter(animal -> animal.getClassification().equals(Classification.PREDATOR))
                .max(Comparator.comparing(Animal::getAge))
                .map(Animal::getName);
        oldestPredator.ifPresent(System.out::println);
    }

    public static List<Animal> getAnimals() {
        return List.of(
                new Animal("Слон", 20, Classification.HERBIVORE),
                new Animal("Лев", 10, Classification.PREDATOR),
                new Animal("Гиена", 11, Classification.PREDATOR),
                new Animal("Жираф", 20, Classification.HERBIVORE),
                new Animal("Гибон", 35, Classification.OMNIVOROUS),
                new Animal("Гибон", 27, Classification.OMNIVOROUS),
                new Animal("Лошадь", 36, Classification.HERBIVORE),
                new Animal("Мышь", 2, Classification.HERBIVORE),
                new Animal("Рысь", 10, Classification.PREDATOR),
                new Animal("Рысь", 8, Classification.PREDATOR),
                new Animal("Динозавр", 200, Classification.PREDATOR)
        );
    }
}
