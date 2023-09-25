package com.sj.t_safer.domain.metal;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "product_process_map")
public class ProductProcessMap {
    @Id
    @Column(name = "map_no")
    private int mapNo;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_no")
    private Product product;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "process_metal_no")
    private ProcessMetal processMetal;

    public int getMapNo() {
        return mapNo;
    }

    public void setMapNo(int mapNo) {
        this.mapNo = mapNo;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProcessMetal getProcessMetal() {
        return processMetal;
    }

    public void setProcessMetal(ProcessMetal processMetal) {
        this.processMetal = processMetal;
    }
}
