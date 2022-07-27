package com.listenup.individualassignment.business.playlist.imp;

import com.listenup.individualassignment.business.dtoconverter.PlaylistDTOConverter;
import com.listenup.individualassignment.business.playlist.GetPlaylistsUseCase;
import com.listenup.individualassignment.dto.vieweditdto.PlaylistDTO;
import com.listenup.individualassignment.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class GetPlaylistsUseCaseImp implements GetPlaylistsUseCase {
    private final PlaylistRepository db;

    @Override
    public List<PlaylistDTO> getPlaylists(){
        return PlaylistDTOConverter.convertToDTOList(db.findAll());
    }
}
