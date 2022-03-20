package com.kakaroo.springlocationjpa.web;

import java.sql.Date;
import java.time.LocalDateTime;

import com.kakaroo.springlocationjpa.entity.LocationEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LocationCreateDto {
	private int userId;
	private String time;
	private Double latitude;
	private Double longitude;
	
	@Builder
	public LocationCreateDto(int userId, String time, Double latitude, Double longitude) {
		this.userId = userId;
		this.time = time;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public LocationEntity toEntity() {
		return LocationEntity.builder().userId(userId).time(time).latitude(latitude).longitude(longitude).build();
	}

}
