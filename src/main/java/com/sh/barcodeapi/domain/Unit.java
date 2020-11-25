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
@ApiModel("Unit Model Domain")
public class Unit {

    private Long id;
    private String name;
    private String note;
    private Long storeId;
    private boolean status;

}
