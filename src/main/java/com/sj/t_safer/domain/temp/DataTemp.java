package com.sj.t_safer.domain.temp;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity
@Table(name = "data_temp")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Getter
@Setter
public class DataTemp {

    @Id
    @Column(name = "get_date", nullable = false)
    private Long getDate;

    @Column(name = "product_no", nullable = false)
    private Integer productNo;

    @Lob
    @Column(name = "collect_data", columnDefinition = "TEXT")
    private String collectData;
}
