package com.alsharif.shipchandling.stockunitmaster.service;

import com.alsharif.shipchandling.stockunitmaster.dto.FilterRequestDto;
import com.alsharif.shipchandling.stockunitmaster.dto.StockUnitMasterDto;

import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface StockUnitService {
     StockUnitMasterDto getStockUnitByPoid(Long stockUnitPoid);

     StockUnitMasterDto createStockUnit(StockUnitMasterDto stockUnitMasterDto);

     StockUnitMasterDto updateStockUnit(Long stockUnitPoid, StockUnitMasterDto stockUnitMasterDto);

     Map<String, Object> listStockUnits(String docId, FilterRequestDto request, Pageable pageable);

     void softDeleteStockUnit(Long stockUnitPoid);
}
