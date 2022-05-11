package com.listenup.individualassignment.dto.vieweditdto;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.validation.constraints.Email;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDTO {
    private long id;
    private String username;
    @Email
    private String email;
    private String password;
}
