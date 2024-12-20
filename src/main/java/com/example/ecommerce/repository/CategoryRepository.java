package com.example.ecommerce.repository;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepository {


    @Autowired
    JdbcTemplate jdbcTemplate;

    public Category addCategory(Category category){
        jdbcTemplate.update(
                "INSERT INTO category (name) " +
                        "VALUES (?)",
               category.getName()
        );

        return getCategoryById(getLatestCategoryId().get());
    }

    public List<Category> getAllCategory(){
        return jdbcTemplate.query("SELECT * FROM category", rs -> {
            List<Category> categories = new ArrayList<>();

            while (rs.next()) {
                categories.add(new Category(
                        rs.getInt(1),
                        rs.getString(2)
                ));
            }
            return categories;
        });
    }

    public Category getCategoryById(int id){
        String query = "Select * from category where id=?";
        Object[] arg = {id};
        return jdbcTemplate.query(query,arg,rs->{
            Category category = null;
            if(rs.next()) {
                category = new Category(
                        rs.getInt(1),
                        rs.getString(2)
                );
            }
            return category;
        });
    }

    public Optional<Integer> getLatestCategoryId(){
        String query = "SELECT * FROM category ORDER BY id DESC LIMIT 1";

        return jdbcTemplate.query(query, rs -> {
            if (rs.next()) {
                return Optional.of(rs.getInt(1));
            }
            return Optional.empty();
        });
    }

}
