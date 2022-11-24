package com.listenup.individualassignment.business.dtoconverter;

import com.listenup.individualassignment.business.song.SongIsLiked;
import com.listenup.individualassignment.dto.vieweditdto.SingleSongDTO;
import com.listenup.individualassignment.repository.entity.Song;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SongListDTOConverter {
    private SongIsLiked songIsLiked;

    public SingleSongDTO convertToSingleSongDTO(Song song){
        return SingleSongDTO.builder()
                .id(song.getId())
                .name(song.getName())
                .artist(ArtistDTOConverter.convertToDTO(song.getArtist()))
                .genre(GenreDTOConverter.convertToDTO(song.getGenre()))
                .releasedDate(song.getReleasedDate())
                .uploadedDate(song.getUploadedDate())
                .isLiked(songIsLiked.isLiked(song.getId()))
                .build();
    }

    public List<SingleSongDTO> convertToSingleSongDTOList(List<Song> songs){
        List<SingleSongDTO> dtoList = new ArrayList<>();
        for (Song song : songs){
            dtoList.add(convertToSingleSongDTO(song));
        }
        return dtoList;
    }
}
