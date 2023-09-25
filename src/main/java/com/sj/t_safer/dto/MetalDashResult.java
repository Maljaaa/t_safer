package com.sj.t_safer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MetalDashResult {

    private String date;
    private String time;
    private String equipment;
    private String limitRange;
    private String productItem;
    private String result;

    public MetalDashResult(String date, String time, String equipment, String limitRange, String productItem, String result) {
        System.out.println("dto MetalDashResult");
        this.date = date;
        this.time = time;
        this.equipment = equipment;
        this.limitRange = limitRange;
        this.productItem = productItem;
        this.result = result;
    }

    @Override
    public String toString() {
        return "DashResult{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", equipment='" + equipment + '\'' +
                ", limitRange='" + limitRange + '\'' +
                ", productItem='" + productItem + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
