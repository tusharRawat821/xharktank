package com.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.api.model.Pitch;

@Repository
public interface PitchRepository extends MongoRepository<Pitch, String> {
	
}
