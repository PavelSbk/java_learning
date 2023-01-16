package org.example.npe;

import java.util.Optional;

/**
 * Класс Optional появился в Java 8.
 * Задачей этого класса является предоставление решений на уровне типа-обертки
 * для обеспечения удобства обработки возможных null-значений.
 * По сути является контейнером в котором находится ссылка или null.
 * Optional позволяет сократить код, за счёт избавления от проверок на null
 *
 * Полезные источники:
 * https://habr.com/ru/post/658457/
 * https://habr.com/ru/post/346782/
 *
 * Документация JDK19:
 * https://docs.oracle.com/en/java/javase/19/docs/api/java.base/java/util/Optional.html
 *
 */

public class Main {

    public static void main(String[] args) {
        /**
         * Создание пустого Optional с помощью метода .empty()
         */
        System.out.println("==============================================================================");
        Optional<String> empty = Optional.empty();
        System.out.println(empty.isPresent());
        System.out.println(empty.isEmpty());
        /**
         * orElse(T other)
         * Если Optional содержит значение, то вернёт значение или переданное значение Т other
         * При передачи в Optional значения с помощью .of(), возможен NPE, если значение null
         * При передачи в Optional значения с помощью .ofNullable(), NPE не появится
         */
        System.out.println("==============================================================================");
        String orElse = empty.orElse("world");
        System.out.println("Optional пуст, orElse(\"world\") возвращает - " + orElse);

        Optional<String> hello = Optional.ofNullable("hello");
        orElse = hello.orElse("world");
        System.out.println("Optional содержит строку \"Hello\", orElse(\"world\") возвращает - " + orElse);

        hello = Optional.ofNullable(null);
        orElse = hello.orElse("world");
        System.out.println("Optional содержит null, orElse(\"world\") возвращает - " + orElse);

        hello = Optional.ofNullable("hello");
        orElse = hello
                .map(String::toUpperCase)
                .orElse("world");
        System.out.println(orElse);
        /**
         * orElseThrow()
         * Exeption можно вызвать с помощью метода orElseThrow()
         * map(Function<? super T,? extends U> mapper) - преобразовывает объект в другой объект, но
         * срабатывает только в том случае, если в Optional есть не-нулловый объект.
         */
        System.out.println("==============================================================================");
        hello = Optional.ofNullable("hello");
        orElse = hello
                .map(String::toUpperCase)
                .orElseThrow(IllegalAccessError::new);
        System.out.println(orElse);
        /**
         * ifPresent(Consumer<? super T> action)
         * Если значение существует, выполнится переданное выражение, в противном случае ничего не произойдёт
         */
        System.out.println("==============================================================================");
        hello = Optional.ofNullable("hello");
        hello.ifPresent(world -> {
            System.out.println(world);
        });
        System.out.println("==============================================================================");
        hello.ifPresent(System.out::println);
        System.out.println("==============================================================================");
        hello.ifPresentOrElse(world -> {
            System.out.println(world);
        }, () -> System.out.println("world"));
    }
}
