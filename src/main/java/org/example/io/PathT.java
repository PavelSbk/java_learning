package org.example.io;

import java.nio.file.Path;

public class PathT {
    /**
     * Path, по большому счету, — это переработанный аналог класса File. Работать с ним значительно проще, чем с File.
     * Во-первых, из него убрали многие утилитные (статические) методы, и перенесли их в класс Files.
     * Во-вторых, в Path были упорядочены возвращаемые значения методов.
     * В классе File методы возвращали то String, то boolean, то File — разобраться было непросто.
     * Например, был метод getParent(), который возвращал родительский путь для текущего файла в виде строки.
     * Но при этом был метод getParentFile(), который возвращал то же самое, но в виде объекта File!
     * Это явно избыточно. Поэтому в интерфейсе Path метод getParent()
     * и другие методы работы с файлами возвращают просто объект Path. Никакой кучи вариантов — все легко и просто.
     */
    public static void main(String[] args) {
        Path testFilePath = Path.of("C:\\Users\\Username\\Desktop\\testFile.txt");

        /**
         * getFileName() — возвращает имя файла из пути;
         */
        Path fileName = testFilePath.getFileName();
        System.out.println(fileName);
        /**
         * getParent() — возвращает «родительскую» директорию по отношению
         * к текущему пути (то есть ту директорию, которая находится выше по дереву каталогов);
         */
        Path parent = testFilePath.getParent();
        System.out.println(parent);
        /**
         * getRoot() — возвращает «корневую» директорию;
         * то есть ту, которая находится на вершине дерева каталогов;
         */
        Path root = testFilePath.getRoot();
        System.out.println(root);
        /**
         * startsWith(), endsWith() — проверяют, начинается/заканчивается ли путь с переданного пути;
         */
        boolean endsWithTxt = testFilePath.endsWith("Desktop\\testFile.txt");
        System.out.println(endsWithTxt);
        boolean endsWithTxt2 = testFilePath.endsWith("esktop\\testFile.txt");
        System.out.println(endsWithTxt2);
        boolean startsWithLalala = testFilePath.startsWith("lalalala");
        System.out.println(startsWithLalala);
        /**
         * boolean isAbsolute() — возвращает true, если текущий путь является абсолютным;
         */
        System.out.println(testFilePath.isAbsolute());
        /**
         * Path normalize() — «нормализует» текущий путь, удаляя из него ненужные элементы.
         * В популярных операционных системах при обозначении путей
         * часто используются символы “.” (“текущая директория”)
         * и “..” (родительская директория).
         * Например:
         * “./Pictures/dog.jpg” обозначает,
         * что в той директории, в которой мы сейчас находимся, есть папка Pictures, а в ней — файл “dog.jpg”
         *
         * Если в программе появился путь, использующий “.” или “..”,
         * метод normalize() позволит удалить их и получить путь, в котором они не будут содержаться
         */
        Path path5 = Path.of("C:\\Users\\Java\\.\\examples");
        System.out.println(path5.normalize());
        Path path6 = Path.of("C:\\Users\\Java\\..\\examples");
        System.out.println(path6.normalize());
        /**
         * Path relativize() — вычисляет относительный путь между текущим и переданным путем;
         */
        Path testFilePath1 = Path.of("C:\\Users\\Users\\Users\\Users");
        Path testFilePath11 = Path.of("C:\\Users\\Users\\Users\\Users\\Username\\Desktop\\testFile.txt");
        System.out.println(testFilePath1.relativize(testFilePath11));
    }
}
