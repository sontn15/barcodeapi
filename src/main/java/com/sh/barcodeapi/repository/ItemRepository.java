package com.sh.barcodeapi.repository;

import com.sh.barcodeapi.entity.ItemEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    @Query("SELECT i FROM ItemEntity i WHERE i.status = true AND i.store.id = :storeId ")
    List<ItemEntity> findAllByStoreId(Long storeId, Sort sort);

    @Query("SELECT i FROM ItemEntity i WHERE i.status = true AND i.store.code = :storeCode AND i.barcode = :barCode ")
    Optional<ItemEntity> findByStoreAndBarcode(@Param("storeCode") String storeCode,
                                               @Param("barCode") String barCode);


}
