package com.book.bookingrestaurantapi.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="restaurant")
public class Restaurant {
	//esto se utiliza para mapear los datos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID",unique = true,nullable=false)
	private Long id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="IMAGE")
	private String image;
	
	@OneToMany(cascade = CascadeType.ALL,fetch =FetchType.LAZY,mappedBy="restaurant")
	private List<Reservation> Reservations;
	
	@OneToMany(cascade = CascadeType.ALL,fetch =FetchType.LAZY,mappedBy="restaurant")
	private List<Board> boards;
	
	@OneToMany(cascade = CascadeType.ALL,fetch =FetchType.LAZY,mappedBy="restaurant")
	private List<Turn> turns;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Reservation> getReservations() {
		return Reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		Reservations = reservations;
	}

	public List<Board> getBoards() {
		return boards;
	}

	public void setBoards(List<Board> boards) {
		this.boards = boards;
	}

	public List<Turn> getTurns() {
		return turns;
	}

	public void setTurns(List<Turn> turns) {
		this.turns = turns;
	}
}
