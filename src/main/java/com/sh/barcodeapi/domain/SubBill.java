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
@ApiModel("SubBill Model Domain")
public class SubBill {

    private Long id;

    private Item item;

    private String itemName;

    private Unit unit;

    private Double quantity;

    private Long price;

    private Long totalMoney;

    private String tyLeCK;

    private Long tongTienCK;

    private Long unitCoSo;

    private Long heSoCoSo;

}
