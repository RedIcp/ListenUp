package com.listenup.individualassignment.business.imp.dtoconverter;

import java.util.List;
import java.util.ArrayList;

import com.listenup.individualassignment.dto.createdto.CreateAlbumSongRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateSingleSongRequestDTO;
import com.listenup.individualassignment.model.Song;
import com.listenup.individualassignment.model.AlbumSong;
import com.listenup.individualassignment.model.SingleSong;
import com.listenup.individualassignment.dto.vieweditdto.SingleSongDTO;
import com.listenup.individualassignment.dto.vieweditdto.AlbumSongDTO;

public class SongDTOConverter {
    private SongDTOConverter(){

    }
    public static SingleSongDTO convertToSingleSongDTO(Song song){
        return SingleSongDTO.builder()
                .id(song.getId())
                .name(song.getName())
                .artist(ArtistDTOConverter.convertToDTO(song.getArtist()))
                .genre(GenreDTOConverter.convertToDTO(song.getGenre()))
                .releasedDate(song.getReleasedDate())
                .uploadedDate(song.getUploadedDate())
                .build();
    }
    public static AlbumSongDTO convertToAlbumSongDTO(AlbumSong song){
        return AlbumSongDTO.builder()
                .id(song.getId())
                .name(song.getName())
                .album(AlbumDTOConverter.convertToDTO(song.getAlbum()))
                .genre(GenreDTOConverter.convertToDTO(song.getGenre()))
                .build();
    }
    public static SingleSong convertToSingleSongModelForUpdate(SingleSongDTO song){
        return new SingleSong(song.getId(), song.getName(), ArtistDTOConverter.convertToModelForUpdate(song.getArtist()), GenreDTOConverter.convertToModelForUpdate(song.getGenre()), song.getReleasedDate(), song.getUploadedDate());
    }
    public static SingleSong convertToSingleSongModelForCreate(CreateSingleSongRequestDTO song){
        return new SingleSong(song.getName(), ArtistDTOConverter.convertToModelForUpdate(song.getArtist()), GenreDTOConverter.convertToModelForUpdate(song.getGenre()), song.getReleasedDate(), song.getUploadedDate());
    }
    public static AlbumSong convertToAlbumSongModelForUpdate(AlbumSongDTO song){
        return new AlbumSong(song.getId(), song.getName(), GenreDTOConverter.convertToModelForUpdate(song.getGenre()), AlbumDTOConverter.convertToModelForUpdate(song.getAlbum()));
    }
    public static AlbumSong convertToAlbumSongModelForCreate(CreateAlbumSongRequestDTO song){
        return new AlbumSong(song.getName(), GenreDTOConverter.convertToModelForUpdate(song.getGenre()), AlbumDTOConverter.convertToModelForUpdate(song.getAlbum()));
    }
    public static List<SingleSongDTO> convertToSingleSongDTOList(List<Song> songs){
        List<SingleSongDTO> dtoList = new ArrayList<>();
        for (Song song : songs){
            dtoList.add(convertToSingleSongDTO(song));
        }
        return dtoList;
    }
    public static List<AlbumSongDTO> convertToAlbumSongDTOList(List<AlbumSong> songs){
        List<AlbumSongDTO> dtoList = new ArrayList<>();
        for (AlbumSong song : songs){
            dtoList.add(convertToAlbumSongDTO(song));
        }
        return dtoList;
    }
    public static List<Song> convertToSingleSongModelList(List<SingleSongDTO> songs){
        List<Song> songList = new ArrayList<>();
        for (SingleSongDTO songDTO: songs){
            songList.add(convertToSingleSongModelForUpdate(songDTO));
        }
        return songList;
    }
    public static List<Song> convertToAlbumSongModelList(List<AlbumSongDTO> songs){
        List<Song> songList = new ArrayList<>();
        for (AlbumSongDTO songDTO: songs){
            songList.add(convertToAlbumSongModelForUpdate(songDTO));
        }
        return songList;
    }
}
