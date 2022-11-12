package com.imas.axsos.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.imas.axsos.models.Category;
import com.imas.axsos.models.Product;
import com.imas.axsos.services.CategoryService;
import com.imas.axsos.services.ProductService;

@Controller
public class MainController {

	@Autowired
	private ProductService prodService;
	
	@Autowired
	private CategoryService catService;
	
	@GetMapping("/")
	public String index(Model model) {
		List<Product> products = prodService.allProducts();
		List<Category> categories = catService.allCategories();
		
		model.addAttribute("products", products);
		model.addAttribute("categories", categories);
		
		return "index.jsp";
	}
	
	@GetMapping("/products/new")
	public String newProduct(@ModelAttribute("product") Product product) {
		return "new_product.jsp";
	}
	
	@PostMapping("/products/new")
	public String addNewProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "new_product.jsp";
		}else {
			prodService.addProduct(product);
			return "redirect:/";
		}
	}
	
	@GetMapping("/products/{id}")
	public String showProduct(@PathVariable("id") Long id, Model model) {
		Product product = prodService.findById(id);
		model.addAttribute("assignedCategories", catService.getAssignedProducts(product));
		model.addAttribute("unassignedCategories", catService.getUnassignedProducts(product));
		model.addAttribute("product", product);
		return "show_product.jsp";
	}
	
	@PostMapping("/products/{id}")
	public String editProduct(@PathVariable("id") Long id, @RequestParam(value="categoryId") Long catId,  Model model) {
		Product product = prodService.findById(id);
		Category category = catService.findById(catId);
		product.getCategories().add(category);
		prodService.updateProduct(product);
		model.addAttribute("assignedCategories", catService.getAssignedProducts(product));
		model.addAttribute("unassignedCategories", catService.getUnassignedProducts(product));
		return "redirect:/products/" + id;
	}
	
	@GetMapping("/categories/new")
	public String newCategory(@ModelAttribute("category") Category category) {
		return "new_category.jsp";
	}
	
	@PostMapping("/categories/new")
	public String addNewCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "new_category.jsp";
		}else {
			catService.addCategory(category);
			return "redirect:/";
		}
	}
	
	@GetMapping("/categories/{id}")
	public String showCategory(@PathVariable("id") Long id, Model model) {
		Category category = catService.findById(id);
		model.addAttribute("assignedProducts", prodService.getAssignedCategories(category));
		model.addAttribute("unassignedProducts", prodService.getUnassignedCategories(category));
		model.addAttribute("category", catService.findById(id));
		return "show_category.jsp";
	}
	
	@PostMapping("/categories/{id}")
	public String editCategory(@PathVariable("id") Long id, @RequestParam(value="productId") Long productId, Model model) {
		Category category = catService.findById(id);
		Product product = prodService.findById(productId);
		category.getProducts().add(product);
		catService.updateCategory(category);
		model.addAttribute("assignedProducts", prodService.getAssignedCategories(category));
		model.addAttribute("unassignedProducts", prodService.getUnassignedCategories(category));
		return "redirect:/categories/" + id;
	}

}