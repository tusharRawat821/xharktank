package com.api.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Document(collection = "pitches")
public class Pitch {

	@Id
	private String id;

	@NotBlank(message = "cannot be empty")
	private String entrepreneur;
	@NotBlank(message = "cannot be empty")
	private String pitchTitle;
	@NotBlank(message = "cannot be empty")
	private String pitchIdea;
	@NotNull(message = "cannot be empty")
	private BigDecimal askAmount;
	@NotNull(message = "cannot be empty")
	@Max(value = 100)
	private BigDecimal equity;

	// Stores only the '_id' of the reference document in the MongoDB thus saves the
	// space.
	// On retrieval of 'Pitch' it execute another query to fetch the referenced
	// Document.
	// Manual Reference.
	@JsonInclude(value = Include.ALWAYS)
	@DocumentReference
	private List<Offer> offers;

	public Pitch() {
		super();
		this.offers = new ArrayList<>();
	}

	public Pitch(String entrepreneur, String pitchTitle, String pitchIdea, BigDecimal askAmount, BigDecimal equity) {
		super();
		this.entrepreneur = entrepreneur;
		this.pitchTitle = pitchTitle;
		this.pitchIdea = pitchIdea;
		this.askAmount = askAmount;
		this.equity = equity;
		this.offers = new ArrayList<>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setEntrepreneur(String entrepreneur) {
		this.entrepreneur = entrepreneur;
	}

	public void setPitchTitle(String pitchTitle) {
		this.pitchTitle = pitchTitle;
	}

	public void setPitchIdea(String pitchIdea) {
		this.pitchIdea = pitchIdea;
	}

	public void setAskAmount(BigDecimal askAmount) {
		this.askAmount = askAmount;
	}

	public void setEquity(BigDecimal equity) {
		this.equity = equity;
	}

	public String getEntrepreneur() {
		return entrepreneur;
	}

	public String getPitchTitle() {
		return pitchTitle;
	}

	public String getPitchIdea() {
		return pitchIdea;
	}

	public BigDecimal getAskAmount() {
		return askAmount;
	}

	public BigDecimal getEquity() {
		return equity;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

}
