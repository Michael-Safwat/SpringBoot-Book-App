package com.michael.BookStore.repositories;

import com.michael.BookStore.domain.entities.AuthorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, Long>
{
    Iterable<AuthorEntity> ageLessThan(int age);

    @Query("SELECT a FROM AuthorEntity a where a.age> ?1")
    Iterable<AuthorEntity> findAuthorWithAgeMoreThan(int age);
}
