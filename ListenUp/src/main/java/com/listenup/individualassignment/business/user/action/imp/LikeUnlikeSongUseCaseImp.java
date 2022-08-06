package com.listenup.individualassignment.business.user.action.imp;

import com.listenup.individualassignment.business.user.action.AddLikedSongUseCase;
import com.listenup.individualassignment.business.user.action.LikeUnlikeSongUseCase;
import com.listenup.individualassignment.business.user.action.RemoveLikedSongUseCase;
import com.listenup.individualassignment.dto.createdto.AddRemoveSongToCollectionDTO;
import com.listenup.individualassignment.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Primary
@Transactional
@RequiredArgsConstructor
public class LikeUnlikeSongUseCaseImp implements LikeUnlikeSongUseCase {
    private final SongRepository db;
    private final AddLikedSongUseCase add;
    private final RemoveLikedSongUseCase remove;

    public void likeUnlikeSong(AddRemoveSongToCollectionDTO song) {
        if (db.songLiked(song.getSongID(), song.getCustomerID()) == 0) {
            add.addSongToCollection(song);
        } else if (db.songLiked(song.getSongID(), song.getCustomerID()) == 1) {
            remove.removeSongFromCollection(song);
        }
    }
}
