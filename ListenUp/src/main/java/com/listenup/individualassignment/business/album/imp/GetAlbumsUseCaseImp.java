package com.listenup.individualassignment.business.album.imp;

import com.listenup.individualassignment.business.album.GetAlbumsUseCase;
import com.listenup.individualassignment.business.dtoconverter.AlbumDTOConverter;
import com.listenup.individualassignment.dto.vieweditdto.AlbumDTO;
import com.listenup.individualassignment.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Primary
@Transactional
@RequiredArgsConstructor
public class GetAlbumsUseCaseImp implements GetAlbumsUseCase {
    private final AlbumRepository db;

    @Override
    public List<AlbumDTO> getAlbums(){
        return AlbumDTOConverter.convertToDTOList(db.findAll());
    }
}
