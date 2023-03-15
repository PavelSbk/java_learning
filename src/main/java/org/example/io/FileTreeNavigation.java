package org.example.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileTreeNavigation {
    /**
     * Files.walkFileTree(Path start, FileVisitor visitor) - метод для обхода дерева файлов;
     * FileVisitor — это специальный интерфейс, в котором описаны все методы для обхода дерева файлов;
     * <p>
     * visitFile() - здесь описывается что нужно делать с каждым файлом в каждой директории;
     * return FileVisitResult.CONTINUE - здесь описывается что должна делать программа после того,
     * как выполнен вход в файл, и все необходимые операции совершены.
     * Если необходимо продолжать обход дерева, выбирается вариант CONTINUE;
     * <p>
     * preVisitDirectory() — логика, которую надо выполнять перед входом в папку;
     * <p>
     * visitFileFailed() — что делать, если вход в файл невозможен (нет доступа, или другие причины);
     * <p>
     * postVisitDirectory() — логика, которую надо выполнять после захода в папку.
     */
    public static void main(String[] args) throws IOException {
        Path path = Path.of("C:\\Users\\User\\Desktop\\testDirectory");
        Files.walkFileTree(path, new MyFileVisitor());
    }
}

