package com.api.model;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "offers")
public class Offer {
	@Id
	private String id;

	@NotBlank(message = "cannot be empty")
	private String investor;
	
	@NotNull(message = "cannot be empty")
	private BigDecimal amount;
	
	@NotNull(message = "cannot be empty")
	@Max(value = 100)
	private BigDecimal equity;
	
	@NotBlank(message = "cannot be empty")
	private String comment;
	
	public Offer() {
		super();
	}
	
	public Offer(String investor, BigDecimal amount, BigDecimal equity, String comment) {
		super();
		this.investor = investor;
		this.amount = amount;
		this.equity = equity;
		this.comment = comment;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInvestor() {
		return investor;
	}

	public void setInvestor(String investor) {
		this.investor = investor;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getEquity() {
		return equity;
	}

	public void setEquity(BigDecimal equity) {
		this.equity = equity;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
