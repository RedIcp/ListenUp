package com.listenup.individualassignment.dto.createdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddRemoveSongToCollectionDTO {
    private long customerID;
    private long songID;
}
