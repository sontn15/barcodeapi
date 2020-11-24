package com.sh.barcodeapi.repository;

import com.sh.barcodeapi.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Long> {

    Optional<StoreEntity> findByCode(String code);

    Optional<StoreEntity> findByUsernameAndPassword(String username, String password);

}
