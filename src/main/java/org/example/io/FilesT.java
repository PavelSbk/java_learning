package org.example.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class FilesT {
    /**
     * Files — это утилитный класс, куда были вынесены статические методы из класса File. Files — это примерно то же,
     * что и Arrays или Collections, только работает он с файлами, а не с массивами и коллекциями;
     * Он сосредоточен на управлении файлами и директориями.
     * Используя статические методы Files, мы можем создавать, удалять и перемещать файлы и директории.
     * Для этих операций используются методы createFile() (для директорий — createDirectory()), move() и delete().
     */
    public static void main(String[] args) throws IOException {
        // Удаление файла из директории (если он существует)
        if (Files.deleteIfExists(Path.of("C:\\Users\\User\\Desktop\\testDirectory\\testFile111.txt"))) {
            System.out.println("Удаление файла прошло успешно?");
            System.out.println(!Files.exists(Path.of("C:\\Users\\User\\Desktop\\testDirectory\\testFile111.txt")));
            System.out.println("===========================");
        }
        // Удаление директории с файлом (если она существует)
        if (Files.deleteIfExists(Path.of("C:\\Users\\User\\Desktop\\testDirectory"))) {
            System.out.println("Удаление директории прошло успешно?");
            System.out.println(!Files.exists(Path.of("C:\\Users\\User\\Desktop\\testDirectory")));
            System.out.println("===========================");
        }

        // Создание файла
        Path testFile1 = Path.of("C:\\Users\\User\\Desktop\\testFile111.txt");
        Files.createFile(testFile1);
        System.out.println("Был ли файл успешно создан?");
        System.out.println(Files.exists(testFile1));
        System.out.println("===========================");
        // Создание директории
        Path testFile2 = Path.of("C:\\Users\\User\\Desktop\\testDirectory");
        Files.createDirectory(testFile2);
        System.out.println("Была ли директория успешно создана?");
        System.out.println(Files.exists(testFile2));
        System.out.println("===========================");

        // Перемещаем файл с рабочего стола в директорию testDirectory.
        // Перемещать надо с указанием имени файла в папке!
        testFile1 = Files.move(testFile1,
                Path.of("C:\\Users\\User\\Desktop\\testDirectory\\testFile111.txt"),
                REPLACE_EXISTING
        );
        System.out.println("Остался ли наш файл на рабочем столе?");
        Path whereTo = Path.of("C:\\Users\\User\\Desktop\\testFile111.txt");
        System.out.println(Files.exists(whereTo));
        System.out.println("===========================");

        System.out.println("Был ли наш файл перемещен в testDirectory?");
        Path whereIs = Path.of("C:\\Users\\User\\Desktop\\testDirectory\\testFile111.txt");
        System.out.println(Files.exists(whereIs));
        System.out.println("===========================");
        /**
         * Для записи данных в файл в класс Files есть метод write(),
         * а для чтения — целых 3: read(), readAllBytes() и readAllLines()
         *
         * readAllLines() - тип возвращаемого значения — List<String>!
         * То есть он возвращает список строк файла. Конечно, это делает работу с содержимым очень удобной,
         * ведь весь файл, строку за строкой, можно, например, вывести в консоль в обычном цикле for;
         */
        List<String> lines = Files.readAllLines(Path.of("C:\\projects\\java_learning\\data\\Pushkin.txt"), UTF_8);
        for (String s: lines) {
            System.out.println(s);
        }
        System.out.println("===========================");
        /**
         * найти в файле все строки, которые начинаются со слова «Как», привести их к UPPER CASE и вывести в консоль
         */
        lines.stream()
                .filter(s -> s.startsWith("Как"))
                .map(String::toUpperCase)
                .toList()
                .forEach(System.out::println);
    }
}
