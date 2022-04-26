package com.listenup.individualassignment.business.imp.dtoconverter;

import java.util.List;
import java.util.ArrayList;

import com.listenup.individualassignment.model.Song;
import com.listenup.individualassignment.model.AlbumSong;
import com.listenup.individualassignment.model.SingleSong;
import com.listenup.individualassignment.dto.createupdate.SongDTO;
import com.listenup.individualassignment.dto.createupdate.AlbumSongDTO;

public class SongDTOConverter {
    private SongDTOConverter(){

    }
    public static SongDTO convertToDTO(Song song){
        return SongDTO.builder()
                .id(song.getId())
                .name(song.getName())
                .artist(ArtistDTOConverter.convertToDTO(song.getArtist()))
                .genre(GenreDTOConverter.convertToDTO(song.getGenre()))
                .releasedDate(song.getReleasedDate())
                .uploadedDate(song.getUploadedDate())
                .build();
    }
    public static AlbumSongDTO convertToDTOForAlbum(AlbumSong song){
        return AlbumSongDTO.builder()
                .id(song.getId())
                .name(song.getName())
                .album(AlbumDTOConverter.convertToDTO(song.getAlbum()))
                .genre(GenreDTOConverter.convertToDTO(song.getGenre()))
                .build();
    }
    public static Song convertToModel(SongDTO song){
        return new SingleSong(song.getId(), song.getName(), ArtistDTOConverter.convertToModel(song.getArtist()), GenreDTOConverter.convertToModel(song.getGenre()), song.getReleasedDate(), song.getUploadedDate());
    }
    public static List<SongDTO> convertToDTOList(List<Song> songs){
        List<SongDTO> dtoList = new ArrayList<>();
        for (Song song : songs){
            dtoList.add(convertToDTO(song));
        }
        return dtoList;
    }
    public static List<AlbumSongDTO> convertToDTOListForAlbum(List<AlbumSong> songs){
        List<AlbumSongDTO> dtoList = new ArrayList<>();
        for (AlbumSong song : songs){
            dtoList.add(convertToDTOForAlbum(song));
        }
        return dtoList;
    }
}
