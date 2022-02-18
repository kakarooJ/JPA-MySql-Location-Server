package com.kakaroo.springlocationjpa.web;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kakaroo.springlocationjpa.entity.LocationEntity;
import com.kakaroo.springlocationjpa.entity.LocationRepository;
import com.kakaroo.springlocationjpa.service.LocationService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class LocationApiController {
	private final LocationRepository repository;
	private final LocationService locationService;
	
	@GetMapping("/")
	public String init() {
		Double latitude = 37.3863871;
		Double longitude = 126.9648526;
		for (int i = 0; i < 5; i++) {
			
			//java.sql.TimeStamp
			SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
			Calendar cal = Calendar.getInstance();
			String today = formatter.format(cal.getTime());
			
			Timestamp ts = Timestamp.valueOf(today);
			System.out.println("timestamp : "+ts);		
			
			LocalDateTime ldt = ts.toLocalDateTime();
			System.out.println("SQL type of timestamp : "+ldt);
						
			Double reviseValueA = i * 0.001;
			Double reviseValueB = i * 0.002;			
			repository
					.save(LocationEntity.builder().time(ldt).latitude(latitude+reviseValueA).longitude(longitude+reviseValueB).build());
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "Default item is inserted";
	}
	
	@PostMapping("/post")
	public Long create(@RequestBody LocationCreateDto createDto) {
		return locationService.create(createDto);		
	}
	
	@GetMapping("/get/list")
	public List<LocationReadDto> readAll() {
		return locationService.readAll();
	}
	
	@DeleteMapping("/delete/{id}")
	public Long delete(@PathVariable Long id) {
		return locationService.delete(id);
	}
	
	@DeleteMapping("/deleteall")
	public Long deleteAll() {
		return locationService.deleteAll();
	}

}
