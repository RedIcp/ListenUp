package com.listenup.individualassignment.dto;

import java.util.List;

import com.listenup.individualassignment.dto.vieweditdto.SingleSongDTO;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerLikedSongListDTO {
    private long id;
    private List<SingleSongDTO> likedSongs;
}
