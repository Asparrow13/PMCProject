package dk.easv.moviecollectionproject.BLL;

import dk.easv.moviecollectionproject.BE.Category;
import dk.easv.moviecollectionproject.DAL.DBCategory;

import java.util.List;

public class BLCategory {
    DBCategory dbCategory = new DBCategory();

    public List<Category> getAllCategories() {
        return dbCategory.getAllCategories();
    }

    public Category getCategory(int id) {
        return dbCategory.getCategoryById(id);
    }

    public String getCategoryName(int id){
        return dbCategory.getCategoryById(id).getName();
    }

    public void removeCategory(int id) {
        dbCategory.removeCategory(id);
    }
}
