package com.sh.barcodeapi.service.mapper;

import java.util.Collection;
import java.util.List;

public interface EntityMapper<E, D> {
    D toDomain(E entity);

    E toEntity(D domain);

    List<D> toDomain(Collection<E> entities);

    List<E> toEntity(Collection<D> domains);
}
