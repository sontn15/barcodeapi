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
import javax.validation.constraints.NotNull;
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
    @GetMapping("/stores/{storeId}/items")
    List<Item> findAllItems(@PathVariable(value = "storeId") @NotNull Long storeId);

    @ApiOperation("Get item of store by barcode")
    @GetMapping("/stores/{storeId}/items/barcode/{barcode}")
    Item getItemByBarcodeOfStore(@PathVariable(value = "storeId") @NotNull Long storeId,
                                 @PathVariable(value = "barcode") @NotBlank String barcode);

    @ApiOperation("Create bill")
    @PostMapping("/stores/customers/bills")
    Bill createOrUpdateBill(@RequestBody @Valid BillRequest request);

}
