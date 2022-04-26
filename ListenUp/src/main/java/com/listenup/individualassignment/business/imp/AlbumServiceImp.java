package com.listenup.individualassignment.business.imp;

import java.util.List;

import com.listenup.individualassignment.model.Album;
import com.listenup.individualassignment.business.AlbumService;
import com.listenup.individualassignment.repository.AlbumRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Primary;

@Service
@Primary
@RequiredArgsConstructor
public class AlbumServiceImp implements AlbumService {
    private final AlbumRepository db;

    public boolean addAlbum(Album album){
        boolean result = false;
        if(!albumExist(album.getId())){
            getAlbums().add(album);
            db.save(album);
            result = true;
        }
        return result;
    }

    public List<Album> getAlbums(){
        return db.findAll();
    }

    public boolean editAlbum(Album album){
        boolean result = false;
        Album old = getAlbum(album.getId());
        if(old != null){
            old.setName(album.getName());
            old.setReleasedDate(album.getReleasedDate());
            old.setUploadedDate(album.getUploadedDate());
            old.setArtist(album.getArtist());
            db.save(album);
            result = true;
        }
        return result;
    }

    public boolean deleteAlbum(long id){
        boolean result = false;
        if(albumExist(id)){
            getAlbums().remove(getAlbum(id));
            db.delete(getAlbum(id));
            result = true;
        }
        return result;
    }

    public Album getAlbum(long id){
        for(Album album: getAlbums()){
            if(album.getId()==id){
                return album;
            }
        }
        return null;
    }
    private boolean albumExist(long id){
        boolean result = true;
        if(getAlbum(id)==null){
            result = false;
        }
        return result;
    }

}
