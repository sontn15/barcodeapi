package com.sh.barcodeapi.service.mapper;

import com.sh.barcodeapi.domain.SubBill;
import com.sh.barcodeapi.entity.SubBillEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubBillEntityMapper extends EntityMapper<SubBillEntity, SubBill> {
}
