package com.itms.echallan_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "evidences")
public class Evidences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileType;

    @Column(name="image_path")
    private String imagePath;

    @Column(name = "image_Order")
    private Integer imageOrder;

    @Column(name = "uploaded_at")
    private LocalDateTime uploaded_at;

    @ManyToOne
    @JoinColumn(name = "violation_id")
    private Violation violation;
}
