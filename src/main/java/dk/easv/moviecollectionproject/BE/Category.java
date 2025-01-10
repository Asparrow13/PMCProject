package dk.easv.moviecollectionproject.BE;

import java.util.ArrayList;

public class Category {
    private int id;
    private String name;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Category [id=" + id + ", name=" + name + "]";
    }

    public ArrayList<Category> toArrayList() {
        ArrayList<Category> categories = new ArrayList<Category>();
        categories.add(this);
        return categories;
    }
}

