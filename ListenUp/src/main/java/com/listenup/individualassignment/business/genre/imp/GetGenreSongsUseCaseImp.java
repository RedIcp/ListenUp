package com.listenup.individualassignment.business.genre.imp;

import com.listenup.individualassignment.business.dtoconverter.SongListDTOConverter;
import com.listenup.individualassignment.business.genre.GetGenreSongsUseCase;
import com.listenup.individualassignment.business.dtoconverter.GenreDTOConverter;
import com.listenup.individualassignment.dto.GenreSongListDTO;
import com.listenup.individualassignment.repository.GenreRepository;
import com.listenup.individualassignment.repository.entity.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Primary
@Transactional
@RequiredArgsConstructor
public class GetGenreSongsUseCaseImp implements GetGenreSongsUseCase {
    private final GenreRepository db;
    private final SongListDTOConverter songList;

    @Override
    public GenreSongListDTO getGenreSongs(long id) {
        if (db.existsById(id)) {
            Genre genre = db.getById(id);

            GenreSongListDTO genreDTO = GenreDTOConverter.convertToDTOForSong(genre);
            genreDTO.setSongs(songList.convertToSingleSongDTOList(genre.getSongs()));

            return genreDTO;
        }
        return null;
    }
}
