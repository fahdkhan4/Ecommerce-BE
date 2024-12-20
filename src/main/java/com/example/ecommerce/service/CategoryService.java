package com.example.ecommerce.service;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Category addCategory(Category category){
        return categoryRepository.addCategory(category);
    }

    public void addCategories(){
        List<String> categories = new ArrayList<>();

        categories.add("Electronics");
        categories.add("Fashion");
        categories.add("Home and Kitchen");
        categories.add("Books");
        categories.add("Health and Personal Care");
        categories.add("Beauty and Cosmetics");
        categories.add("Sports and Outdoors");
        categories.add("Toys and Games");
        categories.add("Automotive");
        categories.add("Groceries");
        categories.add("Office Supplies");
        categories.add("Jewelry");
        categories.add("Pet Supplies");
        categories.add("Music and Movies");
        categories.add("Baby Products");


        if(getAllCategory().size() == 0){
            for (String category:categories){
                addCategory(new Category(category));
            }
        }
    }
    public List<Category> getAllCategory(){
        return  categoryRepository.getAllCategory();
    }

    public Category getCategoryById(int id){
        return categoryRepository.getCategoryById(id);
    }

}
