package com.sh.barcodeapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tblCuaHang")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MA_CH", nullable = false)
    private Long id;

    @Column(name = "CODE_CH", nullable = false, unique = true)
    private String code;

    @Column(name = "TEN_CH", nullable = false)
    private String name;

    @Column(name = "LOAI_CH")
    private Long type;

    @Column(name = "DIA_CHI")
    private String address;

    @Column(name = "GHI_CHU")
    private String note;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "SOLUONG_THIETBI")
    private Long quantityDevice;

    @Column(name = "SERIAL_NUMBER")
    private String serialDevice;

    @Column(name = "CHANGE_DATA")
    private Boolean changeData;

}
