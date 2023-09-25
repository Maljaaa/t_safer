package com.sj.t_safer.repository;

import com.sj.t_safer.domain.metal.InspectMetalNg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InspectNgRepository extends JpaRepository<InspectMetalNg, Integer> {

    @Query(value = "select SUM(CASE\n" +
            "    WHEN (cast(dm.collect_data as JSON) -> 'limit_info' -> 0 ->> 'control_point_type' = 'SUS' AND cast(cast(dm.collect_data as JSON) -> 'limit_info' -> 0 ->> 'range_value_1' as float) >= 5.0)\n" +
            "  OR (cast(dm.collect_data as JSON) -> 'limit_info' -> 0 ->> 'control_point_type' = 'FE' AND cast(cast(dm.collect_data as JSON) -> 'limit_info' -> 0 ->> 'range_value_1' as float) >= 4.0)\n" +
            "    THEN 1\n" +
            "    ELSE 0\n" +
            "  END) as \"부적합\"\n" +
            "from hcp.data_metal dm\n" +
            "where\n" +
            "to_char(to_timestamp(dm.get_date), 'YYYY-MM-DD')\n" +
            "between ?1 and ?2 ; ", nativeQuery = true)
    Long getCount(@Param("startDate") String startDate, @Param("endDate") String endDate);




}
