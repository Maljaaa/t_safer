package com.sj.t_safer.repository;

import com.sj.t_safer.domain.temp.DataTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataTempRepository extends JpaRepository<DataTemp, Long> {

    @Query(value = "select TO_CHAR(TO_TIMESTAMP(get_date), 'YYYY-MM-DD') as \"날짜\", \n" +
            "to_char(to_timestamp(get_date), 'HH24:MI:SS') as \"시간\",\n" +
            "concat('10°C <= ', cast(dt.collect_data as JSON) ->> 'temp', '°C', ' <= 18°C') as \"한계범위\",\n" +
            "CASE\n" +
            "        WHEN CAST(CAST(dt.collect_data as JSON) ->> 'temp' as FLOAT) BETWEEN 10 AND 18 THEN\n" +
            "            '적합'\n" +
            "        ELSE \n" +
            "            '부적합'\n" +
            "    END AS \"결과\"\n" +
            "from hcp.data_temp dt\n" +
            "where TO_CHAR(TO_TIMESTAMP(get_date), 'YYYY-MM-DD')\n" +
            "between ?1 and ?2 ;", nativeQuery = true)
    List<Object[]> findTempResultsByDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Query(value = "SELECT \n" +
            "    (SELECT count(*) \n" +
            "    FROM hcp.inspect_normal_in \n" +
            "    where to_char(detection_date, 'YYYY-MM-DD')\n" +
            "    between ?1 and ?2) AS incount,\n" +
            "    (SELECT count(*) \n" +
            "   FROM hcp.inspect_normal_out \n" +
            "  where to_char(detection_date, 'YYYY-MM-DD')\n" +
            " between ?1 and ?2) AS outcount;", nativeQuery = true)
    List<Object[]> findResultsForPie(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Query(value = "select CASE\n" +
            "        WHEN CAST(CAST(dt.collect_data as JSON) ->> 'temp' as FLOAT) BETWEEN 10 AND 18 THEN\n" +
            "            '적합'\n" +
            "        ELSE \n" +
            "            '부적합'\n" +
            "END AS \"title\",\n" +
            "dt.get_date as \"date\",\n" +
            "cast(dt.collect_data as JSON) ->> 'temp' as \"temp\"\n" +
            "from hcp.data_temp dt\n" +
            "where TO_CHAR(TO_TIMESTAMP(get_date), 'YYYY-MM-DD')\n" +
            "between ?1 and ?2 ;", nativeQuery = true)
    List<Object[]> findResultsForBubble(@Param("startDate") String startDate, @Param("endDate") String endDate);
}
