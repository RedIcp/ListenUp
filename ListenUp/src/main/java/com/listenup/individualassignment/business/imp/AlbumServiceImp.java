package com.listenup.individualassignment.business.imp;

import java.util.List;

import com.listenup.individualassignment.business.exception.InvalidAlbumException;
import com.listenup.individualassignment.business.imp.dtoconverter.AlbumDTOConverter;
import com.listenup.individualassignment.dto.AlbumSongListDTO;
import com.listenup.individualassignment.dto.createdto.CreateAlbumRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateAlbumResponseDTO;
import com.listenup.individualassignment.dto.vieweditdto.AlbumDTO;
import com.listenup.individualassignment.business.AlbumService;
import com.listenup.individualassignment.model.Album;
import com.listenup.individualassignment.repository.AlbumRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Primary;

@Service
@Primary
@RequiredArgsConstructor
public class AlbumServiceImp implements AlbumService {
    private final AlbumRepository db;

    public CreateAlbumResponseDTO addAlbum(CreateAlbumRequestDTO album){
        Album savedAlbum = db.save(AlbumDTOConverter.convertToModelForCreate(album));

        return CreateAlbumResponseDTO.builder()
                .albumID(savedAlbum.getId())
                .build();
    }

    public List<AlbumDTO> getAlbums(){
        return AlbumDTOConverter.convertToDTOList(db.findAll());
    }
    public AlbumSongListDTO getAlbumSongs(long id){
        return AlbumDTOConverter.convertToDTOForSong(db.getById(id));
    }

    public AlbumDTO editAlbum(AlbumDTO album){
        if(!db.existsById(album.getId())){
            throw new InvalidAlbumException("INVALID_ALBUM");
        }
        db.save(AlbumDTOConverter.convertToModelForUpdate(album));
        return album;
    }

    public boolean deleteAlbum(long id){
        boolean result = false;
        if(db.existsById(id)){
            db.deleteById(id);
            result = true;
        }
        return result;
    }
}
