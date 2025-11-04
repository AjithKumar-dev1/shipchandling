package com.alsharif.shipchandling.stockunitmaster.service;

import com.alsharif.shipchandling.exceptions.ResourceAlreadyExistsException;
import com.alsharif.shipchandling.exceptions.ResourceNotFoundException;
import com.alsharif.shipchandling.group.repository.GroupRepository;
import com.alsharif.shipchandling.stockunitmaster.dto.StockUnitMasterDto;
import com.alsharif.shipchandling.stockunitmaster.entity.StockUnitMaster;
import com.alsharif.shipchandling.stockunitmaster.repository.StockUnitRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StockUnitServiceImpl implements StockUnitService{

    @Autowired
    StockUnitRepository stockUnitRepository;

    @Autowired
    GroupRepository groupRepository;

    @Override
    public StockUnitMasterDto getStockUnitByPoid(Long stockUnitPoid) {
        if (!stockUnitRepository.existsByStockUnitPoid(stockUnitPoid)) {
            throw new ResourceNotFoundException("StockUnit", "stockUnitPoid", stockUnitPoid);
        }
        StockUnitMasterDto stockUnitMasterDto = new StockUnitMasterDto();

        StockUnitMaster stockUnitMaster = stockUnitRepository.findByStockUnitPoid(stockUnitPoid);
        BeanUtils.copyProperties(stockUnitMaster,stockUnitMasterDto);
        return stockUnitMasterDto;
    }

    @Override
    public StockUnitMasterDto createStockUnit(StockUnitMasterDto stockUnitMasterDto) {
        StockUnitMasterDto responseDto = new StockUnitMasterDto();

        if (stockUnitRepository.existsByStockUnitCode(stockUnitMasterDto.getStockUnitCode())) {
            throw new ResourceAlreadyExistsException("stockUnitCode", stockUnitMasterDto.getStockUnitCode());
        }

        if (stockUnitRepository.existsByStockUnitName(stockUnitMasterDto.getStockUnitName())) {
            throw new ResourceAlreadyExistsException("stockUnitName", stockUnitMasterDto.getStockUnitName());
        }

        groupRepository.findById(stockUnitMasterDto.getGroupPoid()).orElseThrow(() -> new ResourceNotFoundException("Group", "groupPoid", stockUnitMasterDto.getGroupPoid()));

        StockUnitMaster entity = mapDtoToEntity(stockUnitMasterDto);

        StockUnitMaster responseEntity = stockUnitRepository.save(entity);
        BeanUtils.copyProperties(responseEntity,responseDto);

        return responseDto;
    }

    private StockUnitMaster mapDtoToEntity(StockUnitMasterDto dto){
        StockUnitMaster entity = new StockUnitMaster();
        entity.setStockUnitCode(dto.getStockUnitCode());
        entity.setStockUnitName(dto.getStockUnitName());
        entity.setStockUnitName2(dto.getStockUnitName2());
        entity.setGroupPoid(dto.getGroupPoid());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setActive(dto.getActive());
        entity.setSeqNo(dto.getSeqNo());
        entity.setDeleted("N");
        entity.setClassified(dto.getClassified());

        return entity;
    }
}
