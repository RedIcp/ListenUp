package com.listenup.individualassignment.business.user.action.imp;

import com.listenup.individualassignment.business.dtoconverter.CustomerDTOConverter;
import com.listenup.individualassignment.business.dtoconverter.SongListDTOConverter;
import com.listenup.individualassignment.business.user.action.GetUserLikedSongsUseCase;
import com.listenup.individualassignment.dto.CustomerLikedSongListDTO;
import com.listenup.individualassignment.repository.UserRepository;
import com.listenup.individualassignment.repository.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Primary
@Transactional
@RequiredArgsConstructor
public class GetUserLikedSongsUseCaseImp implements GetUserLikedSongsUseCase {
    private final UserRepository db;
    private final SongListDTOConverter songList;

    @Override
    public CustomerLikedSongListDTO getCustomerCollection(long id){
        if(db.existsById(id)){
            Customer customer = db.getUserById(id);

            CustomerLikedSongListDTO customerDTO = CustomerDTOConverter.convertToDTOForLikedSong(customer);
            customerDTO.setSongs(songList.convertToSingleSongDTOList(customer.getLikedSongs()));

            return customerDTO;
        }
        return null;
    }
}
