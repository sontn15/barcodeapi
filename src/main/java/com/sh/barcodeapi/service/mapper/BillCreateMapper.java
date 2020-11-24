package com.sh.barcodeapi.service.mapper;

import com.sh.barcodeapi.entity.BillEntity;
import com.sh.barcodeapi.web.rest.request.BillRequest;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {SubBillEntityMapper.class})
public interface BillCreateMapper extends EntityMapper<BillEntity, BillRequest> {
}