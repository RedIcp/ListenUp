package com.listenup.individualassignment;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.listenup.individualassignment.model.Artist;
import com.listenup.individualassignment.business.ArtistService;
import com.listenup.individualassignment.dto.createupdate.ArtistDTO;
import com.listenup.individualassignment.repository.ArtistRepository;
import com.listenup.individualassignment.business.imp.ArtistServiceImp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ArtistTest {
    @Autowired
    ArtistRepository dbArtist;

    ArtistService artistMG;

    @BeforeEach
    void  setUp(){
        artistMG = new ArtistServiceImp(dbArtist);
    }
    //dto: valid input
    @Test
    void artistDTOConvertorValidInput() {
        Artist artist = new Artist(1, "Maroon 5");
        artistMG.addArtist(artist);

        ArtistDTO artistDTO= new ArtistDTO(1, "Maroon 5");
        assertNotNull(artistMG.artistDTOConvertor(artistDTO));
    }
    @Test
    void artistObjConvertorValidInput() {
        Artist artist = new Artist(1, "Maroon 5");
        artistMG.addArtist(artist);

        assertNotNull(artistMG.artistObjConvertor(artist));
    }
    //dto list: valid input
    @Test
    void artistDTOListConvertorValidInput() {
        Artist artist = new Artist(1, "Maroon 5");
        List<Artist> artists = new ArrayList<>();
        artists.add(artist);

        assertNotNull(artistMG.getArtistDTOS(artists));
    }

    //create artist: valid input
    @Test
    void addArtistValidID() {
        Artist artist = new Artist(1, "Maroon 5");
        Artist artist1 = new Artist(2, "Post Malone");
        artistMG.addArtist(artist1);

        assertTrue(artistMG.addArtist(artist));
    }
    //create artist: same id
    @Test
    void addArtistSameID() {
        Artist artist1 = new Artist(1, "Maroon 5");
        Artist artist2 = new Artist(1, "Post Malone");
        artistMG.addArtist(artist1);
        assertFalse(artistMG.addArtist(artist2));
    }

    //update artist: valid input
    @Test
    void updateArtistValidInput() {
        Artist artist1 = new Artist(1, "Maroon 5");
        Artist artist2 = new Artist(1, "Post Malone");
        artistMG.addArtist(artist1);
        assertTrue(artistMG.editArtist(artist2));
    }
    //update artist: artist not in database
    @Test
    void updateArtistInvalidID() {
        Artist artist1 = new Artist(1, "Maroon 5");
        assertFalse(artistMG.editArtist(artist1));
    }

    //delete artist: valid input
    @Test
    void deleteArtistValidInput() {
        Artist artist1 = new Artist(1, "Maroon 5");
        artistMG.addArtist(artist1);
        assertTrue(artistMG.deleteArtist(1));
    }

    //delete artist: invalid id
    @Test
    void deleteArtistInvalidID() {
        assertFalse(artistMG.deleteArtist(100));
    }
}

