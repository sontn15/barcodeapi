package com.sh.barcodeapi.domain.desktop;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("BillDTO Model Domain")
public class BillDTO {

    private Long STT_REC;

    private String Ten_KH;

    private String DiaChi;

    private Long TG_TIEN;

    private String Ngay;

    private Date GIO;

    private String DienGiai;

    private boolean STATUS;

    private String Ma_KH;

    private Long MA_NV;

    private Long Ma_CH;

    private Long TrangThai;

    private String Ma_NV;

    private String Ten_NV;

}
