package com.sh.barcodeapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tblPhieu")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STT_REC", nullable = false)
    private Long id;

    @Column(name = "Ten_KH")
    private String customerName;

    @Column(name = "DiaChi")
    private String customerAddress;

    @Column(name = "TG_TIEN")
    private Long totalMoney;

    @Column(name = "Ngay")
    private String day;

    @Column(name = "GIO")
    private Date createdDate;

    @Column(name = "DienGiai")
    private String description;

    @Column(name = "STATUS")
    private boolean status;

    @Column(name = "Ma_CH", nullable = false)
    private Long storeId;

    @OneToMany(mappedBy = "bill", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SubBillEntity> lstSubBills;

    @Column(name = "TrangThai", nullable = false)
    private Long statusBillDesktop;
}
