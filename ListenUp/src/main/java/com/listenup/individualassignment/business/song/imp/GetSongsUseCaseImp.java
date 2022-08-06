package com.listenup.individualassignment.business.song.imp;

import com.listenup.individualassignment.business.dtoconverter.SongDTOConverter;
import com.listenup.individualassignment.business.song.GetSongsUseCase;
import com.listenup.individualassignment.dto.vieweditdto.SingleSongDTO;
import com.listenup.individualassignment.repository.entity.Song;
import com.listenup.individualassignment.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Primary
@Transactional
@RequiredArgsConstructor
public class GetSongsUseCaseImp implements GetSongsUseCase {
    private final SongRepository db;

    @Override
    public List<SingleSongDTO> getSongs(){
        List<SingleSongDTO> dtoList = new ArrayList<>();
        for (Song song : db.findAll()){
            SingleSongDTO dto = SongDTOConverter.convertToSingleSongDTO(song);
            dto.setIsLiked(db.songLiked(song.getId(),1));
            dtoList.add(dto);
        }
        return dtoList;
    }
}
