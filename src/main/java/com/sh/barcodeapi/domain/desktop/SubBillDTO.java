package com.sh.barcodeapi.domain.desktop;


import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("SubBillDTO Model Domain")
public class SubBillDTO {

    private Long TT;

    private Long STTRec;

    private String MaSP;

    private String TenSP;

    private Long DVT;

    private Double SL;

    private Long DG;

    private Long TTien;

    private Long d1Nut;

    private Long d2Nut;

    private Double dienTichNut;

    private Long chieuDai;

    private Long chieuRong;

    private Long itemType;

    private Long soLuongVien;

}
