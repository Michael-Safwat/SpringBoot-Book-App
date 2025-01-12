/*
package com.michael.BookStore.repositories;

import com.michael.BookStore.TestDataUtil;
import com.michael.BookStore.domain.entities.AuthorEntity;
import com.michael.BookStore.domain.entities.BookEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookEntityRepositoryIntegrationTests
{
    private  final BookRepository underTest;

    @Autowired
    public BookEntityRepositoryIntegrationTests(BookRepository underTest)
    {
        this.underTest=underTest;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled()
    {
        AuthorEntity authorEntity =TestDataUtil.createTestAuthorA();
        BookEntity bookEntity = TestDataUtil.createTestBookA(authorEntity);
        underTest.save(bookEntity);
        Optional<BookEntity> result = underTest.findById(bookEntity.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookEntity);
    }

    @Test
    public void TestThatMultipleBooksCanBeCreatedAndRecalled()
    {
        AuthorEntity authorEntity =TestDataUtil.createTestAuthorA();

        BookEntity bookEntityA = TestDataUtil.createTestBookA(authorEntity);
        underTest.save(bookEntityA);

        BookEntity bookEntityB = TestDataUtil.createTestBookB(authorEntity);
        underTest.save(bookEntityB);

        BookEntity bookEntityC = TestDataUtil.createTestBookC(authorEntity);
        underTest.save(bookEntityC);

        Iterable<BookEntity> result = underTest.findAll();
        assertThat(result)
                .hasSize(3)
                .containsExactly(bookEntityA, bookEntityB, bookEntityC);
    }

    @Test
    public void testThatBookCanBeUpdated()
    {
        AuthorEntity authorEntity =TestDataUtil.createTestAuthorA();

        BookEntity bookEntityA = TestDataUtil.createTestBookA(authorEntity);
        underTest.save(bookEntityA);

        bookEntityA.setTitle("UPDATED");
        underTest.save(bookEntityA);

        Optional<BookEntity> result = underTest.findById(bookEntityA.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookEntityA);
    }

    @Test
    public void testThatBookCanBeDeleted()
    {
        AuthorEntity authorEntity =TestDataUtil.createTestAuthorA();

        BookEntity bookEntityA = TestDataUtil.createTestBookA(authorEntity);
        underTest.save(bookEntityA);

        underTest.deleteById(bookEntityA.getIsbn());
        Optional<BookEntity> result = underTest.findById(bookEntityA.getIsbn());
        assertThat(result).isEmpty();
    }
}
*/
