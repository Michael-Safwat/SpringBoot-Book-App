/*
package com.michael.BookStore.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.michael.BookStore.TestDataUtil;
import com.michael.BookStore.domain.dto.BookDto;
import com.michael.BookStore.domain.entities.BookEntity;
import com.michael.BookStore.services.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTests
{
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private BookService bookService;

    @Autowired
    public BookControllerIntegrationTests(MockMvc mockMvc, BookService bookService) {
        this.mockMvc = mockMvc;
        this.bookService = bookService;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatCreateBookReturnsHttpStatus201Created() throws Exception
    {
       BookDto bookDto = TestDataUtil.createTestBookDtoA(null);
       String bookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/"+bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatUpdateBookReturnsHttpStatus200Ok() throws Exception
    {
        BookEntity bookEntity = TestDataUtil.createTestBookA(null);
        BookEntity savedBookEntity = bookService.createUpdateBook(bookEntity.getIsbn(), bookEntity);

        BookDto bookDto = TestDataUtil.createTestBookDtoA(null);
        bookDto.setIsbn(savedBookEntity.getIsbn());
        String bookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatCreateBookReturnsCreatedBook() throws Exception
    {
        BookDto bookDto = TestDataUtil.createTestBookDtoA(null);
        String bookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/"+bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value("978-1-2345-6789-0")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value("The Shadow in the Attic")
        );
    }

    @Test
    public void testThatUpdateBookReturnsUpdatedBook() throws Exception
    {
        BookEntity bookEntity = TestDataUtil.createTestBookA(null);
        BookEntity savedBookEntity = bookService.createUpdateBook(bookEntity.getIsbn(),bookEntity);

        BookDto bookDto = TestDataUtil.createTestBookDtoA(null);
        bookDto.setIsbn(savedBookEntity.getIsbn());
        bookDto.setTitle("UPDATED");
        String bookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/"+bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value("978-1-2345-6789-0")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value("UPDATED")
        );
    }

    @Test
    public void testThatListBooksReturnsHttpStatus200() throws Exception
    {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatListBooksReturnsBook() throws Exception
    {
        BookEntity testBookEntityA = TestDataUtil.createTestBookA(null);
        bookService.createUpdateBook(testBookEntityA.getIsbn(), testBookEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].isbn").value(testBookEntityA.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].title").value("The Shadow in the Attic")
        );
    }

    @Test
    public void testThatGetBooksReturnsHttpStatus200WhenBookExists() throws Exception
    {
        BookEntity book = TestDataUtil.createTestBookA(null);
        bookService.createUpdateBook(book.getIsbn(), book);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/"+book.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatGetBooksReturnsHttpStatus404WhenNoBookExists() throws Exception
    {
        BookEntity book = TestDataUtil.createTestBookA(null);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/"+book.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatPartialUpdateBookReturnHttpStatus200Ok()throws Exception
    {
        BookEntity book = TestDataUtil.createTestBookA(null);
        bookService.createUpdateBook(book.getIsbn(), book);

        BookDto bookDto = TestDataUtil.createTestBookDtoA(null);
        bookDto.setTitle("UPDATED");
        String bookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/books/"+book.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatPartialUpdateBookReturnUpdatedBook()throws Exception
    {
        BookEntity book = TestDataUtil.createTestBookA(null);
        bookService.createUpdateBook(book.getIsbn(), book);

        BookDto bookDto = TestDataUtil.createTestBookDtoA(null);
        bookDto.setTitle("UPDATED");
        String bookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/books/"+book.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(book.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value("UPDATED")
        );
    }

    @Test
    public void testThatDeleteNonExistingBookReturnHttpStatus204NoContent() throws Exception
    {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/books/99")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testThatDeleteExistingBookReturnHttpStatus204NoContent() throws Exception
    {
        BookEntity book = TestDataUtil.createTestBookA(null);
        bookService.createUpdateBook(book.getIsbn(), book);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/books/"+ book.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
*/
