package com.listenup.individualassignment.business.playlist.imp;

import com.listenup.individualassignment.business.dtoconverter.PlaylistDTOConverter;
import com.listenup.individualassignment.business.playlist.CreatePlaylistUseCase;
import com.listenup.individualassignment.dto.createdto.CreatePlaylistRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreatePlaylistResponseDTO;
import com.listenup.individualassignment.entity.Playlist;
import com.listenup.individualassignment.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class CreatePlaylistUseCaseImp implements CreatePlaylistUseCase {
    private final PlaylistRepository db;

    @Override
    public CreatePlaylistResponseDTO addPlaylist(CreatePlaylistRequestDTO playlist){
        Playlist savedPlaylist = db.save(PlaylistDTOConverter.convertToModelForCreate(playlist));

        return CreatePlaylistResponseDTO.builder()
                .playlistID(savedPlaylist.getId())
                .build();
    }
}
