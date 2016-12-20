package javaapiclient;

public class School {

    String name;
    int id;

    public School(String name, int id) {
        this.name = name;
        this.id = id;
    }
        
    @Override
    public String toString() {
            return "Product [name=" + name + ", id=" + id + "]";
    }

}