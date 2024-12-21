package com.example.ecommerce.repository;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductImageRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public ProductImage addProductImage(ProductImage productImage){
        jdbcTemplate.update(
                "INSERT INTO product_image (product_id,image_path) " +
                        "VALUES (?,?)",
                productImage.getProduct_id(),
                productImage.getImage_path()

        );

        return getProductImageById(getLatestProductImageId().get());
    }

    public List<ProductImage> getAllProductImage(){
        return jdbcTemplate.query("SELECT * FROM product_image", rs -> {
            List<ProductImage> productImages = new ArrayList<>();

            while (rs.next()) {
                productImages.add(new ProductImage(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3)
                ));
            }
            return productImages;
        });
    }

    public ProductImage getProductImageById(int id){
        String query = "Select * from product_image where id=?";
        Object[] arg = {id};
        return jdbcTemplate.query(query,arg,rs->{
            ProductImage productImage = null;
            if(rs.next()) {
                productImage = new ProductImage(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3)
                );
            }
            return productImage;
        });
    }

    public List<ProductImage> getProductImageByProductId(int productId){
        String query = "Select * from product_image where product_id=?";
        Object[] arg = {productId};
        return jdbcTemplate.query(query,arg,rs->{
            List<ProductImage> productImages = new ArrayList<>();

            while (rs.next()) {
                productImages.add(new ProductImage(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3)
                ));
            }
            return productImages;
        });
    }

    public Optional<Integer> getLatestProductImageId(){
        String query = "SELECT * FROM product_image ORDER BY id DESC LIMIT 1";

        return jdbcTemplate.query(query, rs -> {
            if (rs.next()) {
                return Optional.of(rs.getInt(1));
            }
            return Optional.empty();
        });
    }
}
