package org.example.serialization;

import java.io.*;

public class SerializatorT {

    public boolean serializator(Object obj, String path) {
        boolean rst = false;
        try (var oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(obj);
            rst = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rst;
    }

    public boolean deserializator(String path) throws IOException, ClassNotFoundException {
        boolean rst = false;
        try (var ois = new ObjectInputStream(new FileInputStream(path))) {
            final Object object = ois.readObject();
            rst = true;
            System.out.println(object);
        }
        return rst;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String path = "C:\\projects\\java_learning\\data\\serTest.data";
        var ser = new SerializatorT();
        System.out.println(ser.serializator(new Animal("Dog"), path));
        System.out.println(ser.deserializator(path));

    }
}
