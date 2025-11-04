package com.alsharif.shipchandling.stockunitmaster.service;

import com.alsharif.shipchandling.stockunitmaster.dto.StockUnitMasterDto;
import org.springframework.stereotype.Service;

@Service
public interface StockUnitService {
     StockUnitMasterDto getStockUnitByPoid(Long stockUnitPoid);
     StockUnitMasterDto createStockUnit(StockUnitMasterDto stockUnitMasterDto);
}
