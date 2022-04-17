package com.listenup.individualassignment;

import com.listenup.individualassignment.business.*;
import com.listenup.individualassignment.business.imp.*;
import com.listenup.individualassignment.model.*;
import com.listenup.individualassignment.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ListenUpApplicationTests {
	@Autowired
	UserRepository dbUser;
	@Autowired
	SongRepository dbSong;
	@Autowired
	AlbumRepository dbAlbum;
	@Autowired
	ArtistRepository dbArtist;
	@Autowired
	GenreRepository dbGenre;
	@Autowired
	PlaylistRepository dbPlaylist;

	UserService userMG;
	SongService songMG;
	AlbumService albumMG;
	ArtistService artistMG;
	GenreService genreMG;
	PlaylistService playlistMG;

	@BeforeEach
	void  setUp(){
		userMG = new UserServiceImp(dbUser);
		songMG = new SongServiceImp(dbSong);
		albumMG = new AlbumServiceImp(dbAlbum);
		artistMG = new ArtistServiceImp(dbArtist);
		genreMG = new GenreServiceImp(dbGenre);
		playlistMG = new PlaylistServiceImp(dbPlaylist);
	}

	//USER------------------------------------------------------------------------------------------------------
	//create user: valid input
	@Test
	void createUserValidInput() {
		User user = new Customer(10, "Jam", "jam@yahoo.com", "123Jam");
		assertTrue(userMG.createAccount(user));
	}
	//create user: same email
	@Test
	void createUserSameEmail() {
		User user1 = new Customer(10, "Jam", "yellow@gmail.com", "123Jam");
		User user2 = new Customer(1, "Jam", "yellow@gmail.com", "123Jam");
		userMG.createAccount(user1);
		assertFalse(userMG.createAccount(user2));
	}
	//create user: same id
	@Test
	void createUserSameID() {
		User user1 = new Customer(1, "Jam", "jam@gmail.com", "123Jam");
		User user2 = new Customer(1, "Jam", "jam@gmail.com", "123Jam");
		userMG.createAccount(user1);
		assertFalse(userMG.createAccount(user2));
	}

	//update user: valid input
	@Test
	void updateUserValidInput() {
		User user1 = new Customer(1, "Jam", "jam@gmail.com", "123Jam");
		User user2 = new Customer(1, "Yellow", "yellow@gmail.com", "123Yellow");
		userMG.createAccount(user1);
		assertTrue(userMG.updateAccount(user2));
	}
	//update user: same email same user
	@Test
	void updateUserSameEmailAsUser() {
		User user1 = new Customer(1, "Jam", "yellow@gmail.com", "123Jam");
		User user2 = new Customer(1, "Yellow", "yellow@gmail.com", "123Yellow");
		userMG.createAccount(user1);
		assertTrue(userMG.updateAccount(user2));
	}
	//update user: same email another user
	@Test
	void updateUserSameEmailAsAnotherUser() {
		User user1 = new Customer(1, "Red", "red@gmail.com", "123Red");
		User user2 = new Customer(2, "Yellow", "yellow@gmail.com", "123Yellow");
		User user3 = new Customer(2, "Jam", "red@gmail.com", "123Jam");
		userMG.createAccount(user1);
		userMG.createAccount(user2);
		assertFalse(userMG.updateAccount(user3));
	}

	//delete user: valid input
	@Test
	void deleteUserValidInput() {
		User user = new Customer(10, "Jam", "jam@gmail.com", "123Jam");
		userMG.createAccount(user);
		assertTrue(userMG.deleteAccount(10));
	}

	//delete user: invalid id
	@Test
	void deleteUserInvalidID() {
		assertFalse(userMG.deleteAccount(100));
	}

	//SONG------------------------------------------------------------------------------------------------------
	//create song: valid input
	@Test
	void addSongValidInput() {
		Genre genre = new Genre(1, "Pop");
		genreMG.addGenre(genre);
		Date date = new Date(2021,11,27);
		Artist artist = new Artist(1, "Maroon 5");
		artistMG.addArtist(artist);
		Song song = new SingleSong(1, "Payphone", artist, genre, date, date);
		assertTrue(songMG.addSong(song));
	}
	//create song: same id
	@Test
	void addSongSameID() {
		Genre genre = new Genre(1, "Pop");
		genreMG.addGenre(genre);
		Date date = new Date(2021,11,27);
		Artist artist = new Artist(1, "Maroon 5");
		artistMG.addArtist(artist);
		Song song1 = new SingleSong(1, "Payphone", artist, genre, date, date);
		Song song2 = new SingleSong(1, "Star Boy", artist, genre, date, date);
		songMG.addSong(song1);
		assertFalse(songMG.addSong(song2));
	}

	//update song: valid input
	@Test
	void updateSongValidInput() {
		Genre genre = new Genre(1, "Pop");
		genreMG.addGenre(genre);
		Date date = new Date(2021,11,27);
		Artist artist = new Artist(1, "Maroon 5");
		artistMG.addArtist(artist);
		Song song = new SingleSong(1, "Payphone", artist, genre, date, date);
		songMG.addSong(song);
		assertTrue(songMG.editSong(song));
	}
	//update song: song not in database
	@Test
	void updateSongInvalidID() {
		Genre genre = new Genre(1, "Pop");
		genreMG.addGenre(genre);
		Date date = new Date(2021,11,27);
		Artist artist = new Artist(1, "Maroon 5");
		artistMG.addArtist(artist);
		Song song = new SingleSong(1, "Payphone", artist, genre, date, date);
		assertFalse(songMG.editSong(song));
	}

	//delete song: valid input
	@Test
	void deleteSongValidInput() {
		Genre genre = new Genre(1, "Pop");
		genreMG.addGenre(genre);
		Date date = new Date(2021,11,27);
		Artist artist = new Artist(1, "Maroon 5");
		artistMG.addArtist(artist);
		Song song = new SingleSong(1, "Payphone", artist, genre, date, date);
		songMG.addSong(song);
		assertTrue(songMG.deleteSong(1));
	}

	//delete song: invalid id
	@Test
	void deleteSongInvalidID() {
		assertFalse(songMG.deleteSong(100));
	}

	//ALBUM-----------------------------------------------------------------------------------------------------
	//create album: valid input
	@Test
	void addAlbumValidInput() {
		Artist artist = new Artist(1, "Maroon 5");
		artistMG.addArtist(artist);
		Date date = new Date(2021,11,27);
		Album album = new Album(1, "Overexposed", artist, date, date);
		assertTrue(albumMG.addAlbum(album));
	}
	//create album: same id
	@Test
	void addAlbumSameID() {
		Artist artist = new Artist(1, "Maroon 5");
		artistMG.addArtist(artist);
		Date date = new Date(2021,11,27);
		Album album1 = new Album(1, "Overexposed", artist, date, date);
		Album album2 = new Album(1, "V", artist, date, date);
		albumMG.addAlbum(album1);
		assertFalse(albumMG.addAlbum(album2));
	}

	//update album: valid input
	@Test
	void updateAlbumValidInput() {
		Artist artist = new Artist(1, "Maroon 5");
		artistMG.addArtist(artist);
		Date date = new Date(2021,11,27);
		Album album1 = new Album(1, "Overexposed", artist, date, date);
		Album album2 = new Album(1, "V", artist, date, date);
		albumMG.addAlbum(album1);
		assertTrue(albumMG.editAlbum(album2));
	}
	//update album: album not in database
	@Test
	void updateAlbumInvalidID() {
		Artist artist = new Artist(1, "Maroon 5");
		artistMG.addArtist(artist);
		Date date = new Date(2021,11,27);
		Album album = new Album(1, "Overexposed", artist, date, date);
		assertFalse(albumMG.editAlbum(album));
	}

	//delete album: valid input
	@Test
	void deleteAlbumValidInput() {
		Artist artist = new Artist(1, "Maroon 5");
		artistMG.addArtist(artist);
		Date date = new Date(2021,11,27);
		Album album = new Album(1, "Overexposed", artist, date, date);
		albumMG.addAlbum(album);
		assertTrue(albumMG.deleteAlbum(1));
	}

	//delete album: invalid id
	@Test
	void deleteAlbumInvalidID() {
		assertFalse(albumMG.deleteAlbum(100));
	}

	//PLAYLIST--------------------------------------------------------------------------------------------------
	//create playlist: valid input
	@Test
	void addPlaylistValidInput() {
		Customer customer = new Customer(1, "Jam", "jam@gmail.com", "123Jam");
		userMG.createAccount(customer);
		Playlist playlist = new Playlist(1, "Workout", customer);
		assertTrue(playlistMG.addPlaylist(playlist));
	}
	//create playlist: same id
	@Test
	void addPlaylistSameID() {
		Customer customer = new Customer(1, "Jam", "jam@gmail.com", "123Jam");
		userMG.createAccount(customer);
		Playlist playlist = new Playlist(1, "Workout", customer);
		Playlist playlist1 = new Playlist(1, "Chill", customer);
		playlistMG.addPlaylist(playlist);
		assertFalse(playlistMG.addPlaylist(playlist1));
	}

	//update playlist: valid input
	@Test
	void updatePlaylistValidInput() {
		Customer customer = new Customer(1, "Jam", "jam@gmail.com", "123Jam");
		userMG.createAccount(customer);
		Playlist playlist1 = new Playlist(1, "Workout", customer);
		Playlist playlist2 = new Playlist(1,"Chill", customer);
		playlistMG.addPlaylist(playlist1);
		assertTrue(playlistMG.editPlaylist(playlist2));
	}
	//update playlist: playlist not in database
	@Test
	void updatePlaylistInvalidID() {
		Customer customer = new Customer(1, "Jam", "jam@gmail.com", "123Jam");
		userMG.createAccount(customer);
		Playlist playlist = new Playlist(1, "Workout", customer);
		assertFalse(playlistMG.editPlaylist(playlist));
	}

	//delete playlist: valid input
	@Test
	void deletePlaylistValidInput() {
		Customer customer = new Customer(1, "Jam", "jam@gmail.com", "123Jam");
		userMG.createAccount(customer);
		Playlist playlist = new Playlist(1, "Workout", customer);
		playlistMG.addPlaylist(playlist);
		assertTrue(playlistMG.deletePlaylist(1));
	}

	//delete playlist: invalid id
	@Test
	void deletePlaylistInvalidID() {
		assertFalse(playlistMG.deletePlaylist(100));
	}

	//GENRE-----------------------------------------------------------------------------------------------------
	//create genre: valid input
	@Test
	void addGenreValidInput() {
		Genre genre = new Genre(1, "Pop");
		assertTrue(genreMG.addGenre(genre));
	}
	//create genre: same id
	@Test
	void addGenreSameID() {
		Genre genre1 = Genre.builder().id(1).name("Pop").build();
		Genre genre2 = Genre.builder().id(1).name("Hip-hop").build();
		genreMG.addGenre(genre1);
		assertFalse(genreMG.addGenre(genre2));
	}

	//update genre: valid input
	@Test
	void updateGenreValidInput() {
		Genre genre1 = new Genre(1, "Pop");
		Genre genre2 = new Genre(1, "Hip-Hop");
		genreMG.addGenre(genre1);
		assertTrue(genreMG.editGenre(genre2));
	}
	//update genre: genre not in database
	@Test
	void updateGenreInvalidID() {
		Genre genre = new Genre(1, "Hip-Hop");
		assertFalse(genreMG.editGenre(genre));
	}

	//delete genre: valid input
	@Test
	void deleteGenreValidInput() {
		Genre genre = new Genre(1, "Pop");
		genreMG.addGenre(genre);
		assertTrue(genreMG.deleteGenre(1));
	}

	//delete artist: invalid id
	@Test
	void deleteGenreInvalidID() {
		assertFalse(genreMG.deleteGenre(100));
	}

	//ARTIST----------------------------------------------------------------------------------------------------
	//create artist: valid input
	@Test
	void addArtistValidID() {
		Artist artist = new Artist(1, "Maroon 5");
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
