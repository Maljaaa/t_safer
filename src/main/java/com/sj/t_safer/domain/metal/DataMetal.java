package com.sj.t_safer.domain.metal;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "data_metal")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Getter
@Setter
public class DataMetal {

    @Id
    @Column(name = "get_date", nullable = false)
    private Long getDate;

    @Column(name = "product_no", nullable = false)
    private Integer productNo;

    @Lob
    @Column(name = "collect_data", columnDefinition = "TEXT")
    private String collectData;

    @OneToMany(mappedBy = "dataMetal")
    @PrimaryKeyJoinColumn
    private List<Product> products = new ArrayList<>();
}
