package com.sj.t_safer.repository;

import com.sj.t_safer.domain.temp.InspectTempIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InspectInRepository extends JpaRepository<InspectTempIn, Integer> {
    @Query(value = "select count(*)\n" +
            "from hcp.inspect_normal_in iti\n" +
            "where to_char(iti.detection_date, 'YYYY-MM-DD') between ?1 and ?2 ;", nativeQuery = true)
    Long getCount(@Param("startDate") String startDate, @Param("endDate") String endDate);
}
