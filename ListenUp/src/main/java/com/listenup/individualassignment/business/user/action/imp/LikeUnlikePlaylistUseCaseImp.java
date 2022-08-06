package com.listenup.individualassignment.business.user.action.imp;

import com.listenup.individualassignment.business.user.action.AddLikedPlaylistUseCase;
import com.listenup.individualassignment.business.user.action.LikeUnlikePlaylistUseCase;
import com.listenup.individualassignment.business.user.action.RemoveLikedPlaylistUseCase;
import com.listenup.individualassignment.dto.createdto.AddRemoveLikedPlaylistDTO;
import com.listenup.individualassignment.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Primary
@Transactional
@RequiredArgsConstructor
public class LikeUnlikePlaylistUseCaseImp implements LikeUnlikePlaylistUseCase {
    private final PlaylistRepository db;
    private final AddLikedPlaylistUseCase add;
    private final RemoveLikedPlaylistUseCase remove;

    public void likeUnlikePlaylist(AddRemoveLikedPlaylistDTO playlist){
        if(db.playlistLiked(playlist.getPlaylistID(), playlist.getCustomerID())==1){
            remove.removeLikedPlaylist(playlist);
        } else if(db.playlistLiked(playlist.getPlaylistID(), playlist.getCustomerID())==0){
            add.addLikedPlaylist(playlist);
        }
    }
}
