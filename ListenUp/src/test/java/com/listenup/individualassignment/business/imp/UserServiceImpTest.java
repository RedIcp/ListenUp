package com.listenup.individualassignment.business.imp;

import com.listenup.individualassignment.business.login.AccessTokenEncoder;
import com.listenup.individualassignment.business.exception.InvalidCredentialsException;
import com.listenup.individualassignment.business.exception.InvalidCustomerEmailException;
import com.listenup.individualassignment.business.exception.InvalidCustomerException;
import com.listenup.individualassignment.business.dtoconverter.CustomerDTOConverter;
import com.listenup.individualassignment.business.dtoconverter.PlaylistDTOConverter;
import com.listenup.individualassignment.business.dtoconverter.SongDTOConverter;
import com.listenup.individualassignment.business.login.imp.LoginUseCaseImp;
import com.listenup.individualassignment.business.playlist.GetPlaylist;
import com.listenup.individualassignment.business.song.GetSong;
import com.listenup.individualassignment.business.user.IsAuthorised;
import com.listenup.individualassignment.business.user.account.imp.CreateAccountUseCaseImp;
import com.listenup.individualassignment.business.user.account.imp.DeleteAccountUseCaseImp;
import com.listenup.individualassignment.business.user.account.imp.GetUserUseCaseImp;
import com.listenup.individualassignment.business.user.account.imp.UpdateProfileUseCaseImp;
import com.listenup.individualassignment.business.user.action.imp.*;
import com.listenup.individualassignment.dto.*;
import com.listenup.individualassignment.dto.createdto.AddRemoveLikedPlaylistDTO;
import com.listenup.individualassignment.dto.createdto.AddRemoveSongToCollectionDTO;
import com.listenup.individualassignment.dto.createdto.CreateUserRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateUserResponseDTO;
import com.listenup.individualassignment.dto.vieweditdto.*;
import com.listenup.individualassignment.repository.entity.Customer;
import com.listenup.individualassignment.repository.entity.RoleEnum;
import com.listenup.individualassignment.repository.entity.UserRole;
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
    @Mock
    private IsAuthorised authorised;
    @Mock
    private GetPlaylist getPlaylist;
    @Mock
    private GetSong getSong;

    @InjectMocks
    private UpdateProfileUseCaseImp updateProfileUseCase;
    @InjectMocks
    private DeleteAccountUseCaseImp deleteAccountUseCase;
    @InjectMocks
    private GetUserUseCaseImp getUserUseCase;
    @InjectMocks
    private GetUsersUseCaseImp getUsersUseCase;
    @InjectMocks
    private GetUserPlaylistsUseCaseImp getCustomerPlaylistsUseCase;
    @InjectMocks
    private GetUserLikedPlaylistsUseCaseImp getUserLikedPlaylistsUseCase;
    @InjectMocks
    private GetUserLikedSongsUseCaseImp getUserLikedSongsUseCase;
    @InjectMocks
    private AddLikedSongUseCaseImp addLikedSongUseCase;
    @InjectMocks
    private AddLikedPlaylistUseCaseImp addLikedPlaylistUseCase;
    @InjectMocks
    private RemoveLikedSongUseCaseImp removeLikedSongUseCase;
    @InjectMocks
    private RemoveLikedPlaylistUseCaseImp removeLikedPlaylistUseCase;
    @InjectMocks
    private CreateAccountUseCaseImp createAccountUseCase;
    @InjectMocks
    private LoginUseCaseImp loginUseCase;

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

        CreateUserResponseDTO actualDTO = createAccountUseCase.createAccount(dto);

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

        InvalidCustomerEmailException exception = assertThrows(InvalidCustomerEmailException.class, ()-> createAccountUseCase.createAccount(expectedDTO));

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

        when(loginUseCase.generateAccessToken(savedCustomer)).thenReturn("h123H123s");
        when(passwordEncoder.matches(request.getPassword(), savedCustomer.getPassword())).thenReturn(true);

        LoginResponseDTO expectedDTO = LoginResponseDTO.builder()
                .accessToken("h123H123s")
                .build();

        LoginResponseDTO actualDTO = loginUseCase.login(request);

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

        InvalidCredentialsException exception = assertThrows(InvalidCredentialsException.class, ()->loginUseCase.login(request));

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

        InvalidCustomerEmailException exception = assertThrows(InvalidCustomerEmailException.class, ()->loginUseCase.login(request));

        assertEquals("EMAIL_DOES_NOT_EXIST", exception.getReason());

        verify(repository).existsByEmail("yellow@gmail.com");
    }

    @Test
    void getUser() {
        Customer customer = new Customer(1L,"Yellow", "yellow@gmail.com", "123Yellow");

        when(repository.getById(1L)).thenReturn(customer);

        UpdateUserDTO expectedDTO = UpdateUserDTO.builder()
                .id(1L)
                .username("Yellow")
                .email("yellow@gmail.com")
                .password("123Yellow")
                .build();

        UpdateUserDTO actualDTO = getUserUseCase.getUser(1L);

        assertEquals(actualDTO, expectedDTO);

        verify(repository).getById(1L);
    }

    @Test
    void getUsers() {
        Customer customer = new Customer(1L,"Yellow", "yellow@gmail.com", "123Yellow");

        when(repository.findAll()).thenReturn(List.of(customer));

        List<ViewUserDTO> expectedList = new ArrayList<>();
        expectedList.add(CustomerDTOConverter.convertToDTOForView(customer));

        List<ViewUserDTO> actualList = getUsersUseCase.getUsers();

        assertEquals(actualList, expectedList);

        verify(repository).findAll();
    }

    @Test
    void getCustomerCollection() {
        Customer customer = new Customer(1L,"Yellow", "yellow@gmail.com", "123Yellow");

        when(repository.getUserById(1L)).thenReturn(customer);

        CustomerLikedSongListDTO expectedDTO = CustomerLikedSongListDTO.builder()
                .id(1L)
                .songs(Collections.emptyList())
                .build();

        CustomerLikedSongListDTO actualDTO = getUserLikedSongsUseCase.getCustomerCollection(1L);

        assertEquals(actualDTO, expectedDTO);

        verify(repository).getUserById(1L);
    }

    @Test
    void getCustomerPlaylists() {
        Customer customer = new Customer(1L,"Yellow", "yellow@gmail.com", "123Yellow");

        when(repository.getUserById(1L)).thenReturn(customer);

        CustomerPlaylistListDTO expectedDTO = CustomerPlaylistListDTO.builder()
                .id(1L)
                .playlists(Collections.emptyList())
                .build();

        CustomerPlaylistListDTO actualDTO = getCustomerPlaylistsUseCase.getCustomerPlaylists(1L);

        assertEquals(actualDTO, expectedDTO);

        verify(repository).getUserById(1L);
    }

    @Test
    void getCustomerLikedPlaylists() {
        Customer customer = new Customer(1L,"Yellow", "yellow@gmail.com", "123Yellow");
        
        when(repository.getUserById(1L)).thenReturn(customer);

        CustomerLikedPlaylistListDTO expectedDTO = CustomerLikedPlaylistListDTO.builder()
                .id(1L)
                .playlists(Collections.emptyList())
                .build();

        CustomerLikedPlaylistListDTO actualDTO = getUserLikedPlaylistsUseCase.getCustomerLikedPlaylists(1L);

        assertEquals(actualDTO, expectedDTO);

        verify(repository).getUserById(1L);
    }

    @Test
    void updateAccountValid() {
        Customer beforeUpdateCustomer = new Customer(1L,"Yellow", "yellow@gmail.com", "123Yellow");

        when(repository.getById(1L)).thenReturn(beforeUpdateCustomer);
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.existsByEmail("blue@gmail.com")).thenReturn(false);

        UpdateUserDTO updateDTO = UpdateUserDTO.builder()
                .id(1L)
                .username("Blue")
                .email("blue@gmail.com")
                .password("123Blue")
                .build();

        updateProfileUseCase.updateAccount(updateDTO);

        Customer actualCustomer = new Customer(1L,"Blue", "blue@gmail.com", "123Blue");

        verify(repository).existsByEmail("blue@gmail.com");
        verify(repository).getById(1L);
        verify(repository).existsById(1L);
        verify(repository).save(actualCustomer);
    }

    @Test
    void updateAccountSameEmailAsCurrentUser() {
        Customer beforeUpdateCustomer = new Customer(1L,"Yellow", "yellow@gmail.com", "123Yellow");

        when(repository.getById(1L)).thenReturn(beforeUpdateCustomer);
        when(repository.existsById(1L)).thenReturn(true);

        UpdateUserDTO updateDTO = UpdateUserDTO.builder()
                .id(1L)
                .username("Blue")
                .email("yellow@gmail.com")
                .password("123Blue")
                .build();

        updateProfileUseCase.updateAccount(updateDTO);

        Customer actualCustomer = new Customer(1L,"Blue", "yellow@gmail.com", "123Blue");

        verify(repository).getById(1L);
        verify(repository).existsById(1L);
        verify(repository).save(actualCustomer);
    }

    @Test
    void updateAccountInvalidID() {
        when(repository.existsById(1L)).thenReturn(false);

        UpdateUserDTO updateDTO = UpdateUserDTO.builder()
                .id(1L)
                .username("Blue")
                .email("blue@gmail.com")
                .password("123Blue")
                .build();

        InvalidCustomerException exception = assertThrows(InvalidCustomerException.class, ()-> updateProfileUseCase.updateAccount(updateDTO));

        assertEquals("INVALID_ID", exception.getReason());

        verify(repository).getById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void updateAccountInvalidEmail() {
        Customer beforeUpdateCustomer = new Customer(1L,"Yellow", "yellow@gmail.com", "123Yellow");

        when(repository.getById(1L)).thenReturn(beforeUpdateCustomer);
        when(repository.existsByEmail("blue@gmail.com")).thenReturn(true);
        when(repository.existsById(1L)).thenReturn(true);

        UpdateUserDTO updateDTO = UpdateUserDTO.builder()
                .id(1L)
                .username("Blue")
                .email("blue@gmail.com")
                .password("123Blue")
                .build();

        InvalidCustomerEmailException exception = assertThrows(InvalidCustomerEmailException.class, ()-> updateProfileUseCase.updateAccount(updateDTO));

        assertEquals("EMAIL_EXIST", exception.getReason());

        verify(repository).getById(1L);
        verifyNoMoreInteractions(repository);
    }

//    @Test
//    void updateAccountUnauthorised() {
//        Customer beforeUpdateCustomer = new Customer(1L,"Yellow", "yellow@gmail.com", "123Yellow");
//
//        when(authorised.isAuthorised(1l)).thenThrow(UnauthorizedDataAccessException.class);
//        when(repository.getById(1L)).thenReturn(beforeUpdateCustomer);
//
//        UpdateUserDTO updateDTO = UpdateUserDTO.builder()
//                .id(1L)
//                .username("Blue")
//                .email("blue@gmail.com")
//                .password("123Blue")
//                .build();
//
//        UnauthorizedDataAccessException exception = assertThrows(UnauthorizedDataAccessException.class, ()-> updateProfileUseCase.updateAccount(updateDTO));
//
//        assertEquals("USER_ID_NOT_FROM_LOGGED_IN_USER", exception.getReason());
//
//        verify(repository).getById(1L);
//        verifyNoMoreInteractions(repository);
//    }

    @Test
    void addSongToCollection() {
        Customer beforeUpdateCustomer = new Customer(1L,"Yellow", "yellow@gmail.com", "123Yellow");

        when(repository.getUserById(1L)).thenReturn(beforeUpdateCustomer);
        when(repository.existsById(1L)).thenReturn(true);

        AddRemoveSongToCollectionDTO updateDTO = AddRemoveSongToCollectionDTO.builder()
                .customerID(1L)
                .songID(1L)
                .build();

        addLikedSongUseCase.addSongToCollection(updateDTO);

        verify(repository).existsById(1L);
        verify(repository).getUserById(1L);
    }

    @Test
    void addSongToCollectionInvalid() {
        when(repository.existsById(1L)).thenReturn(false);

        AddRemoveSongToCollectionDTO updateDTO = AddRemoveSongToCollectionDTO.builder()
                .customerID(1L)
                .songID(1L)
                .build();

        InvalidCustomerException exception = assertThrows(InvalidCustomerException.class, () -> addLikedSongUseCase.addSongToCollection(updateDTO));

        assertEquals("INVALID_CUSTOMER", exception.getReason());

        verify(repository).getUserById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void addLikedPlaylists() {
        Customer beforeUpdateCustomer = new Customer(1L,"Yellow", "yellow@gmail.com", "123Yellow");

        when(repository.getUserById(1L)).thenReturn(beforeUpdateCustomer);
        when(repository.existsById(1L)).thenReturn(true);

        AddRemoveLikedPlaylistDTO updateDTO = AddRemoveLikedPlaylistDTO.builder()
                .customerID(1L)
                .playlistID(1L)
                .build();

        addLikedPlaylistUseCase.addLikedPlaylist(updateDTO);

        verify(repository).existsById(1L);
        verify(repository).getUserById(1L);
    }

    @Test
    void addPlaylistsInvalid(){
        when(repository.existsById(1L)).thenReturn(false);

        AddRemoveLikedPlaylistDTO updateDTO = AddRemoveLikedPlaylistDTO.builder()
                .customerID(1L)
                .playlistID(1L)
                .build();

        InvalidCustomerException exception = assertThrows(InvalidCustomerException.class, () -> addLikedPlaylistUseCase.addLikedPlaylist(updateDTO));

        assertEquals("INVALID_CUSTOMER", exception.getReason());

        verify(repository).existsById(1L);
    }

    @Test
    void removeSongFromCollection() {
        when(repository.existsById(1L)).thenReturn(true);

        AddRemoveSongToCollectionDTO updateDTO = AddRemoveSongToCollectionDTO.builder()
                .customerID(1L)
                .songID(1L)
                .build();

        removeLikedSongUseCase.removeSongFromCollection(updateDTO);

        verify(repository).existsById(1L);
        verify(repository).removeLikedSong(1L, 1L);
    }

    @Test
    void removeSongFromCollectionInvalid() {
        when(repository.existsById(1L)).thenReturn(false);

        AddRemoveSongToCollectionDTO updateDTO = AddRemoveSongToCollectionDTO.builder()
                .customerID(1L)
                .songID(1L)
                .build();

        InvalidCustomerException exception = assertThrows(InvalidCustomerException.class, () -> removeLikedSongUseCase.removeSongFromCollection(updateDTO));

        assertEquals("INVALID_CUSTOMER", exception.getReason());

        verify(repository).existsById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void removeLikedPlaylists() {
        when(repository.existsById(1L)).thenReturn(true);

        AddRemoveLikedPlaylistDTO updateDTO = AddRemoveLikedPlaylistDTO.builder()
                .customerID(1L)
                .playlistID(1L)
                .build();

        removeLikedPlaylistUseCase.removeLikedPlaylist(updateDTO);

        verify(repository).existsById(1L);
        verify(repository).removeLikedPlaylist(1L, 1L);
    }

    @Test
    void removeLikedPlaylistsInvalid(){
        when(repository.existsById(1L)).thenReturn(false);

        AddRemoveLikedPlaylistDTO updateDTO = AddRemoveLikedPlaylistDTO.builder()
                .customerID(1L)
                .playlistID(1L)
                .build();

        InvalidCustomerException exception = assertThrows(InvalidCustomerException.class, () -> removeLikedPlaylistUseCase.removeLikedPlaylist(updateDTO));

        assertEquals("INVALID_CUSTOMER", exception.getReason());

        verify(repository).existsById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deleteAccountValid() {
        when(repository.existsById(1L)).thenReturn(true);

        deleteAccountUseCase.deleteAccount(1L);

        verify(repository).existsById(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    void deleteAccountInvalid() {
        when(repository.existsById(1L)).thenReturn(false);

        deleteAccountUseCase.deleteAccount(1L);

        verify(repository).existsById(1L);
        verifyNoMoreInteractions(repository);
    }
}