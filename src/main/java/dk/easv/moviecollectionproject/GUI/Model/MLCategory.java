package dk.easv.moviecollectionproject.GUI.Model;

import dk.easv.moviecollectionproject.BE.Category;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MLCategory {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;

    public MLCategory(Category category) {
        this.id = new SimpleIntegerProperty(category.getId());
        this.name = new SimpleStringProperty(category.getName());
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }
}
