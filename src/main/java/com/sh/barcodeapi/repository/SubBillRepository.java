package com.sh.barcodeapi.repository;

import com.sh.barcodeapi.entity.SubBillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubBillRepository extends JpaRepository<SubBillEntity, Long> {

    @Query("SELECT s FROM SubBillEntity s where s.bill.id = :billId ")
    List<SubBillEntity> findAllByBillId(@Param("billId") Long billId);

}
