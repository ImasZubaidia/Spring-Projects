package com.imas.axsos.controllers;

import java.awt.print.Book;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imas.axsos.services.BookService;

@RestController
public class BooksApi {
private final BookService bookService;
	
	public BooksApi(BookService bookService) {
		this.bookService = bookService;
	}
	
	@RequestMapping("/api/books")
	public List<Book> index() {
		return bookService.allBooks();
	}
	
	@RequestMapping(value="/api/books", method=RequestMethod.POST)
	public Book create(
			@RequestParam(value="title") String title, 
			@RequestParam(value="description") String desc, 
			@RequestParam(value="language") String lang, 
			@RequestParam(value="pages") Integer numOfPages){
		Book book = new Book(title, desc, lang, numOfPages);
		return bookService.createBook(book);
	}
	
	@RequestMapping("/api/books/{id}")
	public Book show(@PathVariable("id") Long id) {
		Book book = bookService.findBook(id);
		return book;
	}
	
	// to update a row in our database, we use the 'PUT' method
	// you will also need to change the Postman method to 'PUT' in order for this to work
	// the update process in Postman is very similar to adding rows with a 'POST' method
	@RequestMapping(value="/api/books/{id}", method=RequestMethod.PUT)
	// first we get the Book ID from our path, we then get the title, description, language, and pages values
    public Book update(@PathVariable("id") Long id, @RequestParam(value="title") String title, @RequestParam(value="description") String desc, @RequestParam(value="language") String lang, @RequestParam(value="pages") Integer numOfPages) {
		// we create a new Book object with the values we passed in
		Book book = new Book(title=, desc, lang, numOfPages);
		// we then set the ID of the new Book object to equal the ID of the Book we are updating
		book.setId(id);
		// now we can use our bookService to update the book
		Book updatedBook = bookService.updateBook(book);
        return updatedBook;
    }
    
	// to delete a row, we must use the 'DELETE' method
	// in Postman, you will also have to use the 'DELETE' method
    @RequestMapping(value="/api/books/{id}", method=RequestMethod.DELETE)
    // we only need the Book ID to delete the book in our bookService
    public void destroy(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
    }
}