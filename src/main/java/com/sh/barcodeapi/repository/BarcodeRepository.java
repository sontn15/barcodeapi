package com.sh.barcodeapi.repository;

import com.sh.barcodeapi.entity.BarcodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarcodeRepository extends JpaRepository<BarcodeEntity, Long> {

    @Query("SELECT b FROM BarcodeEntity b WHERE b.storeId = :storeId ")
    List<BarcodeEntity> findAllByStoreId(@Param("storeId") Long storeId);

}