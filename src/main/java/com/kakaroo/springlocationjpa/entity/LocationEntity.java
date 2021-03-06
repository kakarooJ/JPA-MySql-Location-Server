package com.kakaroo.springlocationjpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity

@Table(name = "locations")
public class LocationEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int userId;
	private String time;
	private Double latitude;
	private Double longitude;
	
	@Builder
	public LocationEntity(Long id, int userId, String time, Double latitude, Double longitude) {
		this.id = id;
		this.userId = userId;
		this.time = time;
		this.latitude = latitude;
		this.longitude = longitude;
	}
}
