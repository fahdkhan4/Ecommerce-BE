package com.example.ecommerce;

import com.example.ecommerce.service.CategoryService;
import com.example.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class EcommerceApplication {


	public static void main(String[] args) {


		var context = SpringApplication.run(EcommerceApplication.class, args);
		CategoryService categoryService = context.getBean(CategoryService.class);
		categoryService.addCategories();
	}

}
