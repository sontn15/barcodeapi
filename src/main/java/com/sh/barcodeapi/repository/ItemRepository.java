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

    @Query("SELECT i FROM ItemEntity i WHERE i.status = true AND i.store.id = :storeId AND i.barcode = :barCode ")
    Optional<ItemEntity> findByStoreAndBarcode(@Param("storeId") Long storeId,
                                               @Param("barCode") String barCode);

    @Query("SELECT i FROM ItemEntity i " +
            "INNER JOIN BarcodeEntity b ON i.code = b.itemCode AND i.status = true " +
            "AND b.storeId = :storeId AND b.barcode = :barCode ")
    Optional<ItemEntity> findByStoreAndBarcodeSub(@Param("storeId") Long storeId,
                                                    @Param("barCode") String barCode);

}
