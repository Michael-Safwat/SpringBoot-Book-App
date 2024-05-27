package com.michael.BookStore;

import com.michael.BookStore.domain.dto.AuthorDto;
import com.michael.BookStore.domain.dto.BookDto;
import com.michael.BookStore.domain.entities.AuthorEntity;
import com.michael.BookStore.domain.entities.BookEntity;

public final class TestDataUtil
{
    private TestDataUtil(){}

    public static AuthorDto createTestAuthorDtoA()
    {
        return AuthorDto.builder()
                .id(1L)
                .name("Abigail Rose")
                .age(80)
                .build();
    }

    public static AuthorEntity createTestAuthorA()
    {
        return AuthorEntity.builder()
                .id(1L)
                .name("Abigail Rose")
                .age(80)
                .build();
    }

    public static AuthorEntity createTestAuthorB()
    {
        return AuthorEntity.builder()
                .id(2L)
                .name("Thomas Cronin")
                .age(44)
                .build();
    }

    public static AuthorEntity createTestAuthorC()
    {
        return AuthorEntity.builder()
                .id(3L)
                .name("Jesse A Casey")
                .age(24)
                .build();
    }

    public static BookDto createTestBookDtoA(final AuthorDto authorDto)
    {
        return BookDto.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .authorEntity(authorDto)
                .build();
    }

    public static BookEntity createTestBookA(final AuthorEntity authorEntity)
    {
        return BookEntity.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .authorEntity(authorEntity)
                .build();
    }

    public static BookEntity createTestBookB(final AuthorEntity authorEntity)
    {
        return BookEntity.builder()
                .isbn("978-1-2345-6789-1")
                .title("Beyond the Horizon")
                .authorEntity(authorEntity)
                .build();
    }

    public static BookEntity createTestBookC(final AuthorEntity authorEntity)
    {
        return BookEntity.builder()
                .isbn("978-1-2345-6789-2")
                .title("The Shadow in the Attic")
                .authorEntity(authorEntity)
                .build();
    }
}
