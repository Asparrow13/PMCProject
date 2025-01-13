package dk.easv.moviecollectionproject.GUI.Controller;

import dk.easv.moviecollectionproject.BE.Category;
import dk.easv.moviecollectionproject.BLL.BLCategory;
import dk.easv.moviecollectionproject.GUI.Model.MLCategory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MCController {

    @FXML
    private TableView<MLCategory> categoryTableView;

    @FXML
    private TableColumn<MLCategory, String> categoryNameColumn;

    private final BLCategory blCategory = new BLCategory();
    private final ObservableList<MLCategory> categories = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Set up the table column to use the name property
        categoryNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Load categories from the BLL and populate the TableView
        loadCategories();
    }

    private void loadCategories() {
        categories.clear();
        for (Category category : blCategory.getAllCategories()) {
            categories.add(new MLCategory(category));
        }
        categoryTableView.setItems(categories);
    }
}
