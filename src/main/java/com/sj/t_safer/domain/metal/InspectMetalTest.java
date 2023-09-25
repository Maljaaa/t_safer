package com.sj.t_safer.domain.metal;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.sql.Struct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "inspect_metal_test")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@NamedStoredProcedureQuery(
        name = "procMetal",
        procedureName = "proc_metal",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.OUT, type = Struct.class, name = "shootingM")
        }
)
@Getter
public class InspectMetalTest {

    @Id
    @Column(name = "inspect_metal_test_no")
    private Integer inspectMetalTestNo;

    @Type(type = "jsonb")
    @Column(name = "food_info", columnDefinition = "jsonb")
    private List foodInfo;

    @Type(type = "jsonb")
    @Column(name = "limit_info", columnDefinition = "jsonb")
    private List limitInfo;

    @Column(name = "detection_date")
    private LocalDateTime detectionDate;

    @Column(name = "control_point_type_code", length = 20)
    private String controlPointTypeCode;

    @Column(name = "test_judge_code", length = 20)
    private String testJudgeCode;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "reason", columnDefinition = "TEXT")
    private String reason;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "take_action", columnDefinition = "TEXT")
    private String takeAction;

    @Column(name = "charge_user_no")
    private Integer chargeUserNo;

    @Column(name = "charge_user_name", length = 100)
    private String chargeUserName;

    @Column(name = "register_date")
    private LocalDateTime registerDate;
}
