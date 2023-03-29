package org.example.serialization;

import java.io.Serializable;

public class Animal implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Animal {"
                + " name = '" + name + '\''
                + '}';
    }
}
