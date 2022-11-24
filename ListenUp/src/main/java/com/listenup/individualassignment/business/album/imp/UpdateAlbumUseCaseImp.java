package com.listenup.individualassignment.business.album.imp;

import com.listenup.individualassignment.business.album.UpdateAlbumUseCase;
import com.listenup.individualassignment.business.exception.InvalidAlbumException;
import com.listenup.individualassignment.business.dtoconverter.AlbumDTOConverter;
import com.listenup.individualassignment.dto.vieweditdto.AlbumDTO;
import com.listenup.individualassignment.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Primary
@Transactional
@RequiredArgsConstructor
public class UpdateAlbumUseCaseImp implements UpdateAlbumUseCase {
    private final AlbumRepository db;

    @Override
    public AlbumDTO editAlbum(AlbumDTO album){
        if(!db.existsById(album.getId())){
            throw new InvalidAlbumException("INVALID_ALBUM");
        }
        db.save(AlbumDTOConverter.convertToModelForUpdate(album));
        return album;
    }
}
