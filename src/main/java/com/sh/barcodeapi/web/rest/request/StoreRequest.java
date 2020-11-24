package com.sh.barcodeapi.web.rest.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("StoreRequest model")
public class StoreRequest {

    @NotBlank(message = "oldPassword is not null")
    private String oldPassword;

    @NotBlank(message = "password is not null")
    private String password;

}
