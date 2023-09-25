package com.sj.t_safer.domain.metal;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "process_metal")
public class ProcessMetal {

    @Id
    @Column(name = "process_metal_no")
    private int processMetalNo;

    @Column(name = "process_name", length = 100, nullable = false)
    private String processName;

    @Column(name = "use_yn", length = 1)
    private String useYn;

    @OneToMany(mappedBy = "processMetal")
    private List<ProductProcessMap> productProcessMaps = new ArrayList<>();

    public int getProcessMetalNo() {
        return processMetalNo;
    }

    public void setProcessMetalNo(int processMetalNo) {
        this.processMetalNo = processMetalNo;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public List<ProductProcessMap> getProductProcessMaps() {
        return productProcessMaps;
    }

    public void setProductProcessMaps(List<ProductProcessMap> productProcessMaps) {
        this.productProcessMaps = productProcessMaps;
    }
}
