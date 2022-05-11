package com.listenup.individualassignment.dto.vieweditdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ViewUserDTO {
    private long id;
    private String username;
    private String email;
}
