package com.sj.t_safer.domain.metal;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "inspect_metal_ng")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class InspectMetalNg {

    @Id
    @Column(name = "inspect_metal_ng_no")
    private Integer inspectMetalNgNo;

    @Type(type = "jsonb")
    @Column(name = "food_info", columnDefinition = "jsonb")
    private Map<String, Object> foodInfo;

    @Column(name = "detection_date")
    private LocalDateTime detectionDate;

    @Lob
    @Column(name = "reason", columnDefinition = "TEXT")
    private String reason;

    @Lob
    @Column(name = "take_action", columnDefinition = "TEXT")
    private String takeAction;

    @Column(name = "charge_user_no")
    private int chargeUserNo;

    @Column(name = "charge_user_name", length = 100)
    private String chargeUserName;

    @Column(name = "register_date")
    private LocalDateTime registerDate;

    public int getInspectMetalNgNo() {
        return inspectMetalNgNo;
    }

    public void setInspectMetalNgNo(int inspectMetalNgNo) {
        this.inspectMetalNgNo = inspectMetalNgNo;
    }

    public Map<String, Object> getFoodInfo() {
        return foodInfo;
    }

    public void setFoodInfo(Map<String, Object> foodInfo) {
        this.foodInfo = foodInfo;
    }

    public LocalDateTime getDetectionDate() {
        return detectionDate;
    }

    public void setDetectionDate(LocalDateTime detectionDate) {
        this.detectionDate = detectionDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTakeAction() {
        return takeAction;
    }

    public void setTakeAction(String takeAction) {
        this.takeAction = takeAction;
    }

    public int getChargeUserNo() {
        return chargeUserNo;
    }

    public void setChargeUserNo(int chargeUserNo) {
        this.chargeUserNo = chargeUserNo;
    }

    public String getChargeUserName() {
        return chargeUserName;
    }

    public void setChargeUserName(String chargeUserName) {
        this.chargeUserName = chargeUserName;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }
}
