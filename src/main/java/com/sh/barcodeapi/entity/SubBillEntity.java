package com.sh.barcodeapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tblSubCT")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubBillEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TT", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STTRec")
    private BillEntity bill;

    @OneToOne
    @JoinColumn(name = "MaSP", referencedColumnName = "MaSP")
    private ItemEntity item;

    @Column(name = "TenSP")
    private String itemName;

    @OneToOne
    @JoinColumn(name = "DVT", referencedColumnName = "IdDVT")
    private UnitEntity unit;

    @Column(name = "SL")
    private Double quantity;

    @Column(name = "DG")
    private Long price;

    @Column(name = "TTien")
    private Long totalMoney;

    @Column(name = "DVT_B")
    private Long unitCoSo;

    @Column(name = "HS_B")
    private Long heSoCoSo;

}
