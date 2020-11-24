package com.sh.barcodeapi.service.mapper;

import com.sh.barcodeapi.domain.Store;
import com.sh.barcodeapi.entity.StoreEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StoreEntityMapper extends EntityMapper<StoreEntity, Store> {
}
