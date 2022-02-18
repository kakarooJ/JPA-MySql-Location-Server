package com.kakaroo.springlocationjpa.entity;

import java.sql.Date;
import java.time.LocalDateTime;

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
	private LocalDateTime time;
	private Double latitude;
	private Double longitude;
	
	@Builder
	public LocationEntity(Long id, LocalDateTime time, Double latitude, Double longitude) {
		this.id = id;
		this.time = time;
		this.latitude = latitude;
		this.longitude = longitude;
	}
}
