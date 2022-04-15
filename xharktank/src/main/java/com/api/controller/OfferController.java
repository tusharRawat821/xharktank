package com.api.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.exception.PitchNotFoundException;
import com.api.model.SucceesResponse;
import com.api.model.Offer;
import com.api.model.Pitch;
import com.api.service.OfferService;
import com.api.service.PitchService;

@RestController
public class OfferController {

	@Autowired
	private PitchService pitchService;
	@Autowired
	private OfferService offerService;

	@PostMapping("/pitches/{pitchId}/makeOffer")
	public ResponseEntity<SucceesResponse> createOffer(@PathVariable String pitchId, @Valid @RequestBody Offer offer) {
		Optional<Pitch> pitch = pitchService.getPitch(pitchId);

		if (pitch.isEmpty())
			throw new PitchNotFoundException("pitch not found");

		Offer offerCreated = offerService.createOffer(offer);
		pitchService.addOfferToPitch(pitch.get(), offerCreated);
		return ResponseEntity.status(HttpStatus.CREATED).body(createResponse(offerCreated.getId()));
	}

	private SucceesResponse createResponse(String id) {
		return new SucceesResponse(id);
	}

}
