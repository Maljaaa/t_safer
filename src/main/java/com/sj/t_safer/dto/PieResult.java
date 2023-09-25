package com.sj.t_safer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PieResult {

    private Long okCount;
    private Long ngCount;

    public PieResult(Long okCount, Long ngCount) {
        System.out.println("dto PieResult");
        this.okCount = okCount;
        this.ngCount = ngCount;
    }

    @Override
    public String toString() {
        return "PieResult{" +
                "okCount=" + okCount +
                ", ngCount=" + ngCount +
                '}';
    }
}
