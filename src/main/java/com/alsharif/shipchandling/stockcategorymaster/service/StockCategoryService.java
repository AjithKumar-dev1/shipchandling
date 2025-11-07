package com.alsharif.shipchandling.stockcategorymaster.service;

import com.alsharif.shipchandling.stockcategorymaster.dto.StockCategoryMasterDto;
import org.springframework.stereotype.Service;

@Service
public interface StockCategoryService {
    StockCategoryMasterDto getStockCategoryByPoid(Long stockCategoryPoid);
    StockCategoryMasterDto createStockCategory(StockCategoryMasterDto stockCategoryMasterDto);
    StockCategoryMasterDto updateStockCategory(Long categoryPoid, StockCategoryMasterDto stockCategoryMasterDto);
    void deleteStockCategory(Long categoryPoid);
}
