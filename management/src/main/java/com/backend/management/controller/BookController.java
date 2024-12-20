package com.backend.management.controller;

import com.backend.management.model.Book;
import com.backend.management.model.BookCategory;
import com.backend.management.model.PaginatedResponse;
import com.backend.management.service.BookCategoryService;
import com.backend.management.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookCategoryService bookCategoryService;

    // lay tat ca cac sach
    @GetMapping
    public ResponseEntity<PaginatedResponse<Book>> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Page<Book> books = bookService.getAllBooks(page, size);
            PaginatedResponse<Book> response = PaginatedResponse.of(books);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/categories/{bigCategoryName}/{subCategoryName}/books")
    public ResponseEntity<List<Book>> getBooksBySubCategory(@PathVariable String subCategoryName,
                                                            @PathVariable String bigCategoryName){
        List<Book> books = bookService.getBooksBySubCategory(subCategoryName, bigCategoryName);
        return ResponseEntity.ok(books);
    }



    //lay sach theo id
    @GetMapping("/{bookId}")
    public Optional<Book> getBookByBookId(@PathVariable String bookId) {
        return bookService.getBookByBookId(bookId);
    }

    //them sach
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    //cap nhat sach theo id
    @PutMapping("/update/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable String bookId, @RequestBody Book updatedBook) {
        Book book = bookService.updateBook(bookId,updatedBook);
        return ResponseEntity.ok(book);

    }

    // xoa sach theo id
    @DeleteMapping("/delete/{bookId}")
    public void deleteBook(@PathVariable String bookId) {
        bookService.deleteBook(bookId);
    }


    // lay ca the loai lon
    @GetMapping("/categories")
    public ResponseEntity<List<String>> getBigCategories() {
        List<String> bigCategories = bookCategoryService.getAllBigCategories();
        return ResponseEntity.ok(bigCategories);
    }


    @GetMapping("/categories/{bigCategorySlug}")
    public  List<String> getSmallCategories(@PathVariable String bigCategorySlug){
        return bookCategoryService.getSmallCategories(bigCategorySlug);
    }







    @GetMapping("/{bookId}/availability")
    public boolean checkAvaibility(@PathVariable String bookId){
        return bookService.isBookAvailable(bookId);
    }

    //tong so luong sach dang co
    @GetMapping("/total")
    public int getTotalBooksInStock(){
        return bookService.getTotalBooksInStock();
    }

    @GetMapping("/category-distribution")
    public Map<String, Long> getCategoryDistribution() {
        return bookService.getCategoryDistribution();
    }

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam(required = false) String title,
                                  @RequestParam(required = false) String author)
    {
        return bookService.searchBooks(title,author);
    }

    @GetMapping("/suggest")
    public ResponseEntity<List<Book>> suggestBooks(
            @RequestParam String query
    ) {
        return ResponseEntity.ok(bookService.suggestBooks(query));
    }
    // sua danh muc lon
    @PutMapping("/update-big-category")
    public ResponseEntity<String> updateBigCategoryName(
            @RequestParam String oldName,
            @RequestParam String newName){

        bookService.updateBigCategoryName(oldName,newName);
        return ResponseEntity.ok("success");
    }

    //xoa danh muc lon
    @DeleteMapping("delete-big-category")
    public ResponseEntity<String> deleteBigCategory(@RequestParam  String bigCategoryName){
        bookService.deleteBigCategoryName(bigCategoryName);
        return ResponseEntity.ok("delete success");
    }



//    @PutMapping("/update-small-category")
//    public ResponseEntity<String> updateSmallCategory(
//            @RequestParam String bigCategoryName,
//            @RequestParam String oldSmallCategoryName,
//            @RequestParam String newSmallCategoryName) {
//
//        bookService.updateSmallCategory(bigCategoryName, oldSmallCategoryName, newSmallCategoryName);
//        return ResponseEntity.ok("Small category updated successfully.");
//    }








}

