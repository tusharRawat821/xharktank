package com.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.exception.PitchNotFoundException;
import com.api.model.Pitch;
import com.api.model.SucceesResponse;
import com.api.service.PitchService;

@RestController
public class PitchController {

	@Autowired
	private PitchService pitchService;

	@PostMapping("/pitches")
	public ResponseEntity<SucceesResponse> createPitch(@Valid @RequestBody(required = true) Pitch pitch) {
		String pitchId = pitchService.createPitch(pitch).getId();

		SucceesResponse successResponse = createResponse(pitchId);
		return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
	}
	
	@GetMapping("/pitches")
	public ResponseEntity<List<Pitch>> getAllPitches() {
		List<Pitch> pitches = pitchService.getAllPitches();
		return ResponseEntity.status(HttpStatus.OK).body(pitches);
	}
	
	@GetMapping("/pitches/{pitchId}")
	public ResponseEntity<Pitch> getPitch(@PathVariable(required = true) String pitchId) {
		Optional<Pitch> pitch = pitchService.getPitch(pitchId);
		if(pitch.isEmpty())
			throw new PitchNotFoundException("pitch not found");
		return ResponseEntity.status(HttpStatus.OK).body(pitch.get());
	}
	

	private SucceesResponse createResponse(String id) {
		return new SucceesResponse(id);
	}
}
