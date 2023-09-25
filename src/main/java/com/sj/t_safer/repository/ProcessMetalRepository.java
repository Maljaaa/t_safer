package com.sj.t_safer.repository;

import com.sj.t_safer.domain.metal.ProcessMetal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessMetalRepository extends JpaRepository<ProcessMetal, Integer> {

    @Query(value = "select pm.process_name \n" +
            "from hcp.process_metal pm \n" +
            "left join hcp.product_process_map ppm \n" +
            "on pm.process_metal_no = ppm.map_no \n" +
            "left join hcp.product p \n" +
            "on ppm.product_no = p.product_no \n" +
            "where p.product_no = 11;", nativeQuery = true)
    String getTitle11();

    @Query(value = "select pt.process_name \n" +
            "from hcp.process_temp pt\n" +
            "left join hcp.product_process_map ppm \n" +
            "on pt.process_temp_no = ppm.map_no \n" +
            "left join hcp.product p \n" +
            "on ppm.product_no = p.product_no \n" +
            "where p.product_no = 12;", nativeQuery = true)
    String getTitle12();
}
