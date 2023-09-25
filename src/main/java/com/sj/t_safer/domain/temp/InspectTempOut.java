package com.sj.t_safer.domain.temp;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "inspect_temp_out")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Getter
public class InspectTempOut {

    @Id
    @Column(name = "inspect_nomal_out_no")
    private Integer inspectNomalOutNo;

    @Type(type = "jsonb")
    @Column(name = "limit_info", columnDefinition = "jsonb")
    private List limitInfo;

    @Column(name = "detection_date")
    private LocalDateTime detectionDate;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "reason", columnDefinition = "TEXT")
    private String reason;

    @Column(name = "register_date")
    private LocalDateTime registerDate;
}
