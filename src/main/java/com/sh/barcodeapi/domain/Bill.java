package com.sh.barcodeapi.domain;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Bill Model Domain")
public class Bill {

    private Long id;

    private Long totalMoney;

    private String day;

    private Date createdDate;

    private boolean status;

    private Long storeId;

    private List<SubBill> lstSubBills;

}
