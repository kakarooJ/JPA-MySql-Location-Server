package com.kakaroo.springlocationjpa.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kakaroo.springlocationjpa.entity.LocationEntity;
import com.kakaroo.springlocationjpa.entity.LocationRepository;
import com.kakaroo.springlocationjpa.web.LocationCreateDto;
import com.kakaroo.springlocationjpa.web.LocationReadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LocationService {
	
	private final LocationRepository repository;
	
	@Transactional
	public Long create(LocationCreateDto createDto) {
		return repository.save(createDto.toEntity()).getId();
	}
	
	@Transactional(readOnly = true)
	public List<LocationReadDto> readAll() {
		List<LocationReadDto> list = repository.findAll().stream().map(entity -> new LocationReadDto(entity)).collect(Collectors.toList());
		return list;		
	}
	
	@Transactional
	public Long delete(Long id) {
		LocationEntity entity = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("delete error!!! id: " + id));
		repository.delete(entity);
		return id;
	}
	
	@Transactional
	public Long deleteAll() {		
		repository.deleteAll();
		return repository.count();
	}

}
