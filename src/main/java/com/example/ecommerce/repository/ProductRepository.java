package com.example.ecommerce.repository;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Product addProduct(Product product){
        jdbcTemplate.update(
                "INSERT INTO product (name, price, description, weight, dimension, quantity, uploaded_date, status, user_id, category_id) " +
                        "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getWeight(),
                product.getDimension(),
                product.getQuantity(),
                LocalDate.now(),
                product.getStatus(),
                product.getUser_id(),
                product.getCategory_id()
        );

        int newProductId = getLatestProductId().get();
        return getProductById(newProductId);
    }

    public List<Product> getAllProduct(){
        return jdbcTemplate.query("SELECT * FROM product", rs -> {
            List<Product> productList = new ArrayList<>();

            while (rs.next()) {
                productList.add(new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getDouble(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getDate(8).toLocalDate(),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getInt(11)
                ));
            }
            return productList;
        });
    }

    public Product getProductById(int id){
        String query = "Select * from product where id=?";
        Object[] arg = {id};
        return jdbcTemplate.query(query,arg,rs->{
            Product product = null;
            if(rs.next()) {
                product = new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getDouble(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getDate(8).toLocalDate(),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getInt(11)
                );
            }
            return product;
        });
    }

    public Optional<Integer> getLatestProductId() {
        String query = "SELECT id FROM product ORDER BY id DESC LIMIT 1";

        return jdbcTemplate.query(query, rs -> {
            if (rs.next()) {
                return Optional.of(rs.getInt(1));
            }
            return Optional.empty();
        });
    }

    public List<Product> getProductByUserId(int userId) {
        String query = "SELECT * FROM product where user_id=?";
        Object[] arg = {userId};
        return jdbcTemplate.query(query,arg, rs -> {
            List<Product> productList = new ArrayList<>();

            while (rs.next()) {
                productList.add(new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getDouble(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getDate(8).toLocalDate(),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getInt(11)
                ));
            }
            return productList;
        });
    }

    public List<Product> searchProductsByCategory(int id) {
        String query = "SELECT * FROM product where category_id=?";
        Object[] arg = {id};
        return jdbcTemplate.query(query,arg, rs -> {
            List<Product> productList = new ArrayList<>();

            while (rs.next()) {
                productList.add(new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getDouble(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getDate(8).toLocalDate(),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getInt(11)
                ));
            }
            return productList;
        });
    }

    public List<Product> searchProductsByName(String search) {
        String query = "SELECT * FROM product WHERE name LIKE ?";
        Object[] arg = { "%" + search + "%" };
        return jdbcTemplate.query(query,arg, rs -> {
            List<Product> productList = new ArrayList<>();

            while (rs.next()) {
                productList.add(new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getDouble(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getDate(8).toLocalDate(),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getInt(11)
                ));
            }
            return productList;
        });
    }

    public void updateProductQuantity(Product product) {
        String sql = "UPDATE Product SET quantity =? where id=?";
        int rowsUpdated = jdbcTemplate.update(sql,product.getQuantity(),product.getId());
    }
}
