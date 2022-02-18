package com.kakaroo.springlocationjpa.web;

import java.sql.Date;
import java.time.LocalDateTime;

import com.kakaroo.springlocationjpa.entity.LocationEntity;

import lombok.Getter;

@Getter
public class LocationReadDto {
	private Long id;
	private LocalDateTime time;
	private Double latitude;
	private Double longitude;
	
	public LocationReadDto(LocationEntity entity) {
		this.id = entity.getId();
		this.time = entity.getTime();
		this.latitude = entity.getLatitude();
		this.longitude = entity.getLongitude();
	}

}
