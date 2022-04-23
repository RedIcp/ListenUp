package com.listenup.individualassignment.business.imp;

import java.util.List;
import java.util.ArrayList;

import com.listenup.individualassignment.model.Album;
import com.listenup.individualassignment.dto.AlbumSongListDTO;
import com.listenup.individualassignment.business.AlbumService;
import com.listenup.individualassignment.dto.CreateUpdate.AlbumDTO;
import com.listenup.individualassignment.repository.AlbumRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Primary;

@Service
@Primary
@RequiredArgsConstructor
public class AlbumServiceImp implements AlbumService {
    private final AlbumRepository db;

    public Album albumDTOConvertor(AlbumDTO dto) {
        return Album.builder()
                .id(dto.getId())
                .name(dto.getName())
                .artist(dto.getArtist())
                .releasedDate(dto.getReleasedDate())
                .uploadedDate(dto.getUploadedDate())
                .build();
    }
    public AlbumSongListDTO albumObjConvertor(Album album) {
        return AlbumSongListDTO.builder()
                .id(album.getId())
                .songs(album.getAlbumSongs())
                .build();
    }
    public List<AlbumDTO> getAlbumDTOs() {
        List<AlbumDTO> dtoList = new ArrayList<>();
        for (Album album : getAlbums()){
            dtoList.add(new AlbumDTO(album.getId(), album.getName(), album.getArtist(), album.getReleasedDate(), album.getUploadedDate()));
        }
        return dtoList;
    }

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
