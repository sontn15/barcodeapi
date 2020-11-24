package com.sh.barcodeapi.service.mapper;

import com.sh.barcodeapi.domain.Bill;
import com.sh.barcodeapi.entity.BillEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {SubBillEntityMapper.class})
public interface BillEntityMapper extends EntityMapper<BillEntity, Bill> {
}
