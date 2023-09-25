package com.sj.t_safer.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class Bubble2Result {

    private String title;
    private BigInteger date;
    private Float y;
    private String color;

    public Bubble2Result(String title, BigInteger date, Float y, String color) {
        System.out.println("dto Bubble2Result");
        this.title = title;
        this.date = date;
        this.y = y;
        this.color = color;
    }

    @Override
    public String toString() {
        return "BubbleResult2{" +
                "title='" + title + '\'' +
                ", date=" + date +
                ", y=" + y +
                ", color='" + color + '\'' +
                '}';
    }
}
