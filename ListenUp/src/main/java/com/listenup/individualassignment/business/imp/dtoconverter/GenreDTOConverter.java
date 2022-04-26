package com.listenup.individualassignment.business.imp.dtoconverter;

import java.util.List;
import java.util.ArrayList;

import com.listenup.individualassignment.model.Genre;
import com.listenup.individualassignment.dto.GenreSongListDTO;
import com.listenup.individualassignment.dto.createupdate.GenreDTO;

public class GenreDTOConverter {
    private GenreDTOConverter(){

    }
    public static GenreDTO convertToDTO(Genre genre){
        return GenreDTO.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }
    public static Genre convertToModel(GenreDTO genre){
        return Genre.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }
    public static GenreSongListDTO convertToDTOForSong(Genre genre){
        return GenreSongListDTO.builder()
                .id(genre.getId())
                .songs(SongDTOConverter.convertToDTOList(genre.getSongs()))
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
