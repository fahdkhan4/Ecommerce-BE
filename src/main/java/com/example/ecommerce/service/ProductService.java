package com.example.ecommerce.service;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.OrderItem;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.helper.ProductDTO;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductImageService productImageService;

    @Autowired
    CategoryRepository categoryRepository;

    public Product addProduct(Product product){
       return productRepository.addProduct(product);
    }

    public List<Product> getAllProducts(){
        return productRepository.getAllProduct();
    }

    public ProductDTO getProductById(int id) throws IOException {
        ProductDTO product =  productToProductDTO(productRepository.getProductById(id));
        product.setProductImages(productImageService.getProductImageByProductId(id));
        return product;
    }

    public List<ProductDTO> getProductsByUserId(int userId) throws IOException {
        List<ProductDTO> products = productToDTO(productRepository.getProductByUserId(userId));

        for(ProductDTO product:products){
            product.setProductImages(productImageService.getProductImageByProductId(product.getId()));
        }

        return products;
    }

    public List<ProductDTO> productToDTO(List<Product> products){
        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product product:products){
            productDTOS.add(productToProductDTO(product));
        }
        return productDTOS;
    }


    public ProductDTO productToProductDTO(Product product){
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getWeight(),
                product.getDimension(),
                product.getQuantity(),
                product.getUploadedDate(),
                product.getStatus(),
                product.getUser_id(),
                product.getCategory_id(),
                null);
    }

    public List<ProductDTO> filterProductsByCategory(String category) throws IOException {

        List<ProductDTO> productDTOS = null;

        if(!category.equalsIgnoreCase("all")) {

            Category selectedCategory = categoryRepository.getAllCategory()
                    .stream()
                    .filter(el -> el.getName().equalsIgnoreCase(category))
                    .findAny().get();

            if (Objects.isNull(selectedCategory)) {
                throw new IllegalArgumentException(category + " doesn't exist !");
            }

            productDTOS = productToDTO(
                    productRepository.searchProductsByCategory(selectedCategory.getId())
            );

        }
        else{
           productDTOS =  productToDTO(getAllProducts());
        }

        for(ProductDTO product:productDTOS){
            product.setProductImages(productImageService.getProductImageByProductId(product.getId()));
        }

        return productDTOS;
    }

    public List<ProductDTO> filterProductsByName(String search) throws IOException {
        List<ProductDTO> productDTOS = productToDTO(
                productRepository.searchProductsByName(search)
        );

        for(ProductDTO product:productDTOS){
            product.setProductImages(productImageService.getProductImageByProductId(product.getId()));
        }

        return productDTOS;
    }

    public void decrementProductQuantity(List<OrderItem> orderItemList) {
        for (OrderItem item:orderItemList){
           Product product =  productRepository.getProductById(item.getProductId());
           product.setQuantity(product.getQuantity()-item.getQuantity());
           productRepository.updateProductQuantity(product);
        }
    }
}
