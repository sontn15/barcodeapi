package com.sh.barcodeapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_nBarCode")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BarcodeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "IdCH", nullable = false)
    private Long storeId;

    @Column(name = "nMaSP", nullable = false)
    private String itemCode;

    @Column(name = "nBarCode", nullable = false)
    private String barcode;

    @Column(name = "mSL")
    private Long quantity;

    @Column(name = "mGia")
    private Long price;

}
