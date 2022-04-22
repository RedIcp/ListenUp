package com.listenup.individualassignment.business.imp;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Primary;
import com.listenup.individualassignment.model.Artist;
import com.listenup.individualassignment.business.ArtistService;
import com.listenup.individualassignment.dto.CreateUpdate.ArtistDTO;
import com.listenup.individualassignment.repository.ArtistRepository;

@Service
@Primary
@RequiredArgsConstructor
public class ArtistServiceImp implements ArtistService {
    private final ArtistRepository db;

    public Artist artistDTOConvertor(ArtistDTO dto) {
        return Artist.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

    public boolean addArtist(Artist artist){
        boolean result = false;
        if(!artistExit(artist.getId())){
            getArtists().add(artist);
            db.save(artist);
            result = true;
        }
        return result;
    }

    public List<Artist> getArtists(){
        return db.findAll();
    }

    public boolean editArtist(Artist artist){
        boolean result = false;
        Artist old = getArtist(artist.getId());
        if(old != null){
            old.setName(artist.getName());
            db.save(artist);
            result =  true;
        }
        return result;
    }

    public boolean deleteArtist(long id){
        boolean result = false;
        if(artistExit(id)){
            getArtists().remove(getArtist(id));
            db.delete(getArtist(id));
            result = true;
        }
        return result;
    }

    public Artist getArtist(long id){
        for(Artist artist : getArtists()){
            if(artist.getId() == id){
                return artist;
            }
        }
        return null;
    }
    private boolean artistExit(long id){
        boolean result = true;
        if(getArtist(id) == null){
            result = false;
        }
        return result;
    }
}
