package com.virtualbiblio.virtualbiblio.service;

import com.virtualbiblio.virtualbiblio.model.Category;
import com.virtualbiblio.virtualbiblio.model.Format;
import com.virtualbiblio.virtualbiblio.model.Librairy;
import com.virtualbiblio.virtualbiblio.model.Livre;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public interface LivreService {
    Livre ajouter(Livre livre);
    Livre afficher(Long id);
    List<Livre> lister();
    Livre update(Livre livre,Long id);
    Void delete(Long id);
    Livre disable(Long id);
    Livre restore(Long id);
    Collection<Livre> findByState(Boolean state);
    Collection<Livre> Format(Format format);
    List<Livre> findByFormatAndDeleted(Format format, Boolean state);
    void  uplodFile(MultipartFile file) throws IOException;
    byte[] getPhoto(Long id) throws IllegalStateException, IOException;
    byte[] getPdf(Long id) throws IllegalStateException, IOException;
    List<Livre> listByDeleted(Boolean deleted);
    List<Livre> listByFormatAndCategoryAndDeleted(Format format, Category category,Boolean deleted);
    List<Livre> livreByLibrairy(Librairy librairy, Boolean deleted);

}
