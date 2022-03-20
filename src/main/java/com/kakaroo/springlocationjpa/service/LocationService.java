package com.kakaroo.springlocationjpa.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	
	private final String tableName = "locations";
	private final LocationRepository repository;

	@PersistenceContext
	private final EntityManager entityManager;
	
	@Transactional
	public Long create(LocationCreateDto createDto) {
		return repository.save(createDto.toEntity()).getId();
	}
	
	@Transactional(readOnly = true)
	public List<LocationReadDto> readAll() {
		List<LocationReadDto> list = repository.findAll().stream().map(entity -> new LocationReadDto(entity)).collect(Collectors.toList());
		return list;		
	}
	
	@Transactional(readOnly = true)
	public List<LocationReadDto> readAllByUserId(int id) {
		List<LocationReadDto> list = repository.findByUserId(id).stream().map(entity -> new LocationReadDto(entity)).collect(Collectors.toList());
		return list;		
	}
	
	@Transactional
	public Long delete(Long id) {
		LocationEntity entity = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("delete error!!! id: " + id));
		repository.delete(entity);
		return id;
	}
	
	@Transactional
	public int deleteAllByUserId(int userId) {
		List<LocationEntity> entity = repository.findByUserId(userId);
		if(entity != null) {
			entity.forEach(item -> repository.delete(item));
			
			String jpql = "ALTER TABLE " + tableName +" AUTO_INCREMENT = 1";
			entityManager.createNativeQuery(jpql).executeUpdate();
			return entity.size();	
		}
		return 0;		
	}
	
	@Transactional
	public Long deleteAll() {		
		repository.deleteAll();
		Long count = repository.count();
		
		String jpql = "ALTER TABLE " + tableName +" AUTO_INCREMENT = 1";
		entityManager.createNativeQuery(jpql).executeUpdate();
				
		return count;
	}	
	
}
