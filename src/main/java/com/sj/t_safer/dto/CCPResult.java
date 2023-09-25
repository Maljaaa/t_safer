package com.sj.t_safer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CCPResult {

    private String title;
    private Long testCount;
    private Long ngCount;

    public CCPResult(String title, Long testCount, Long ngCount) {
        System.out.println("dto CCPResult");
        this.title = title;
        this.testCount = testCount;
        this.ngCount = ngCount;
    }
}
