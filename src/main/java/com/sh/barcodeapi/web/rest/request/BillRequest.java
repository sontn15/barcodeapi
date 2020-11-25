package com.sh.barcodeapi.web.rest.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sh.barcodeapi.domain.SubBill;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("BIllRequest model")
public class BillRequest {

    private Long id;

    private Long totalMoney;

    private String description;

    @NotNull(message = "storeId is not null")
    private Long storeId;

    @NotNull(message = "lstSubBills is not null")
    private List<SubBill> lstSubBills;
}
