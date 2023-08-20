package com.book.bookingrestaurantapi.jsons;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationRest {
	@JsonProperty("id")
	private Long id;
	@JsonProperty("locator")
	private String locator;
	@JsonProperty("person")
	private String person;
	@JsonProperty("date")
	private Date date;
	@JsonProperty("turnId")
	private String turnId ;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLocator() {
		return locator;
	}
	public void setLocator(String locator) {
		this.locator = locator;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTurn() {
		return turnId;
	}
	public void setTurn(String turn) {
		this.turnId = turn;
	}

	
}
