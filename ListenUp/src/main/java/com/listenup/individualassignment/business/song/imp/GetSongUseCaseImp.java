package com.listenup.individualassignment.business.song.imp;

import com.listenup.individualassignment.business.dtoconverter.SongDTOConverter;
import com.listenup.individualassignment.business.dtoconverter.SongListDTOConverter;
import com.listenup.individualassignment.business.song.GetSongUseCase;
import com.listenup.individualassignment.dto.vieweditdto.SingleSongDTO;
import com.listenup.individualassignment.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Primary
@Transactional
@RequiredArgsConstructor
public class GetSongUseCaseImp implements GetSongUseCase {
    private final SongRepository db;
    private final SongListDTOConverter songList;

    @Override
    public SingleSongDTO getSong(long id){
        return songList.convertToSingleSongDTO(db.getById(id));
    }
}
