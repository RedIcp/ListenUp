package com.listenup.individualassignment.dto;

import com.listenup.individualassignment.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDTO {
    private int id;
    private String name;
    private Customer owner;
}
