package com.sj.t_safer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TempDashResult {

    private String date;
    private String time;
    private String limitRange;
    private String result;

    public TempDashResult(String date, String time, String limitRange, String result) {
        System.out.println("dto TempDashResult");
        this.date = date;
        this.time = time;
        this.limitRange = limitRange;
        this.result = result;
    }
}
