package com.listenup.individualassignment.business.dtoconverter;

import java.util.List;
import java.util.ArrayList;

import com.listenup.individualassignment.dto.createdto.CreateGenreRequestDTO;
import com.listenup.individualassignment.repository.entity.Genre;
import com.listenup.individualassignment.dto.GenreSongListDTO;
import com.listenup.individualassignment.dto.vieweditdto.GenreDTO;

public class GenreDTOConverter {
    private GenreDTOConverter(){

    }
    public static GenreDTO convertToDTO(Genre genre){
        return GenreDTO.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }
    public static Genre convertToModelForUpdate(GenreDTO genre){
        return Genre.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }
    public static Genre convertToModelForCreate(CreateGenreRequestDTO genre){
        return Genre.builder()
                .name(genre.getName())
                .build();
    }
    public static GenreSongListDTO convertToDTOForSong(Genre genre){
        return GenreSongListDTO.builder()
                .id(genre.getId())
                .name(genre.getName())
                .songs(SongDTOConverter.convertToSingleSongDTOList(genre.getSongs()))
                .build();
    }
    public static List<GenreDTO> convertToDTOList(List<Genre> genres){
        List<GenreDTO> dtoList = new ArrayList<>();
        for (Genre genre : genres){
            dtoList.add(convertToDTO(genre));
        }
        return dtoList;
    }
}
