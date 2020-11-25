package com.sh.barcodeapi.service.mapper;

import com.sh.barcodeapi.domain.Barcode;
import com.sh.barcodeapi.entity.BarcodeEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface BarcodeEntityMapper extends EntityMapper<BarcodeEntity, Barcode> {
}
