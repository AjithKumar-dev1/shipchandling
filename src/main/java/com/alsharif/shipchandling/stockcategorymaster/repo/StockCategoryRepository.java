package com.alsharif.shipchandling.stockcategorymaster.repo;

import com.alsharif.shipchandling.stockcategorymaster.entity.StockCategoryMaster;
import com.alsharif.shipchandling.stockunitmaster.entity.StockUnitMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StockCategoryRepository extends JpaRepository<StockCategoryMaster,Long>, JpaSpecificationExecutor<StockUnitMaster> {
    boolean existsByCategoryPoid(Long categoryPoid);
    boolean existsByCategoryCode(String categoryCode);
    boolean existsByCategoryName(String categoryName);

    StockCategoryMaster findByCategoryPoid(Long categoryPoid);
}
