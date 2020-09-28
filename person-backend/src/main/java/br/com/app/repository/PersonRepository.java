package br.com.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.app.data.model.PersonEntity;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    @Modifying
    @Query("UPDATE PersonEntity p SET p.enabled = false WHERE p.id = :id")
    void disablePerson(@Param("id") Long id);

    @Query("select p from PersonEntity p WHERE p.firstName LIKE LOWER(CONCAT('%', :firstName, '%'))")
    Page<PersonEntity> findPersonByName(@Param("firstName") String firstName, Pageable pageable);
}
