package com.sh.barcodeapi.domain;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Store Model Domain")
public class Store implements Serializable {

    private Long id;

    private String code;

    private String name;

    private Long type;

    private String address;

    private String note;

    private String username;

    private String password;

    private Long quantityDevice;

    private String serialDevice;
}
