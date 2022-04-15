package com.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.api.model.Offer;
import com.api.model.Pitch;
import com.api.repository.PitchRepository;

@Service
public class PitchService {

	@Autowired
	private PitchRepository pitchRepository;

	public Pitch createPitch(Pitch pitch) {
		return pitchRepository.save(pitch);
	}

	public List<Pitch> getAllPitches() {
		return pitchRepository.findAll(Sort.by(Direction.DESC, "id"));
	}

	public Optional<Pitch> getPitch(String pitchId) {
		return pitchRepository.findById(pitchId);
	}

	public void addOfferToPitch(Pitch pitch, Offer offer) {
		pitch.getOffers().add(offer);
		pitchRepository.save(pitch);
	}

}
