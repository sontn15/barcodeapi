package com.sh.barcodeapi.domain;

import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ApiModel("Result Model")
public class Result implements Serializable {

    private Long value;

    private String result;

    private String responseDate;


}
