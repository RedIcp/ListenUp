package com.listenup.individualassignment.repository;

import com.listenup.individualassignment.repository.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long>{

}
