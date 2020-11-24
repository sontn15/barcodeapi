package com.sh.barcodeapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tblDVT")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UnitEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdDVT", nullable = false)
    private Long id;

    @Column(name = "MaDVT", nullable = false)
    private Long maUnit;

    @Column(name = "DVT", nullable = false)
    private String name;

    @Column(name = "Ghichu")
    private String note;

    @Column(name = "Active", nullable = false)
    private boolean status;

    @Column(name = "Ma_CH", nullable = false)
    private Long storeId;

}
