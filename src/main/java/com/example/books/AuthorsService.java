package com.example.books;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AuthorsService implements IAuthorServices {
    private static List<Author> authorsRepo = new ArrayList<>();
    
    static {
        authorsRepo.add(new Author(1, "Henryk",  "Sienkiewicz"));
        authorsRepo.add(new Author(2, "Stanisław", "Reymont"));
        authorsRepo.add(new Author(3, "Adam", "Mickiewicz"));
    }

    @Override
    public Collection<Author> getAuthors() {
        return authorsRepo;
    }

    @Override
    public Author getAuthor(int id) {
        return authorsRepo.stream()
                .filter(b -> b.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public Boolean addAuthor(Author newAuthor) {
        if(getAuthor(newAuthor.getId()) == null){
            authorsRepo.add(newAuthor);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateAuthor(Author updatedAuthor) {
        Author oldAuthor = getAuthor(updatedAuthor.getId());
        if(oldAuthor == null){
            return false;
        }
        oldAuthor.setFirstName(updatedAuthor.getFirstName());
        oldAuthor.setLastName(updatedAuthor.getLastName());
        return true;
    }

    @Override
    public Boolean deleteAuthor(int id) {
        Author authorToDelete = getAuthor(id);
        if(authorToDelete == null){
            return false;
        }
        authorsRepo.remove(authorToDelete);
        return true;
    }
}
