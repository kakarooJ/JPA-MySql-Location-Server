package com.kakaroo.springlocationjpa.web;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
		Double latitude = 37.3898897;
		Double longitude = 126.9593416;
		String mTime = "";
		Double reviseValueA = 0d;
		Double reviseValueB = 0d;
		
		for (int i = 0; i < 1; i++) {
			
			//java.sql.TimeStamp
			SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			Date date = cal.getTime();
			formatter.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
			String today = formatter.format(date);
			
			Timestamp ts = Timestamp.valueOf(today);
			//System.out.println("timestamp : "+ts);
			
			String time = ts.toString();
			mTime = time.substring(0, time.length()-2);
						
			//LocalDateTime ldt = ts.toLocalDateTime();
			//System.out.println("SQL type of timestamp : "+ldt);
						
			reviseValueA = (int) Math.floor(Math.random()*20) *0.0003;//i * 0.001;
			reviseValueB = (int) Math.floor(Math.random()*12) *0.0006;//i * 0.002;
			
			System.out.println("reviseValueA,reviseValueB: "+reviseValueA+","+reviseValueB);
			
			System.out.println("<item save> time:"+mTime +", latitude,longitude:"+(latitude+reviseValueA)+","+(longitude+reviseValueB));
			
			repository
					.save(LocationEntity.builder().time(mTime).latitude(latitude+reviseValueA).longitude(longitude+reviseValueB).build());
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return "<item save> time: "+mTime +", latitude,longitude: "+(latitude+reviseValueA)+","+(longitude+reviseValueB);
	}
	
	@PostMapping("/post")
	public Long create(@RequestBody LocationCreateDto createDto) {
		Long ret = locationService.create(createDto);
		System.out.println("<post> id:"+ret +", time:"+createDto.getTime().toString() +", latitude:"+createDto.getLatitude().toString()+", longitude:"+createDto.getLongitude().toString());
		return ret;		
	}
	
	@GetMapping("/get/list")
	public List<LocationReadDto> readAll() {
		return locationService.readAll();
	}
	
	@DeleteMapping("/delete/{id}")
	public Long delete(@PathVariable Long id) {
		System.out.println("<delete> id:"+id);
		return locationService.delete(id);
	}
	
	@DeleteMapping("/delete/all")
	public Long deleteAll() {
		System.out.println("<deleteAll>");
		return locationService.deleteAll();
	}

}
