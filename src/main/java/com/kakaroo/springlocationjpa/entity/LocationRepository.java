package com.kakaroo.springlocationjpa.entity;

import java.util.List;

//import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LocationRepository extends JpaRepository<LocationEntity, Long>{
	@Query("select m from LocationEntity m where m.userId = :userId")
	List<LocationEntity> findByUserId(int userId);
//	@Query(value = "SELECT * FROM LocationEntity l where l.userId = ?1", nativeQuery = true)
//	List<LocationEntity> findByUserId(@Param("userId") int userId);
	
}
