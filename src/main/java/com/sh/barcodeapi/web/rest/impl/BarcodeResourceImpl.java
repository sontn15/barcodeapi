package com.sh.barcodeapi.web.rest.impl;

import com.sh.barcodeapi.domain.*;
import com.sh.barcodeapi.service.BarcodeService;
import com.sh.barcodeapi.web.rest.BarcodeResource;
import com.sh.barcodeapi.web.rest.request.BillRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class BarcodeResourceImpl implements BarcodeResource {

    private final BarcodeService service;

    @Autowired
    public BarcodeResourceImpl(BarcodeService service) {
        this.service = service;
    }

    @Override
    public Store login(String username, String password, String serial) {
        Store store = Store.builder()
                .username(username)
                .password(password)
                .serialDevice(serial)
                .build();

        return service.login(store);
    }

    @Override
    public List<Item> findAllItems(Long storeId) {
        return service.findAllItemsInStore(storeId);
    }

    @Override
    public Item getItemByBarcodeOfStore(Long storeId, String barcode) {
        return service.findItemByStoreAndBarcode(storeId, barcode);
    }

    @Override
    public Bill createOrUpdateBill(BillRequest request) {
        return service.createOrUpdateBill(request);
    }

    @Override
    public List<Unit> findAllUnits(Long storeId) {
        return service.findAllUnitsInStore(storeId);
    }

    @Override
    public List<Barcode> findAllBarCodes(Long storeId) {
        return service.findAllBarCodesInStore(storeId);
    }

    @Override
    public Result checkDataIsChange(Long storeId) {
        return service.checkDataIsChange(storeId);
    }

    @Override
    public Result updateMarkDataChange(Long storeId, Boolean status) {
        return service.updateMarkDataChange(storeId, status);
    }

}
