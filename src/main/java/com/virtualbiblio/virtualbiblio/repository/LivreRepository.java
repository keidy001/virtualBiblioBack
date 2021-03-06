package com.virtualbiblio.virtualbiblio.repository;

import com.virtualbiblio.virtualbiblio.model.Category;
import com.virtualbiblio.virtualbiblio.model.Format;
import com.virtualbiblio.virtualbiblio.model.Librairy;
import com.virtualbiblio.virtualbiblio.model.Livre;
import org.hibernate.sql.Delete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface LivreRepository extends JpaRepository<Livre, Long> {
    //(value = "SELECT types FROM Livre types WHERE types.format=:types ")
    //Optional<Livre> findByFormat(@Param("types") String types);
    Collection<Livre> findByFormat(Format format);
    List<Livre> findByDeleted(Boolean deleted);
    List<Livre> findByFormatAndDeleted(Format format,Boolean deleted);
    List<Livre> findByFormatAndCategoryAndDeleted(Format format, Category category,Boolean deleted);
    List<Livre> findByLibrairyAndDeleted(Librairy librairy, Boolean deleted);
}
