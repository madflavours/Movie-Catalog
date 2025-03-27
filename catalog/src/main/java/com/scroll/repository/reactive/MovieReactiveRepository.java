package com.scroll.repository.reactive;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.scroll.DTO.MovieDTO;

@Repository
public interface MovieReactiveRepository extends ReactiveMongoRepository<MovieDTO, String>{

}
