package com.sh.barcodeapi.service;


import com.sh.barcodeapi.domain.Bill;
import com.sh.barcodeapi.domain.Item;
import com.sh.barcodeapi.domain.Store;
import com.sh.barcodeapi.web.rest.request.BillRequest;

import java.util.List;

public interface BarcodeService {

    Store login(Store store);

    List<Item> findAllItemsInStore(Long storeId);

    Item findItemByStoreAndBarcode(Long storeId, String barcode);

    Bill createOrUpdateBill(BillRequest request);

}
