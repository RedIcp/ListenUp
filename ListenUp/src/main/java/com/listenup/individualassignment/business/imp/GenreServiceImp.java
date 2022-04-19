package com.listenup.individualassignment.business.imp;

import com.listenup.individualassignment.business.GenreService;
import com.listenup.individualassignment.dto.GenreDTO;
import com.listenup.individualassignment.model.Genre;
import com.listenup.individualassignment.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class GenreServiceImp implements GenreService {
    private final GenreRepository db;

    public Genre genreDTOConvertor(GenreDTO dto) {
        return Genre.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

    public boolean addGenre(Genre genre){
        boolean result = false;
        if(!genreExist(genre.getId())){
            getGenres().add(genre);
            db.save(genre);
            result = true;
        }
        return result;
    }

    public List<Genre> getGenres(){
        return  db.findAll();
    }

    public boolean editGenre(Genre genre){
        boolean result = false;
        Genre old = getGenre(genre.getId());
        if(old != null){
            old.setName(genre.getName());
            db.save(genre);
            result = true;
        }
        return result;
    }

    public boolean deleteGenre(long id){
        boolean result = false;
        if(genreExist(id)){
            getGenres().remove(getGenre(id));
            db.delete(getGenre(id));
            result = true;
        }
        return result;
    }

    public Genre getGenre(long id){
        for(Genre genre : getGenres()){
            if(genre.getId() == id){
                return genre;
            }
        }
        return null;
    }
    public boolean genreExist(long id){
        boolean result = true;
        if(getGenre(id) == null){
            result = false;
        }
        return result;
    }
}
