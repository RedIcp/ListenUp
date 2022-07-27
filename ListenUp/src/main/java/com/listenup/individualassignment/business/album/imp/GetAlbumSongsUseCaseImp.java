package com.listenup.individualassignment.business.album.imp;

import com.listenup.individualassignment.business.album.GetAlbumSongsUseCase;
import com.listenup.individualassignment.business.dtoconverter.AlbumDTOConverter;
import com.listenup.individualassignment.dto.AlbumSongListDTO;
import com.listenup.individualassignment.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Primary
@Transactional
@RequiredArgsConstructor
public class GetAlbumSongsUseCaseImp implements GetAlbumSongsUseCase {
    private final AlbumRepository db;

    @Override
    public AlbumSongListDTO getAlbumSongs(long id){
        return AlbumDTOConverter.convertToDTOForSong(db.getById(id));
    }
}
