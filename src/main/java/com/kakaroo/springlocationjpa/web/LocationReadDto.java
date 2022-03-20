package com.kakaroo.springlocationjpa.web;

import com.kakaroo.springlocationjpa.entity.LocationEntity;

import lombok.Getter;

@Getter
public class LocationReadDto {
	private Long id;
	private int userId;
	private String time;
	private Double latitude;
	private Double longitude;
	
	public LocationReadDto(LocationEntity entity) {
		this.id = entity.getId();
		this.userId = entity.getUserId();
		this.time = entity.getTime();
		this.latitude = entity.getLatitude();
		this.longitude = entity.getLongitude();
	}

}
