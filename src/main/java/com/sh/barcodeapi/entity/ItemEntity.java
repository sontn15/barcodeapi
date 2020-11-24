package com.sh.barcodeapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tblSanPham")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdSP", nullable = false)
    private Long id;

    @Column(name = "MaSP", nullable = false)
    private String code;

    @Column(name = "TenSP", nullable = false)
    private String name;

    @Column(name = "BARCODE")
    private String barcode;

    @Column(name = "GiaNhap")
    private Long giaNhap;

    @Column(name = "GiaBanLe")
    private Long giaBanLe;

    @Column(name = "GiaBuon")
    private Long giaBuon;

    @Column(name = "GiaDaiLy")
    private Long giaDaiLy;

    @Column(name = "DVT")
    private Long unitMin;
    @Column(name = "DVT_MD")
    private Long unitDefault;

    @Column(name = "DVT1")
    private Long unit1;
    @Column(name = "QuyCach1")
    private Long quyCach1;
    @Column(name = "GiaQD1")
    private Long giaQuyDoi1;

    @Column(name = "DVT2")
    private Long unit2;
    @Column(name = "QuyCach2")
    private Long quyCach2;
    @Column(name = "GiaQD2")
    private Long giaQuyDoi2;

    @Column(name = "Active", nullable = false)
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "Ma_CH", referencedColumnName = "MA_CH")
    private StoreEntity store;
}
