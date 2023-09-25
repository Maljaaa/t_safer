package com.sj.t_safer.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class BubbleResult {

    private String title;
    private BigInteger date;
    private Float y;
    private String color;

    public BubbleResult(String title, BigInteger date, Float y, String color) {
        System.out.println("dto BubbleResult");
        this.title = title;
        this.date = date;
        this.y = y;
        this.color = color;
    }

    @Override
    public String toString() {
        return "BubbleResult{" +
                "title='" + title + '\'' +
                ", date=" + date +
                ", y=" + y +
                ", color='" + color + '\'' +
                '}';
    }
}
