package com.kakaroo.springlocationjpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class LocationEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String time;
	private Double latitude;
	private Double longitude;
	
	@Builder
	public LocationEntity(Long id, String time, Double latitude, Double longitude) {
		this.id = id;
		this.time = time;
		this.latitude = latitude;
		this.longitude = longitude;
	}
}
