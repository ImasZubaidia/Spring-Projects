package com.imas.axsos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.imas.axsos.models.Category;
import com.imas.axsos.models.Product;
import com.imas.axsos.repositories.ProductRepo;

@Service
public class ProductService {
	private final ProductRepo productRepo;
	
	public ProductService(ProductRepo productRepo) {
		this.productRepo = productRepo;
	}
	
	public List<Product> allProducts(){
		return productRepo.findAll();
	}
	
	public List<Product> getAssignedCategories(Category category){
		return productRepo.findAllByCategories(category);
	}
	
	public List<Product> getUnassignedCategories(Category category){
		return productRepo.findByCategoriesNotContains(category);
	}
	
	public Product findById(Long id) {
		Optional<Product> optionalProduct = productRepo.findById(id);
		if(optionalProduct.isPresent()) {
			return optionalProduct.get();
		}else {
			return null;
		}
	}
	
	public Product addProduct(Product product) {
		return productRepo.save(product);
	}
	
	public Product updateProduct(Product product) {
		return productRepo.save(product);
	}
	
	public void deleteProduct(Product product) {
		productRepo.delete(product);
	}
}