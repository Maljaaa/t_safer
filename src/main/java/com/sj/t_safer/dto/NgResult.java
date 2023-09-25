package com.sj.t_safer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NgResult {

    Integer ngCount;

    public NgResult(Integer ngCount) {
        System.out.println("dto NgResult");
        this.ngCount = ngCount;
    }
}
