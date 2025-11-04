package com.alsharif.shipchandling.stockunitmaster.repository;

import com.alsharif.shipchandling.stockunitmaster.entity.StockUnitMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StockUnitRepository extends JpaRepository<StockUnitMaster, Long>, JpaSpecificationExecutor<StockUnitMaster> {

    boolean existsByStockUnitPoid(Long stockUnitPoid);
    boolean existsByStockUnitCode(String stockUnitCode);
    boolean existsByStockUnitName(String stockUnitName);

    StockUnitMaster findByStockUnitPoid(Long stockUnitPoid);
}
