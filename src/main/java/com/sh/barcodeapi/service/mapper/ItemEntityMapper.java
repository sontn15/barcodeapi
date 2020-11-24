package com.sh.barcodeapi.service.mapper;

import com.sh.barcodeapi.domain.Item;
import com.sh.barcodeapi.entity.ItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemEntityMapper extends EntityMapper<ItemEntity, Item> {
}
