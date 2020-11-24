package com.sh.barcodeapi.domain;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Item Model Domain")
public class Item {

    private Long id;

    private String code;

    private String name;

    private String barcode;

    private Long giaNhap;

    private Long giaBanLe;

    private Long giaBuon;

    private Long giaDaiLy;

    private Long unitMin;
    private Unit unitMinObj;

    private Long unitDefault;
    private Unit unitDefaultObj;

    private Long unit1;
    private Unit unit1Obj;
    private Long quyCach1;
    private Long giaQuyDoi1;

    private Long unit2;
    private Unit unit2Obj;
    private Long quyCach2;
    private Long giaQuyDoi2;

    private boolean status;

    private Store store;
}
