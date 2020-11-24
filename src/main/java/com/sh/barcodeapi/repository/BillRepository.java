package com.sh.barcodeapi.repository;

import com.sh.barcodeapi.entity.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<BillEntity, Long> {

}
