package com.sh.barcodeapi.web.rest.impl;

import com.sh.barcodeapi.domain.Bill;
import com.sh.barcodeapi.domain.Item;
import com.sh.barcodeapi.domain.Store;
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
    public List<Item> findAllItems(String storeCode) {
        return service.findAllItemsByStoreCode(storeCode);
    }

    @Override
    public Item getItemByBarcode(String storeCode, String barcode) {
        return service.findItemByStoreAndBarcode(storeCode, barcode);
    }

    @Override
    public Bill createOrUpdateBill(BillRequest request) {
        return service.createOrUpdateBill(request);
    }

}
