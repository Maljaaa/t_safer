package com.sj.t_safer.domain.metal;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "product_no")
    private int productNo;

    @MapsId
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "get_date")
    private DataMetal dataMetal;


    @Column(name = "use_yn", length = 1)
    private String useYn;

    @Column(name = "type", length = 10)
    private String type;

    @OneToMany(mappedBy = "product")
    private List<ProductProcessMap> productProcessMaps = new ArrayList<>();

    public DataMetal getDataMetal() {
        return dataMetal;
    }

    public void setDataMetal(DataMetal dataMetal) {
        this.dataMetal = dataMetal;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ProductProcessMap> getProductProcessMaps() {
        return productProcessMaps;
    }

    public void setProductProcessMaps(List<ProductProcessMap> productProcessMaps) {
        this.productProcessMaps = productProcessMaps;
    }
}
