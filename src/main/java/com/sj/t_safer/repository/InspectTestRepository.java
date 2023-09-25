package com.sj.t_safer.repository;

import com.sj.t_safer.domain.metal.InspectMetalTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InspectTestRepository extends JpaRepository<InspectMetalTest, Integer> {

    @Query(value = "select count(*)\n" +
            "from hcp.inspect_metal_test imt \n" +
            "where to_char(imt.detection_date, 'YYYY-MM-DD') between ?1 and ?2 ;", nativeQuery = true)
    Long getCount(@Param("startDate") String startDate, @Param("endDate") String endDate);
}
