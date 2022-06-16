package com.listenup.individualassignment.business.imp;

import java.util.ArrayList;
import java.util.List;

import com.listenup.individualassignment.business.exception.InvalidSongException;
import com.listenup.individualassignment.business.imp.dtoconverter.SongDTOConverter;
import com.listenup.individualassignment.dto.AccessTokenDTO;
import com.listenup.individualassignment.dto.createdto.CreateAlbumSongRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateAlbumSongResponseDTO;
import com.listenup.individualassignment.dto.createdto.CreateSingleSongRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateSingleSongResponseDTO;
import com.listenup.individualassignment.dto.vieweditdto.SingleSongDTO;
import com.listenup.individualassignment.business.SongService;
import com.listenup.individualassignment.entity.Customer;
import com.listenup.individualassignment.entity.Song;
import com.listenup.individualassignment.repository.SongRepository;

import com.listenup.individualassignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Primary;

@Service
@Primary
@RequiredArgsConstructor
public class SongServiceImp implements SongService {
    private final SongRepository db;
    private final UserRepository userDB;
    private final AccessTokenDTO requestAccessToken;

    @Override
    public CreateSingleSongResponseDTO addSingleSong(CreateSingleSongRequestDTO song){
        Song savedSong = db.save(SongDTOConverter.convertToSingleSongModelForCreate(song));

        return CreateSingleSongResponseDTO.builder()
                .singleSongID(savedSong.getId())
                .build();
    }

    @Override
    public CreateAlbumSongResponseDTO addAlbumSong(CreateAlbumSongRequestDTO song){
        Song savedSong = db.save(SongDTOConverter.convertToAlbumSongModelForCreate(song));
        return CreateAlbumSongResponseDTO.builder()
                .albumSongID(savedSong.getId())
                .build();
    }

    @Override
    public List<SingleSongDTO> getSongs(){
        List<SingleSongDTO> dtoList = new ArrayList<>();
        for (Song song : db.findAll()){
            SingleSongDTO dto = SongDTOConverter.convertToSingleSongDTO(song);
            dto.setLiked(isLiked(song.getId()));
            dtoList.add(dto);
        }
        return dtoList;
    }

    public boolean isLiked(long songID){
        Customer customer = (Customer) userDB.getById(requestAccessToken.getUserID());
        if(userDB.existsById(requestAccessToken.getUserID())){
            for(Song song : customer.getLikedSongs()){
                if(song.getId() == songID){
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override
    public SingleSongDTO getSong(long id){
        return SongDTOConverter.convertToSingleSongDTO(db.getById(id));
    }

    @Override
    public SingleSongDTO editSong(SingleSongDTO song){
        if(!db.existsById(song.getId())){
            throw new InvalidSongException("INVALID_ID");
        }
        db.save(SongDTOConverter.convertToSingleSongModelForUpdate(song));
        return song;
    }

    @Override
    public boolean deleteSong(long id){
        boolean result = false;
        if(db.existsById(id)){
            db.deleteById(id);
            result = true;
        }
        return result;
    }
}
