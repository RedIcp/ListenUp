package com.listenup.individualassignment.business.imp;

import com.listenup.individualassignment.business.AccessTokenEncoder;
import com.listenup.individualassignment.business.exception.InvalidCredentialsException;
import com.listenup.individualassignment.business.exception.InvalidCustomerEmailException;
import com.listenup.individualassignment.business.exception.InvalidCustomerException;
import com.listenup.individualassignment.business.imp.dtoconverter.CustomerDTOConverter;
import com.listenup.individualassignment.business.imp.dtoconverter.PlaylistDTOConverter;
import com.listenup.individualassignment.business.imp.dtoconverter.SongDTOConverter;
import com.listenup.individualassignment.dto.*;
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

        Customer savedCustomer = new Customer(1l,"Yellow", "yellow@gmail.com", "123Yellow");

        when(repository.existsByEmail("yellow@gmail.com")).thenReturn(false);
        when(repository.save(customer)).thenReturn(savedCustomer);

        CreateUserRequestDTO dto = CreateUserRequestDTO.builder()
                .username("Yellow")
                .email("yellow@gmail.com")
                .password("123Yellow")
                .build();

        CreateUserResponseDTO expectedDTO = CreateUserResponseDTO.builder()
                .userID(1l)
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
        Customer savedCustomer = new Customer(1l,"Yellow", "yellow@gmail.com", "hashed1234");
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
        Customer savedCustomer = new Customer(1l,"Yellow", "yellow@gmail.com", "hashed1234");
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
        Customer customer = new Customer(1l,"Yellow", "yellow@gmail.com", "123Yellow");

        when(requestAccessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(true);
        when(repository.getById(1l)).thenReturn(customer);

        UpdateUserDTO expectedDTO = UpdateUserDTO.builder()
                .id(1l)
                .username("Yellow")
                .email("yellow@gmail.com")
                .password("123Yellow")
                .build();

        UpdateUserDTO actualDTO = service.getUser(1l);

        assertEquals(actualDTO, expectedDTO);
    }

    @Test
    void getUsers() {
        Customer customer = new Customer(1l,"Yellow", "yellow@gmail.com", "123Yellow");

        when(repository.findAll()).thenReturn(List.of(customer));

        List<ViewUserDTO> expectedList = new ArrayList<>();
        expectedList.add(CustomerDTOConverter.convertToDTOForView(customer));

        List<ViewUserDTO> actualList = service.getUsers();

        assertEquals(actualList, expectedList);
    }

    @Test
    void getCustomerCollection() {
        Customer customer = new Customer(1l,"Yellow", "yellow@gmail.com", "123Yellow");

        when(repository.getById(1l)).thenReturn(customer);

        CustomerLikedSongListDTO expectedDTO = CustomerLikedSongListDTO.builder()
                .id(1l)
                .likedSongs(Collections.emptyList())
                .build();

        CustomerLikedSongListDTO actualDTO = service.getCustomerCollection(1l);

        assertEquals(actualDTO, expectedDTO);
    }

    @Test
    void getCustomerPlaylists() {
        Customer customer = new Customer(1l,"Yellow", "yellow@gmail.com", "123Yellow");

        when(repository.getById(1l)).thenReturn(customer);

        CustomerPlaylistListDTO expectedDTO = CustomerPlaylistListDTO.builder()
                .id(1l)
                .playlists(Collections.emptyList())
                .build();

        CustomerPlaylistListDTO actualDTO = service.getCustomerPlaylists(1l);

        assertEquals(actualDTO, expectedDTO);
    }

    @Test
    void getCustomerLikedPlaylists() {
        Customer customer = new Customer(1l,"Yellow", "yellow@gmail.com", "123Yellow");

        when(repository.getById(1l)).thenReturn(customer);

        CustomerLikedPlaylistListDTO expectedDTO = CustomerLikedPlaylistListDTO.builder()
                .id(1l)
                .likedPlaylists(Collections.emptyList())
                .build();

        CustomerLikedPlaylistListDTO actualDTO = service.getCustomerLikedPlaylists(1l);

        assertEquals(actualDTO, expectedDTO);
    }

    @Test
    void updateAccountValid() {
        Customer beforeUpdateCustomer = new Customer(1l,"Yellow", "yellow@gmail.com", "123Yellow");

        when(repository.getById(1l)).thenReturn(beforeUpdateCustomer);
        when(repository.existsById(1l)).thenReturn(true);
        when(repository.existsByEmail("blue@gmail.com")).thenReturn(false);

        UpdateUserDTO updateDTO = UpdateUserDTO.builder()
                .id(1l)
                .username("Blue")
                .email("blue@gmail.com")
                .password("123Blue")
                .build();

        service.updateAccount(updateDTO);

        Customer actualCustomer = new Customer(1l,"Blue", "blue@gmail.com", "123Blue");

        verify(repository).existsByEmail("blue@gmail.com");
        verify(repository).save(actualCustomer);
    }

    @Test
    void updateAccountSameEmailAsCurrentUser() {
        Customer beforeUpdateCustomer = new Customer(1l,"Yellow", "yellow@gmail.com", "123Yellow");

        when(repository.getById(1l)).thenReturn(beforeUpdateCustomer);
        when(repository.existsById(1l)).thenReturn(true);

        UpdateUserDTO updateDTO = UpdateUserDTO.builder()
                .id(1l)
                .username("Blue")
                .email("yellow@gmail.com")
                .password("123Blue")
                .build();

        service.updateAccount(updateDTO);

        Customer actualCustomer = new Customer(1l,"Blue", "yellow@gmail.com", "123Blue");

        verify(repository).save(actualCustomer);
    }

    @Test
    void updateAccountInvalidID() {
        when(repository.existsById(1l)).thenReturn(false);

        UpdateUserDTO updateDTO = UpdateUserDTO.builder()
                .id(1l)
                .username("Blue")
                .email("blue@gmail.com")
                .password("123Blue")
                .build();

        InvalidCustomerException exception = assertThrows(InvalidCustomerException.class, ()-> service.updateAccount(updateDTO));

        assertEquals("INVALID_ID", exception.getReason());

        verify(repository).getById(1l);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void updateAccountInvalidEmail() {
        Customer beforeUpdateCustomer = new Customer(1l,"Yellow", "yellow@gmail.com", "123Yellow");

        when(repository.getById(1l)).thenReturn(beforeUpdateCustomer);
        when(repository.existsByEmail("blue@gmail.com")).thenReturn(true);
        when(repository.existsById(1l)).thenReturn(true);

        UpdateUserDTO updateDTO = UpdateUserDTO.builder()
                .id(1l)
                .username("Blue")
                .email("blue@gmail.com")
                .password("123Blue")
                .build();

        InvalidCustomerEmailException exception = assertThrows(InvalidCustomerEmailException.class, ()-> service.updateAccount(updateDTO));

        assertEquals("EMAIL_EXIST", exception.getReason());

        verify(repository).getById(1l);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void editUserCollection() {
        Customer beforeUpdateCustomer = new Customer(1l,"Yellow", "yellow@gmail.com", "123Yellow");

        when(repository.getById(1l)).thenReturn(beforeUpdateCustomer);
        when(repository.existsById(1l)).thenReturn(true);

        ArtistDTO artist = ArtistDTO.builder()
                .id(1l)
                .name("Maroon 5")
                .build();

        GenreDTO genre = GenreDTO.builder()
                .id(1l)
                .name("Pop")
                .build();

        SingleSongDTO song = SingleSongDTO.builder()
                .id(1l)
                .name("Payphone")
                .artist(artist)
                .genre(genre)
                .uploadedDate(null)
                .releasedDate(null)
                .build();

        CustomerLikedSongListDTO updateDTO = CustomerLikedSongListDTO.builder()
                .id(1l)
                .likedSongs(List.of(song))
                .build();

        service.editUserCollection(updateDTO);

        Customer actualCustomer = new Customer(1l,"Yellow", "yellow@gmail.com", "123Yellow");
        actualCustomer.setLikedSongs(List.of(SongDTOConverter.convertToSingleSongModelForUpdate(song)));

        verify(repository).save(actualCustomer);
    }

    @Test
    void editUserCollectionInvalid() {
        when(repository.existsById(1l)).thenReturn(false);

        CustomerLikedSongListDTO updateDTO = CustomerLikedSongListDTO.builder()
                .id(1l)
                .likedSongs(Collections.emptyList())
                .build();

        InvalidCustomerException exception = assertThrows(InvalidCustomerException.class, () -> service.editUserCollection(updateDTO));

        assertEquals("INVALID_ID", exception.getReason());

        verify(repository).getById(1l);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void editUserLikedPlaylists() {
        Customer customer = new Customer(2l,"Blue", "blue@gmail.com", "123Blue");

        Customer beforeUpdateCustomer = new Customer(1l,"Yellow", "yellow@gmail.com", "123Yellow");

        when(repository.getById(1l)).thenReturn(beforeUpdateCustomer);
        when(repository.existsById(1l)).thenReturn(true);

        PlaylistDTO playlist = PlaylistDTO.builder()
                .id(1l)
                .customer(CustomerDTOConverter.convertToDTOForUpdate(customer))
                .name("Chill")
                .build();

        CustomerLikedPlaylistListDTO updateDTO = CustomerLikedPlaylistListDTO.builder()
                .id(1l)
                .likedPlaylists(List.of(playlist))
                .build();

        service.editUserLikedPlaylists(updateDTO);

        Customer actualCustomer = new Customer(1l,"Yellow", "yellow@gmail.com", "123Yellow");
        actualCustomer.setLikedPlaylists(List.of(PlaylistDTOConverter.convertToModelForUpdate(playlist)));

        verify(repository).save(actualCustomer);
    }

    @Test
    void editUserLikedPlaylistsInvalid(){
        when(repository.existsById(1l)).thenReturn(false);

        CustomerLikedPlaylistListDTO updateDTO = CustomerLikedPlaylistListDTO.builder()
                .id(1l)
                .likedPlaylists(Collections.emptyList())
                .build();

        InvalidCustomerException exception = assertThrows(InvalidCustomerException.class, () -> service.editUserLikedPlaylists(updateDTO));

        assertEquals("INVALID_ID", exception.getReason());

        verify(repository).getById(1l);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deleteAccountValid() {
        when(repository.existsById(1l)).thenReturn(true);

        service.deleteAccount(1l);

        verify(repository).existsById(1l);
        verify(repository).deleteById(1l);
    }

    @Test
    void deleteAccountInvalid() {
        when(repository.existsById(1l)).thenReturn(false);

        service.deleteAccount(1l);

        verify(repository).existsById(1l);
        verifyNoMoreInteractions(repository);
    }
}