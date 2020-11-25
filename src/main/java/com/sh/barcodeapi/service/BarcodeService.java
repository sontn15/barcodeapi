package com.sh.barcodeapi.service;


import com.sh.barcodeapi.domain.*;
import com.sh.barcodeapi.web.rest.request.BillRequest;

import java.util.List;

public interface BarcodeService {

    Store login(Store store);

    List<Item> findAllItemsInStore(Long storeId);

    Item findItemByStoreAndBarcode(Long storeId, String barcode);

    Bill createOrUpdateBill(BillRequest request);

    List<Unit> findAllUnitsInStore(Long storeId);

    List<Barcode> findAllBarCodesInStore(Long storeId);

    Result checkDataIsChange(Long storeId);

    Result updateMarkDataChange(Long storeId, Boolean status);

}
