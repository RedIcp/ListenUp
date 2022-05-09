package com.listenup.individualassignment.dto.createdto;

import com.listenup.individualassignment.dto.vieweditdto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlaylistDTO {
    private String name;
    private UserDTO customer;
}
