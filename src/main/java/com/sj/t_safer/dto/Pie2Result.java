package com.sj.t_safer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pie2Result {

    private Long inCount;
    private Long outCount;

    public Pie2Result(Long inCount, Long outCount) {
        System.out.println("dto Pie2Result");
        this.inCount = inCount;
        this.outCount = outCount;
    }
}
