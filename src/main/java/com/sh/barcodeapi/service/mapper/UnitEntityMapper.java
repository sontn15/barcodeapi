package com.sh.barcodeapi.service.mapper;

import com.sh.barcodeapi.domain.Unit;
import com.sh.barcodeapi.entity.UnitEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UnitEntityMapper extends EntityMapper<UnitEntity, Unit> {
}
