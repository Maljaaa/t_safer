package com.sj.t_safer.repository;

import com.sj.t_safer.domain.metal.DataMetal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataMetalRepository extends JpaRepository<DataMetal, Long> {

    @Query(value = "select TO_CHAR(TO_TIMESTAMP(dm.get_date), 'YYYY-MM-DD') as \"날짜\", \n" +
            "TO_CHAR(TO_TIMESTAMP(dm.get_date), 'HH24:MI:SS') as \"시간\", \n" +
            "concat('금속검출기1') as \"장비\", \n" +
            "concat('<= ', cast(dm.collect_data as JSON) -> 'limit_info' -> 0 ->> 'control_point_type', ' ', cast(dm.collect_data as JSON) -> 'limit_info' -> 0 ->> 'range_value_1', 'mm') as \"한계볌위(mm)\", \n" +
            "cast(dm.collect_data as JSON) -> 'food_info' -> 0 ->> 'food' as \"생산품목\",\n" +
            "case\n" +
            "\t\twhen cast(dm.collect_data as JSON) -> 'limit_info' -> 0 ->> 'control_point_type' = 'SUS' \n" +
            "\t\t\tAND cast(cast(dm.collect_data as JSON) -> 'limit_info' -> 0 ->> 'range_value_1' as float) >= 5.0 \n" +
            "\t\t\t\tthen '부적합'\n" +
            "\t\twhen cast(dm.collect_data as JSON) -> 'limit_info' -> 0 ->> 'control_point_type' = 'FE' \n" +
            "\t\t\tAND cast(cast(dm.collect_data as JSON) -> 'limit_info' -> 0 ->> 'range_value_1' as float) >= 4.0 \n" +
            "\t\t\t\tthen '부적합'\n" +
            "\t    else '적합'\n" +
            "    end as \"결과\"\n" +
            "from hcp.data_metal dm \n" +
            "where TO_CHAR(TO_TIMESTAMP(dm.get_date), 'YYYY-MM-DD') \n" +
            "between ?1 and ?2 ;", nativeQuery = true)
    List<Object[]> findResultsByDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Query(value = "SELECT\n" +
            "  SUM(CASE\n" +
            "    WHEN (cast(dm.collect_data as JSON) -> 'limit_info' -> 0 ->> 'control_point_type' = 'SUS' AND cast(cast(dm.collect_data as JSON) -> 'limit_info' -> 0 ->> 'range_value_1' as float) < 5.0)\n" +
            "  \t\tOR (cast(dm.collect_data as JSON) -> 'limit_info' -> 0 ->> 'control_point_type' = 'FE' AND cast(cast(dm.collect_data as JSON) -> 'limit_info' -> 0 ->> 'range_value_1' as float) < 4.0)\n" +
            "    THEN 1\n" +
            "    ELSE 0\n" +
            "  END) AS \"적합\",\n" +
            "  SUM(CASE\n" +
            "    WHEN (cast(dm.collect_data as JSON) -> 'limit_info' -> 0 ->> 'control_point_type' = 'SUS' AND cast(cast(dm.collect_data as JSON) -> 'limit_info' -> 0 ->> 'range_value_1' as float) >= 5.0)\n" +
            "  OR (cast(dm.collect_data as JSON) -> 'limit_info' -> 0 ->> 'control_point_type' = 'FE' AND cast(cast(dm.collect_data as JSON) -> 'limit_info' -> 0 ->> 'range_value_1' as float) >= 4.0)\n" +
            "    THEN 1\n" +
            "    ELSE 0\n" +
            "  END) as \"부적합\"\n" +
            "FROM hcp.data_metal dm\n" +
            "where\n" +
            "to_char(to_timestamp(dm.get_date), 'YYYY-MM-DD')\n" +
            "between ?1 and ?2 ; ", nativeQuery = true)
    List<Object[]> findResultsForPie(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Query(value = "select case\n" +
            "\t\twhen cast(dm.collect_data as JSON) -> 'limit_info' -> 0 ->> 'control_point_type' = 'SUS' \n" +
            "\t\t\tAND cast(cast(dm.collect_data as JSON) -> 'limit_info' -> 0 ->> 'range_value_1' as float) >= 5.0 \n" +
            "\t\t\t\tthen '부적합'\n" +
            "\t\twhen cast(dm.collect_data as JSON) -> 'limit_info' -> 0 ->> 'control_point_type' = 'FE' \n" +
            "\t\t\tAND cast(cast(dm.collect_data as JSON) -> 'limit_info' -> 0 ->> 'range_value_1' as float) >= 4.0 \n" +
            "\t\t\t\tthen '부적합'\n" +
            "\t    else '적합'\n" +
            "    end as \"title\",\n" +
            "    dm.get_date as \"date\",\n" +
            " cast(dm.collect_data as JSON) -> 'limit_info' -> 0 ->> 'range_value_1' as \"y\"\n" +
            " from hcp.data_metal dm \n" +
            " where\n" +
            " TO_CHAR(TO_TIMESTAMP(dm.get_date), 'YYYY-MM-DD') \n" +
            "between ?1 and ?2 ;", nativeQuery = true)
    List<Object[]> findResultsForBubble(@Param("startDate") String startDate, @Param("endDate") String endDate);
}
