package com.listenup.individualassignment.dto.createupdate;

import com.listenup.individualassignment.model.Customer;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDTO {
    private long id;
    private String name;
    private Customer owner;
}
