package com.listenup.individualassignment.dto.createdto;

import com.listenup.individualassignment.dto.vieweditdto.UpdateUserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlaylistRequestDTO {
    private String name;
    private UpdateUserDTO customer;
}
