package com.sh.barcodeapi.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ApiModel("Bill Result Model")
public class BillResult implements Serializable {
    private String createDate;
    private Long totalMoney;
    private List<Bill> lstBills;
    private int totalRecords;
}
