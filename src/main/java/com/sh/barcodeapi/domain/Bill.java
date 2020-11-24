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

    private String customerName;

    private String customerAddress;

    private Long totalMoney;

    private String day;

    private Date createdDate;

    private String description;

    private boolean status;

    private Long storeId;

    private List<SubBill> lstSubBills;

    private Long statusBillDesktop;

}
