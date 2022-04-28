package com.listenup.individualassignment.business.imp.dtoconverter;

import java.util.List;
import java.util.ArrayList;

import com.listenup.individualassignment.model.Song;
import com.listenup.individualassignment.model.AlbumSong;
import com.listenup.individualassignment.model.SingleSong;
import com.listenup.individualassignment.dto.createupdate.SingleSongDTO;
import com.listenup.individualassignment.dto.createupdate.AlbumSongDTO;

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
    public static SingleSong convertToSingleSongModel(SingleSongDTO song){
        return new SingleSong(song.getId(), song.getName(), ArtistDTOConverter.convertToModel(song.getArtist()), GenreDTOConverter.convertToModel(song.getGenre()), song.getReleasedDate(), song.getUploadedDate());
    }
    public static AlbumSong convertToAlbumSongModel(AlbumSongDTO song){
        return new AlbumSong(song.getId(), song.getName(), GenreDTOConverter.convertToModel(song.getGenre()), AlbumDTOConverter.convertToModel(song.getAlbum()));
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
            songList.add(convertToSingleSongModel(songDTO));
        }
        return songList;
    }
    public static List<Song> convertToAlbumSongModelList(List<AlbumSongDTO> songs){
        List<Song> songList = new ArrayList<>();
        for (AlbumSongDTO songDTO: songs){
            songList.add(convertToAlbumSongModel(songDTO));
        }
        return songList;
    }
}
