package com.sh.barcodeapi.service;


import com.sh.barcodeapi.domain.Bill;
import com.sh.barcodeapi.domain.Item;
import com.sh.barcodeapi.domain.Store;
import com.sh.barcodeapi.web.rest.request.BillRequest;

import java.util.List;

public interface BarcodeService {

    Store login(Store store);

    List<Item> findAllItemsByStoreCode(String storeCode);

    Item findItemByStoreAndBarcode(String storeCode, String barcode);

    Bill createOrUpdateBill(BillRequest request);

}
