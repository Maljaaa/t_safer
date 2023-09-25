package com.sj.t_safer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TempResult {
    private String title;
    private Long inCount;
    private Long outCount;

    public TempResult(String title, Long inCount, Long outCount) {
        System.out.println("dto TempResult");
        this.title = title;
        this.inCount = inCount;
        this.outCount = outCount;
    }
}
