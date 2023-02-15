package org.example.bitwiseoperations;

/**
 * ! ВАЖНО !
 * >> - побитовый сдвиг вправо аналогичен целочисленному делению на 2 (точнее степень 2 - 2^1)
 * 9 >> 1 = 4 (1 - это степень 2)
 * << - побитовый сдвиг влево аналогичен целочисленному умножению на 2
 * 9 << 1 = 18 (1 - это степень 2)
 * Эти операции вычисляются за 1 такт, в отличие от операций умножения * и деления /
 */

public class BitwiseOperations {

    public static class BitArray {
        int size;
        byte[] bytes;
        /**
         * Маска для работы с отдельными разрядами
         */
        private final byte[] mask = {0b00000001, 0b00000010, 0b00000100, 0b00001000,
                0b00010000, 0b00100000, 0b01000000, (byte)  0b10000000};
        // (byte)  0b10000000 - т.к. 10000000 больше 1 байта

        /**
         * Конструктор с валидацией
         * Размер идёт в битах и необходимо выяснить, сколько байт понадобится,
         * чтобы разместить все эти биты
         * если int size не кратен 8, size++
         */
        public BitArray(int size) {
            this.size = size;
            int sizeInBytes = size / 8; // в каждом байте 8 бит
            if (size % 8 != 0) { // если размер не кратен 8
                sizeInBytes++;
            }
            bytes = new byte[sizeInBytes];
        }

        public int get(int index) {
            int byteIndex = index / 8; // положение байта
            int bitIndex = index % 8; // положение бита
            return (bytes[byteIndex] & mask[bitIndex]) > 0 ? 1 : 0; // сравнивает бит с битом в маске
        }

        public void set(int index, int value) { // value - если 0 - сбросить бит, 1 - установить
            int byteIndex = index / 8;
            int bitIndex = index % 8;
            if (value > 0) {
                bytes[byteIndex] = (byte) (bytes[byteIndex] | mask[bitIndex]); // установка бита
            } else {
                bytes[byteIndex] = (byte) (bytes[byteIndex] & ~mask[bitIndex]); // обнуление бита
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size; i++) {
                sb.append(get(i) > 0 ? '1' : '0');
            }
            return sb.toString();
        }
    }

    /**
     * Метод возвращает количество бит,
     * которое необходимо поменять для преобразования числа X в число Y и наоборот.
     * Метод принимает два различных целых числа на входе.
     * Например, для чисел 12 и 16 метод должен вернуть 3.
     */
    public static int findNumberOfBits(int x, int y) {
        int bitCount = 0;
        int z = x ^ y; // применяем оператор XOR ко входящим значениям
        while (z != 0) { // в цикле while увеличиваем счетчик
            bitCount += z & 1;
            z = z >> 1; // если последняя двоичная цифра = 1 и сдвигаем z на единицу вправо
        }
        return bitCount;
    }

    public static String binary(int i) {
        return Integer.toBinaryString(i);
    }

    public static void printBinary(int i) {
        System.out.println("0b" + Integer.toBinaryString(0b10000000 | (i & 0xff)).substring(1));
    }

    public static void main(String[] args) {
        System.out.println("===== Запись числа в двоичном коде 0b ====");
        int i = 0b1001;
        System.out.println(i);
        System.out.println("==== Преобразования числа в двоичный код ====");
        int i2 = 55;
        System.out.println(Integer.toBinaryString(i2));
        System.out.println("==== Побитовый сдвиг числа 9 (1001) вправо: ====");
        System.out.println("В десятичном виде после сдвига 9 >> 1 : " + (i >> 1));
        System.out.println("В двоичном виде после сдвига 1001 >> 1 : " + Integer.toBinaryString(i >> 1));
        /**
         * 1001 >> 0100 - сдвиг вправо на 1 разряд
         */
        System.out.println("==== Побитовый сдвиг числа 9 (1001) влево: ====");
        System.out.println("В десятичном виде после сдвига 9 << 1 : " + (i << 1));
        System.out.println("В двоичном виде после сдвига 1001 << 1 : " + Integer.toBinaryString(i << 1));
        /**
         * 1001 << 10010 - сдвиг влево на 1 разряд
         */
        System.out.println("Быстрое умножение 9 на 4 с помощью сдвига влево (9 << 2): " + (i << 2));
        System.out.println("Быстрое деление 9 на 4 с помощью сдвига вправо (9 >> 2): " + (i >> 2));
        System.out.println("==== Побитовое И (&) где 1 & 1 = 1; 1 & 0 = 0; 0 & 0 = 0 ====");
        int b1 = 9;
        int b2 = 10;
        System.out.println("     9 в двоичном коде: " + binary(b1));
        System.out.println("    10 в двоичном коде: " + binary(b2));
        System.out.println("    ------------------------");
        System.out.println("9 & 10 в двоичном коде: " + binary(b2 & b1) + " = 8");
        System.out.println("==== Побитовое ИЛИ (|) где 1 | 1 = 1; 1 | 0 = 1; 0 | 0 = 0 ====");
        System.out.println("     9 в двоичном коде: " + binary(b1));
        System.out.println("    10 в двоичном коде: " + binary(b2));
        System.out.println("    ------------------------");
        System.out.println("9 | 10 в двоичном коде: " + binary(b2 | b1) + " = 11");
        System.out.println("==== Побитовое XOR (^) где 1 ^ 1 = 0; 1 | 0 = 1; 0 | 0 = 0 ====");
        System.out.println("     9 в двоичном коде: " + binary(b1));
        System.out.println("    10 в двоичном коде: " + binary(b2));
        System.out.println("    ------------------------");
        System.out.println("9 ^ 10 в двоичном коде: " + binary(b2 ^ b1) + " = 3");
        System.out.println("==== Инверсия (~) где 1 = 0; 0 = 1 ====");
        System.out.println("     9 в двоичном коде: " + binary(b1));
        System.out.println("    ------------------------");
        System.out.println("~ 9 в двоичном коде: " + binary(~b1) + " = 4294967286");
        /**
         * Дано число 11011000 = 216
         * Что бы добавить к нему маску 00000010 нужно: 11011000 | 00000010 = 11011010 - 218
         *
         * 11011000
         * 00000010
         * --------
         * 11011010
         */
        int b = 0b11011000;
        System.out.println(binary(b));
        System.out.println(binary(b | 0b00000010));
        System.out.println("=====================");
        System.out.println(findNumberOfBits(12, 16) + System.lineSeparator());
        int alpha = 255;
        int red = 64;
        int green = 128;
        int blue = 32;
        System.out.println("==== Объединение байтов на примере цвета ====");
        printBinary(alpha);
        printBinary(red);
        printBinary(green);
        printBinary(blue);
        int color = alpha << 24 | red << 16 | green << 8 | blue;
        System.out.println(binary(color) + System.lineSeparator());
        System.out.println("==== Разъединение байтов на примере цвета ====");
        System.out.println(binary(color));
        int z1 = color & 0b11111111;
        int z2 = (color & 0b1111111100000000) >> 8;
        int z3 = (color & 0b111111110000000000000000) >> 16;
        int z4 = (color & 0b11111111000000000000000000000000) >> 24;
        z1 = color & 0xFF;
        z2 = (color & 0xFF00) >> 8;
        z3 = (color & 0xFF0000) >> 16;
        z4 = (color & 0xFF000000) >> 24;
        z1 = color & 0xFF;
        z2 = (color & 0xFF << 8) >> 8;
        z3 = (color & 0xFF << 16) >> 16;
        z4 = (color & 0xFF << 24) >> 24;
        printBinary(z1);
        printBinary(z2);
        printBinary(z3);
        printBinary(z4);
        System.out.println("===================");
        BitArray bitArray = new BitArray(10);
        bitArray.set(0, 1);
        bitArray.set(9, 1);

        bitArray.set(5, 1);
        bitArray.set(5, 0);

        System.out.println(bitArray);

    }
}
