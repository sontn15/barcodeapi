package com.sh.barcodeapi.service.impl;

import com.sh.barcodeapi.domain.*;
import com.sh.barcodeapi.entity.*;
import com.sh.barcodeapi.exception.BadRequestError;
import com.sh.barcodeapi.exception.NotFoundError;
import com.sh.barcodeapi.exception.ResponseException;
import com.sh.barcodeapi.repository.*;
import com.sh.barcodeapi.service.BarcodeService;
import com.sh.barcodeapi.service.mapper.*;
import com.sh.barcodeapi.utils.DateUtils;
import com.sh.barcodeapi.web.rest.request.BillRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BarcodeServiceImpl implements BarcodeService {

    private final StoreRepository storeRepository;
    private final UnitRepository unitRepository;
    private final ItemRepository itemRepository;
    private final BillRepository billRepository;
    private final SubBillRepository subBillRepository;
    private final BarcodeRepository barcodeRepository;

    private final StoreEntityMapper storeEntityMapper;
    private final UnitEntityMapper unitEntityMapper;
    private final ItemEntityMapper itemEntityMapper;
    private final BillEntityMapper billEntityMapper;
    private final BillCreateMapper billCreateMapper;
    private final SubBillEntityMapper subBillEntityMapper;
    private final BarcodeEntityMapper barcodeEntityMapper;


    @Autowired
    public BarcodeServiceImpl(StoreRepository storeRepository,
                              UnitRepository unitRepository,
                              ItemRepository itemRepository,
                              BillRepository billRepository,
                              SubBillRepository subBillRepository,
                              BarcodeRepository barcodeRepository,
                              StoreEntityMapper storeEntityMapper,
                              UnitEntityMapper unitEntityMapper,
                              ItemEntityMapper itemEntityMapper,
                              BillEntityMapper billEntityMapper,
                              BillCreateMapper billCreateMapper,
                              SubBillEntityMapper subBillEntityMapper,
                              BarcodeEntityMapper barcodeEntityMapper) {
        this.storeRepository = storeRepository;
        this.unitRepository = unitRepository;
        this.itemRepository = itemRepository;
        this.billRepository = billRepository;
        this.subBillRepository = subBillRepository;
        this.barcodeRepository = barcodeRepository;
        this.storeEntityMapper = storeEntityMapper;
        this.unitEntityMapper = unitEntityMapper;
        this.itemEntityMapper = itemEntityMapper;
        this.billEntityMapper = billEntityMapper;
        this.billCreateMapper = billCreateMapper;
        this.subBillEntityMapper = subBillEntityMapper;
        this.barcodeEntityMapper = barcodeEntityMapper;
    }

    @Override
    public Store login(Store store) {
        StoreEntity storeEntity = storeRepository.findByUsernameAndPassword(store.getUsername().trim(), store.getPassword().trim())
                .orElseThrow(() -> new ResponseException(
                        NotFoundError.STORE_NOT_FOUND.getMessage(), NotFoundError.STORE_NOT_FOUND));
        boolean isCheck = checkDeviceAvailableInStore(storeEntity, store.getSerialDevice());
        if (!isCheck) {
            throw new ResponseException(
                    BadRequestError.QUANTITY_DEVICE_STORE_UNAVAILABLE.getMessage(), BadRequestError.QUANTITY_DEVICE_STORE_UNAVAILABLE);
        }
        return storeEntityMapper.toDomain(storeEntity);
    }

    @Override
    public List<Item> findAllItemsInStore(Long storeId) {
        Sort mSort = Sort.by(Sort.Order.asc("name").nullsLast());

        StoreEntity storeEntity = storeRepository.findById(storeId)
                .orElseThrow(() -> new ResponseException(
                        NotFoundError.STORE_NOT_FOUND.getMessage(), NotFoundError.STORE_NOT_FOUND));
        Map<Long, Unit> mapUnits = unitRepository.findAllByStoreId(storeEntity.getId())
                .stream()
                .map(unitEntityMapper::toDomain)
                .collect(Collectors.toMap(Unit::getId, obj -> obj));

        Unit unitDefault;
        Optional<UnitEntity> unitOptional = unitRepository.findByStoreIdAndNameIgnoreCaseAndStatus(storeId, "Chiếc", true);
        if (unitOptional.isPresent()) {
            unitDefault = unitEntityMapper.toDomain(unitOptional.get());
        } else {
            unitDefault = Unit.builder()
                    .id(100L)
                    .status(true)
                    .name("Chiếc")
                    .storeId(storeId)
                    .build();
        }
        Unit finalUnitDefault = unitDefault;

        return itemRepository.findAllByStoreId(storeEntity.getId(), mSort)
                .stream()
                .filter(Objects::nonNull)
                .map(itemEntityMapper::toDomain)
                .peek(item -> {
                    if (item.getUnitMin() != null) {
                        item.setUnitMinObj(mapUnits.get(item.getUnitMin()));
                    }
                    if (item.getUnitDefault() != null && item.getUnitDefault() != 0L) {
                        item.setUnitDefaultObj(mapUnits.get(item.getUnitDefault()));
                    } else {
                        if (item.getUnitMin() != null) {
                            item.setUnitDefaultObj(mapUnits.get(item.getUnitMin()));
                        } else if (item.getUnit1() != null) {
                            item.setUnitDefaultObj(mapUnits.get(item.getUnit1()));
                        } else if (item.getUnit2() != null) {
                            item.setUnitDefaultObj(mapUnits.get(item.getUnit2()));
                        } else {
                            item.setUnitDefaultObj(finalUnitDefault);
                        }
                    }
                    if (item.getUnit1() != null) {
                        item.setUnit1Obj(mapUnits.get(item.getUnit1()));
                    }
                    if (item.getUnit2() != null) {
                        item.setUnit2Obj(mapUnits.get(item.getUnit2()));
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public Item findItemByStoreAndBarcode(Long storeId, String barcode) {
        Optional<ItemEntity> optional = itemRepository.findByStoreAndBarcode(storeId, barcode);
        if (optional.isPresent()) {
            return itemEntityMapper.toDomain(optional.get());
        }
        return itemRepository.findByStoreAndBarcodeSub(storeId, barcode)
                .map(itemEntityMapper::toDomain)
                .orElseThrow(() -> new ResponseException(
                        NotFoundError.ITEM_NOT_FOUND.getMessage(), NotFoundError.ITEM_NOT_FOUND)
                );
    }

    @Override
    public Bill createOrUpdateBill(BillRequest request) {
        if (storeRepository.findById(request.getStoreId()).isEmpty()) {
            throw new ResponseException(NotFoundError.STORE_NOT_FOUND.getMessage(), NotFoundError.STORE_NOT_FOUND);
        }
        BillEntity billEntity = billCreateMapper.toEntity(request);
        billEntity.setLstSubBills(null);
        if (request.getId() == null) {
            billEntity.setId(null);
        } else {
            billEntity.setId(request.getId());
        }
        billEntity.setStatus(true);
        billEntity.setCreatedDate(new Date());
        billEntity.setDay(DateUtils.getCurrentDateStr());
        BillEntity billInsert = billRepository.save(billEntity);

        List<SubBillEntity> listSubBillEntity = subBillEntityMapper.toEntity(request.getLstSubBills());
        listSubBillEntity.forEach(obj -> obj.setBill(billInsert));

        List<SubBillEntity> subBillEntitiesInsert = subBillRepository.saveAll(listSubBillEntity);
        billInsert.setLstSubBills(subBillEntitiesInsert);

        return billEntityMapper.toDomain(billInsert);
    }

    @Override
    public List<Unit> findAllUnitsInStore(Long storeId) {
        return unitRepository.findAllByStoreId(storeId)
                .stream()
                .map(unitEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Barcode> findAllBarCodesInStore(Long storeId) {
        return barcodeRepository.findAllByStoreId(storeId)
                .stream()
                .map(barcodeEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    private boolean checkDeviceAvailableInStore(StoreEntity storeEntity, String serialNumber) {
        boolean isCheck = false;
        String newSerialNumber;
        List<String> arrSerials = new ArrayList<>();
        if (storeEntity.getSerialDevice() != null && !storeEntity.getSerialDevice().isEmpty()) {
            arrSerials.addAll(Arrays.asList(storeEntity.getSerialDevice().split(",")));
            newSerialNumber = storeEntity.getSerialDevice() + "," + serialNumber;
        } else {
            newSerialNumber = serialNumber;
        }
        if (arrSerials.size() > 0 && arrSerials.contains(serialNumber)) {
            isCheck = true;
        } else if (!arrSerials.contains(serialNumber) && storeEntity.getQuantityDevice() > arrSerials.size()) {
            storeEntity.setSerialDevice(newSerialNumber);
            storeRepository.save(storeEntity);
            isCheck = true;
        }
        return isCheck;
    }

}
