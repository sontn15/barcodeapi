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
@ApiModel("Barcode Model Domain")
public class Barcode {

    private Long id;
    private Long storeId;
    private String itemCode;
    private String barcode;
    private Long quantity;
    private Long price;

}
