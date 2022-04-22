package com.listenup.individualassignment.dto.CreateUpdate;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.listenup.individualassignment.model.Customer;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDTO {
    private long id;
    private String name;
    private Customer owner;
}
