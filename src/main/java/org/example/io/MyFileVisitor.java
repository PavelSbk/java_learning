package org.example.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * SimpleFileVisitor<Path> - интерфейс, позволяющий имплементировать методы по отдельности,
 * в отличие от FileVisitor<Path>;
 * <p>
 * visitFile() - здесь описывается что нужно делать с каждым файлом в каждой директории;
 * <p>
 * preVisitDirectory() — логика, которую надо выполнять перед входом в папку;
 * <p>
 * visitFileFailed() — что делать, если вход в файл невозможен (нет доступа, или другие причины);
 * <p>
 * postVisitDirectory() — логика, которую надо выполнять после захода в папку.
 *
 * CONTINUE - продолжить обход дерева по файлам;
 * TERMINATE - прекратить обход дерева по файлам;
 * SKIP_SUBTREE - пропустить данную директорию (в данную директорию заходить не нужно);
 * SKIP_SIBLINGS - в данной директории продолжать обход по файлам не нужно;
 * SIBLING - это "brother" или содержимое данной папки, в которой находится файл, кроме самого файла
 */

public class MyFileVisitor implements FileVisitor<Path> {

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        System.out.println("Enter to Directory: " + dir);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        System.out.println("File name: " + file.getFileName());
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        System.out.println("Error while visiting file: " + file.getFileName());
        return FileVisitResult.TERMINATE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        System.out.println("Exit from Directory: " + dir);
        return FileVisitResult.CONTINUE;
    }
}
