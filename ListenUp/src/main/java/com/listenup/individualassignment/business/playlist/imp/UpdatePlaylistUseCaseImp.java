package com.listenup.individualassignment.business.playlist.imp;

import com.listenup.individualassignment.business.dtoconverter.PlaylistDTOConverter;
import com.listenup.individualassignment.business.exception.InvalidPlaylistException;
import com.listenup.individualassignment.business.playlist.UpdatePlaylistUseCase;
import com.listenup.individualassignment.business.user.IsAuthorised;
import com.listenup.individualassignment.dto.vieweditdto.PlaylistDTO;
import com.listenup.individualassignment.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class UpdatePlaylistUseCaseImp implements UpdatePlaylistUseCase {
    private final PlaylistRepository db;
    private final IsAuthorised authorised;

    @Override
    public PlaylistDTO editPlaylist(PlaylistDTO playlist){
        authorised.isAuthorised(playlist.getCustomer().getId());

        if(!db.existsById(playlist.getId())){
            throw new InvalidPlaylistException("INVALID_ID");
        }

        db.save(PlaylistDTOConverter.convertToModelForUpdate(playlist));
        return playlist;
    }
}
