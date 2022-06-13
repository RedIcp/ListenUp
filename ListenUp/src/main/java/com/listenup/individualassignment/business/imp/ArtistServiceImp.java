package com.listenup.individualassignment.business.imp;

import java.util.List;

import com.listenup.individualassignment.business.exception.InvalidArtistException;
import com.listenup.individualassignment.business.imp.dtoconverter.ArtistDTOConverter;
import com.listenup.individualassignment.dto.ArtistAlbumListDTO;
import com.listenup.individualassignment.dto.ArtistSongListDTO;
import com.listenup.individualassignment.dto.createdto.CreateArtistRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateArtistResponseDTO;
import com.listenup.individualassignment.dto.vieweditdto.ArtistDTO;
import com.listenup.individualassignment.business.ArtistService;
import com.listenup.individualassignment.entity.Artist;
import com.listenup.individualassignment.repository.ArtistRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Primary;

@Service
@Primary
@RequiredArgsConstructor
public class ArtistServiceImp implements ArtistService {
    private final ArtistRepository db;

    @Async
    @Override
    public CreateArtistResponseDTO addArtist(CreateArtistRequestDTO artist){
        Artist savedArtist = db.save(ArtistDTOConverter.convertToModelForCreate(artist));

        return CreateArtistResponseDTO.builder()
                .artistID(savedArtist.getId())
                .build();
    }

    @Async
    @Override
    public List<ArtistDTO> getArtists(){
        return ArtistDTOConverter.convertToDTOList(db.findAll());
    }

    @Async
    @Override
    public ArtistSongListDTO getArtistSongs(long id){
        return ArtistDTOConverter.convertToDTOForSong(db.getById(id));
    }

    @Async
    @Override
    public ArtistAlbumListDTO getArtistAlbums(long id){
        return ArtistDTOConverter.convertToDTOForAlbum(db.getById(id));
    }

    @Async
    @Override
    public ArtistDTO editArtist(ArtistDTO artist){
        if(!db.existsById(artist.getId())){
            throw new InvalidArtistException("INVALID_ARTIST");
        }
        db.save(ArtistDTOConverter.convertToModelForUpdate(artist));
        return artist;
    }

    @Async
    @Override
    public boolean deleteArtist(long id){
        boolean result = false;
        if(db.existsById(id)){
            db.deleteById(id);
            result = true;
        }
        return result;
    }
}
