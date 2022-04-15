package com.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.api.model.Offer;

@Repository
public interface OfferRepository extends MongoRepository<Offer, String> {

}
