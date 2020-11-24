package com.sh.barcodeapi.web.rest;

import com.sh.barcodeapi.domain.Bill;
import com.sh.barcodeapi.domain.Item;
import com.sh.barcodeapi.domain.Store;
import com.sh.barcodeapi.web.rest.request.BillRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Api(tags = "Barcode Resource")
@RequestMapping("/api/v1/")
@Validated
public interface BarcodeResource {

    @ApiOperation("Login")
    @GetMapping("/stores/login")
    Store login(@RequestParam(value = "username") @NotBlank String username,
                @RequestParam(value = "password") @NotBlank String password,
                @RequestParam(value = "serial") @NotBlank String serial);

    @ApiOperation("Get all items of store")
    @GetMapping("/stores/{storeCode}/items")
    List<Item> findAllItems(@PathVariable(value = "storeCode") @NotBlank String storeCode);

    @ApiOperation("Get item of store by barcode")
    @GetMapping("/stores/{storeCode}/items/{barcode}")
    Item getItemByBarcode(@PathVariable(value = "storeCode") @NotBlank String storeCode,
                          @PathVariable(value = "barcode") @NotBlank String barcode);

    @ApiOperation("Create bill")
    @PostMapping("/stores/customers/bills")
    Bill createOrUpdateBill(@RequestBody @Valid BillRequest request);

}
