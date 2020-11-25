package com.sh.barcodeapi.repository;

import com.sh.barcodeapi.entity.UnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnitRepository extends JpaRepository<UnitEntity, Long> {

    @Query("SELECT u FROM UnitEntity u WHERE u.status = true AND u.storeId = :storeId")
    List<UnitEntity> findAllByStoreId(@Param("storeId") Long storeId);
    
    Optional<UnitEntity> findByStoreIdAndNameIgnoreCaseAndStatus(Long storeId, String name, Boolean status);

}
