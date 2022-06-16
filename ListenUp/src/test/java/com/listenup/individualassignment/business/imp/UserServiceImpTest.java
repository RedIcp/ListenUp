package com.listenup.individualassignment.business.imp;

import com.listenup.individualassignment.business.AccessTokenEncoder;
import com.listenup.individualassignment.business.exception.InvalidCredentialsException;
import com.listenup.individualassignment.business.exception.InvalidCustomerEmailException;
import com.listenup.individualassignment.business.exception.InvalidCustomerException;
import com.listenup.individualassignment.business.exception.UnauthorizedDataAccessException;
import com.listenup.individualassignment.business.imp.dtoconverter.CustomerDTOConverter;
import com.listenup.individualassignment.business.imp.dtoconverter.PlaylistDTOConverter;
import com.listenup.individualassignment.business.imp.dtoconverter.SongDTOConverter;
import com.listenup.individualassignment.dto.*;
import com.listenup.individualassignment.dto.createdto.AddRemoveLikedPlaylistDTO;
import com.listenup.individualassignment.dto.createdto.AddRemoveSongToCollectionDTO;
import com.listenup.individualassignment.dto.createdto.CreateUserRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateUserResponseDTO;
import com.listenup.individualassignment.dto.vieweditdto.*;
import com.listenup.individualassignment.entity.Customer;
import com.listenup.individualassignment.entity.RoleEnum;
import com.listenup.individualassignment.entity.UserRole;
import com.listenup.individualassignment.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImpTest {
    @Mock
    private UserRepository repository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AccessTokenEncoder accessTokenEncoder;
    @Mock
    private AccessTokenDTO requestAccessToken;

    @InjectMocks
    private UserServiceImp service;

    @Test
    void createAccount() {
        Customer customer = new Customer("Yellow", "yellow@gmail.com", "123Yellow");

        Customer savedCustomer = new Customer(1L,"Yellow", "yellow@gmail.com", "123Yellow");

        when(repository.existsByEmail("yellow@gmail.com")).thenReturn(false);
        when(repository.save(customer)).thenReturn(savedCustomer);

        CreateUserRequestDTO dto = CreateUserRequestDTO.builder()
                .username("Yellow")
                .email("yellow@gmail.com")
                .password("123Yellow")
                .build();

        CreateUserResponseDTO expectedDTO = CreateUserResponseDTO.builder()
                .userID(1L)
                .build();

        CreateUserResponseDTO actualDTO = service.createAccount(dto);

        assertEquals(actualDTO, expectedDTO);

        verify(passwordEncoder).encode("123Yellow");
        verify(repository).existsByEmail("yellow@gmail.com");
        verify(repository).save(customer);
    }

    @Test
    void createAccountInvalidEmail() {
        when(repository.existsByEmail("yellow@gmail.com")).thenReturn(true);

        CreateUserRequestDTO expectedDTO = CreateUserRequestDTO.builder()
                .username("Yellow")
                .email("yellow@gmail.com")
                .password("123Yellow")
                .build();

        InvalidCustomerEmailException exception = assertThrows(InvalidCustomerEmailException.class, ()-> service.createAccount(expectedDTO));

        assertEquals("EMAIL_EXIST", exception.getReason());

        verify(repository).existsByEmail("yellow@gmail.com");
        verifyNoMoreInteractions(repository);
    }

    @Test
    void loginValid(){
        Customer savedCustomer = new Customer(1L,"Yellow", "yellow@gmail.com", "hashed1234");
        savedCustomer.setUserRoles(Set.of(
                UserRole.builder()
                        .user(savedCustomer)
                        .role(RoleEnum.CUSTOMER)
                        .build()));

        when(repository.existsByEmail("yellow@gmail.com")).thenReturn(true);
        when(repository.getByEmail("yellow@gmail.com")).thenReturn(savedCustomer);


        LoginRequestDTO request = LoginRequestDTO.builder()
                .email("yellow@gmail.com")
                .password("123Yellow")
                .build();

        when(service.generateAccessToken(savedCustomer)).thenReturn("h123H123s");
        when(passwordEncoder.matches(request.getPassword(), savedCustomer.getPassword())).thenReturn(true);

        LoginResponseDTO expectedDTO = LoginResponseDTO.builder()
                .accessToken("h123H123s")
                .build();

        LoginResponseDTO actualDTO = service.login(request);

        assertEquals(actualDTO, expectedDTO);

        verify(repository).existsByEmail("yellow@gmail.com");
        verify(repository).getByEmail("yellow@gmail.com");
    }

    @Test
    void loginInvalidCredential(){
        Customer savedCustomer = new Customer(1L,"Yellow", "yellow@gmail.com", "hashed1234");
        savedCustomer.setUserRoles(Set.of(
                UserRole.builder()
                        .user(savedCustomer)
                        .role(RoleEnum.CUSTOMER)
                        .build()));

        when(repository.existsByEmail("yellow@gmail.com")).thenReturn(true);
        when(repository.getByEmail("yellow@gmail.com")).thenReturn(savedCustomer);


        LoginRequestDTO request = LoginRequestDTO.builder()
                .email("yellow@gmail.com")
                .password("123Yellow")
                .build();

        when(passwordEncoder.matches(request.getPassword(), savedCustomer.getPassword())).thenReturn(false);

        InvalidCredentialsException exception = assertThrows(InvalidCredentialsException.class, ()->service.login(request));

        assertEquals("INVALID_CREDENTIALS", exception.getReason());

        verify(repository).existsByEmail("yellow@gmail.com");
        verify(repository).getByEmail("yellow@gmail.com");
    }

    @Test
    void loginInvalidEmail(){
        when(repository.existsByEmail("yellow@gmail.com")).thenReturn(false);


        LoginRequestDTO request = LoginRequestDTO.builder()
                .email("yellow@gmail.com")
                .password("123Yellow")
                .build();

        InvalidCustomerEmailException exception = assertThrows(InvalidCustomerEmailException.class, ()->service.login(request));

        assertEquals("EMAIL_DOES_NOT_EXIST", exception.getReason());

        verify(repository).existsByEmail("yellow@gmail.com");
    }

    @Test
    void getUser() {
        Customer customer = new Customer(1L,"Yellow", "yellow@gmail.com", "123Yellow");

        when(requestAccessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(true);
        when(repository.getById(1L)).thenReturn(customer);

        UpdateUserDTO expectedDTO = UpdateUserDTO.builder()
                .id(1L)
                .username("Yellow")
                .email("yellow@gmail.com")
                .password("123Yellow")
                .build();

        UpdateUserDTO actualDTO = service.getUser(1L);

        assertEquals(actualDTO, expectedDTO);
    }

    @Test
    void getUsers() {
        Customer customer = new Customer(1L,"Yellow", "yellow@gmail.com", "123Yellow");

        when(repository.findAll()).thenReturn(List.of(customer));

        List<ViewUserDTO> expectedList = new ArrayList<>();
        expectedList.add(CustomerDTOConverter.convertToDTOForView(customer));

        List<ViewUserDTO> actualList = service.getUsers();

        assertEquals(actualList, expectedList);
    }

    @Test
    void getCustomerCollection() {
        Customer customer = new Customer(1L,"Yellow", "yellow@gmail.com", "123Yellow");

        when(repository.getById(1L)).thenReturn(customer);

        CustomerLikedSongListDTO expectedDTO = CustomerLikedSongListDTO.builder()
                .id(1L)
                .likedSongs(Collections.emptyList())
                .build();

        CustomerLikedSongListDTO actualDTO = service.getCustomerCollection(1L);

        assertEquals(actualDTO, expectedDTO);
    }

    @Test
    void getCustomerPlaylists() {
        Customer customer = new Customer(1L,"Yellow", "yellow@gmail.com", "123Yellow");

        when(repository.getById(1L)).thenReturn(customer);

        CustomerPlaylistListDTO expectedDTO = CustomerPlaylistListDTO.builder()
                .id(1L)
                .playlists(Collections.emptyList())
                .build();

        CustomerPlaylistListDTO actualDTO = service.getCustomerPlaylists(1L);

        assertEquals(actualDTO, expectedDTO);
    }

    @Test
    void getCustomerLikedPlaylists() {
        Customer customer = new Customer(1L,"Yellow", "yellow@gmail.com", "123Yellow");
        
        when(repository.getById(1L)).thenReturn(customer);

        CustomerLikedPlaylistListDTO expectedDTO = CustomerLikedPlaylistListDTO.builder()
                .id(1L)
                .likedPlaylists(Collections.emptyList())
                .build();

        CustomerLikedPlaylistListDTO actualDTO = service.getCustomerLikedPlaylists(1L);

        assertEquals(actualDTO, expectedDTO);
    }

    @Test
    void updateAccountValid() {
        Customer beforeUpdateCustomer = new Customer(1L,"Yellow", "yellow@gmail.com", "123Yellow");

        when(requestAccessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(true);
        when(repository.getById(1L)).thenReturn(beforeUpdateCustomer);
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.existsByEmail("blue@gmail.com")).thenReturn(false);

        UpdateUserDTO updateDTO = UpdateUserDTO.builder()
                .id(1L)
                .username("Blue")
                .email("blue@gmail.com")
                .password("123Blue")
                .build();

        service.updateAccount(updateDTO);

        Customer actualCustomer = new Customer(1L,"Blue", "blue@gmail.com", "123Blue");

        verify(repository).existsByEmail("blue@gmail.com");
        verify(repository).save(actualCustomer);
    }

    @Test
    void updateAccountSameEmailAsCurrentUser() {
        Customer beforeUpdateCustomer = new Customer(1L,"Yellow", "yellow@gmail.com", "123Yellow");

        when(requestAccessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(true);
        when(repository.getById(1L)).thenReturn(beforeUpdateCustomer);
        when(repository.existsById(1L)).thenReturn(true);

        UpdateUserDTO updateDTO = UpdateUserDTO.builder()
                .id(1L)
                .username("Blue")
                .email("yellow@gmail.com")
                .password("123Blue")
                .build();

        service.updateAccount(updateDTO);

        Customer actualCustomer = new Customer(1L,"Blue", "yellow@gmail.com", "123Blue");

        verify(repository).save(actualCustomer);
    }

    @Test
    void updateAccountInvalidID() {
        when(requestAccessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(true);
        when(repository.existsById(1L)).thenReturn(false);

        UpdateUserDTO updateDTO = UpdateUserDTO.builder()
                .id(1L)
                .username("Blue")
                .email("blue@gmail.com")
                .password("123Blue")
                .build();

        InvalidCustomerException exception = assertThrows(InvalidCustomerException.class, ()-> service.updateAccount(updateDTO));

        assertEquals("INVALID_ID", exception.getReason());

        verify(repository).getById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void updateAccountInvalidEmail() {
        Customer beforeUpdateCustomer = new Customer(1L,"Yellow", "yellow@gmail.com", "123Yellow");

        when(requestAccessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(true);
        when(repository.getById(1L)).thenReturn(beforeUpdateCustomer);
        when(repository.existsByEmail("blue@gmail.com")).thenReturn(true);
        when(repository.existsById(1L)).thenReturn(true);

        UpdateUserDTO updateDTO = UpdateUserDTO.builder()
                .id(1L)
                .username("Blue")
                .email("blue@gmail.com")
                .password("123Blue")
                .build();

        InvalidCustomerEmailException exception = assertThrows(InvalidCustomerEmailException.class, ()-> service.updateAccount(updateDTO));

        assertEquals("EMAIL_EXIST", exception.getReason());

        verify(repository).getById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void updateAccountUnauthorised() {
        Customer beforeUpdateCustomer = new Customer(1L,"Yellow", "yellow@gmail.com", "123Yellow");

        when(requestAccessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(false);
        when(requestAccessToken.getUserID()).thenReturn(4L);
        when(repository.getById(1L)).thenReturn(beforeUpdateCustomer);

        UpdateUserDTO updateDTO = UpdateUserDTO.builder()
                .id(1L)
                .username("Blue")
                .email("blue@gmail.com")
                .password("123Blue")
                .build();

        UnauthorizedDataAccessException exception = assertThrows(UnauthorizedDataAccessException.class, ()-> service.updateAccount(updateDTO));

        assertEquals("USER_ID_NOT_FROM_LOGGED_IN_USER", exception.getReason());

        verify(repository).getById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void addSongToCollection() {
        Customer beforeUpdateCustomer = new Customer(1L,"Yellow", "yellow@gmail.com", "123Yellow");

        when(requestAccessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(true);
        when(repository.getById(1L)).thenReturn(beforeUpdateCustomer);
        when(repository.existsById(1L)).thenReturn(true);

        ArtistDTO artist = ArtistDTO.builder()
                .id(1L)
                .name("Maroon 5")
                .build();

        GenreDTO genre = GenreDTO.builder()
                .id(1L)
                .name("Pop")
                .build();

        SingleSongDTO song = SingleSongDTO.builder()
                .id(1L)
                .name("Payphone")
                .artist(artist)
                .genre(genre)
                .uploadedDate(null)
                .releasedDate(null)
                .build();

        AddRemoveSongToCollectionDTO updateDTO = AddRemoveSongToCollectionDTO.builder()
                .customerID(1L)
                .song(song)
                .build();

        service.addSongToCollection(updateDTO);

        Customer actualCustomer = new Customer(1L,"Yellow", "yellow@gmail.com", "123Yellow");
        actualCustomer.setLikedSongs(List.of(SongDTOConverter.convertToSingleSongModelForUpdate(song)));

        verify(repository).save(actualCustomer);
    }

    @Test
    void addSongToCollectionInvalid() {
        when(requestAccessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(true);
        when(repository.existsById(1L)).thenReturn(false);

        AddRemoveSongToCollectionDTO updateDTO = AddRemoveSongToCollectionDTO.builder()
                .customerID(1L)
                .song(null)
                .build();

        InvalidCustomerException exception = assertThrows(InvalidCustomerException.class, () -> service.addSongToCollection(updateDTO));

        assertEquals("INVALID_ID", exception.getReason());

        verify(repository).getById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void addLikedPlaylists() {
        Customer customer = new Customer(2L,"Blue", "blue@gmail.com", "123Blue");

        Customer beforeUpdateCustomer = new Customer(1L,"Yellow", "yellow@gmail.com", "123Yellow");

        when(requestAccessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(true);
        when(repository.getById(1L)).thenReturn(beforeUpdateCustomer);
        when(repository.existsById(1L)).thenReturn(true);

        PlaylistDTO playlist = PlaylistDTO.builder()
                .id(1L)
                .customer(CustomerDTOConverter.convertToDTOForUpdate(customer))
                .name("Chill")
                .build();

        AddRemoveLikedPlaylistDTO updateDTO = AddRemoveLikedPlaylistDTO.builder()
                .customerID(1L)
                .playlist(playlist)
                .build();

        service.addLikedPlaylist(updateDTO);

        Customer actualCustomer = new Customer(1L,"Yellow", "yellow@gmail.com", "123Yellow");
        actualCustomer.setLikedPlaylists(List.of(PlaylistDTOConverter.convertToModelForUpdate(playlist)));

        verify(repository).save(actualCustomer);
    }

    @Test
    void addPlaylistsInvalid(){
        when(requestAccessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(true);
        when(repository.existsById(1L)).thenReturn(false);

        AddRemoveLikedPlaylistDTO updateDTO = AddRemoveLikedPlaylistDTO.builder()
                .customerID(1L)
                .playlist(null)
                .build();

        InvalidCustomerException exception = assertThrows(InvalidCustomerException.class, () -> service.addLikedPlaylist(updateDTO));

        assertEquals("INVALID_ID", exception.getReason());

        verify(repository).getById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void removeSongFromCollection() {
        Customer beforeUpdateCustomer = new Customer(1L,"Yellow", "yellow@gmail.com", "123Yellow");

        when(requestAccessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(true);
        when(repository.getById(1L)).thenReturn(beforeUpdateCustomer);
        when(repository.existsById(1L)).thenReturn(true);

        ArtistDTO artist = ArtistDTO.builder()
                .id(1L)
                .name("Maroon 5")
                .build();

        GenreDTO genre = GenreDTO.builder()
                .id(1L)
                .name("Pop")
                .build();

        SingleSongDTO song = SingleSongDTO.builder()
                .id(1L)
                .name("Payphone")
                .artist(artist)
                .genre(genre)
                .uploadedDate(null)
                .releasedDate(null)
                .build();

        AddRemoveSongToCollectionDTO updateDTO = AddRemoveSongToCollectionDTO.builder()
                .customerID(1L)
                .song(song)
                .build();

        service.removeSongFromCollection(updateDTO);

        Customer actualCustomer = new Customer(1L,"Yellow", "yellow@gmail.com", "123Yellow");
        actualCustomer.setLikedSongs(Collections.emptyList());

        verify(repository).save(actualCustomer);
    }

    @Test
    void removeSongFromCollectionInvalid() {
        when(requestAccessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(true);
        when(repository.existsById(1L)).thenReturn(false);

        AddRemoveSongToCollectionDTO updateDTO = AddRemoveSongToCollectionDTO.builder()
                .customerID(1L)
                .song(null)
                .build();

        InvalidCustomerException exception = assertThrows(InvalidCustomerException.class, () -> service.removeSongFromCollection(updateDTO));

        assertEquals("INVALID_ID", exception.getReason());

        verify(repository).getById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void removeLikedPlaylists() {
        Customer customer = new Customer(2L,"Blue", "blue@gmail.com", "123Blue");

        Customer beforeUpdateCustomer = new Customer(1L,"Yellow", "yellow@gmail.com", "123Yellow");

        when(requestAccessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(true);
        when(repository.getById(1L)).thenReturn(beforeUpdateCustomer);
        when(repository.existsById(1L)).thenReturn(true);

        PlaylistDTO playlist = PlaylistDTO.builder()
                .id(1L)
                .customer(CustomerDTOConverter.convertToDTOForUpdate(customer))
                .name("Chill")
                .build();

        AddRemoveLikedPlaylistDTO updateDTO = AddRemoveLikedPlaylistDTO.builder()
                .customerID(1L)
                .playlist(playlist)
                .build();

        service.removeLikedPlaylist(updateDTO);

        Customer actualCustomer = new Customer(1L,"Yellow", "yellow@gmail.com", "123Yellow");
        actualCustomer.setLikedPlaylists(Collections.emptyList());

        verify(repository).save(actualCustomer);
    }

    @Test
    void removePlaylistsInvalid(){
        when(requestAccessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(true);
        when(repository.existsById(1L)).thenReturn(false);

        AddRemoveLikedPlaylistDTO updateDTO = AddRemoveLikedPlaylistDTO.builder()
                .customerID(1L)
                .playlist(null)
                .build();

        InvalidCustomerException exception = assertThrows(InvalidCustomerException.class, () -> service.removeLikedPlaylist(updateDTO));

        assertEquals("INVALID_ID", exception.getReason());

        verify(repository).getById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deleteAccountValid() {
        when(requestAccessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(true);
        when(repository.existsById(1L)).thenReturn(true);

        service.deleteAccount(1L);

        verify(repository).existsById(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    void deleteAccountInvalid() {
        when(requestAccessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(true);
        when(repository.existsById(1L)).thenReturn(false);

        service.deleteAccount(1L);

        verify(repository).existsById(1L);
        verifyNoMoreInteractions(repository);
    }
}