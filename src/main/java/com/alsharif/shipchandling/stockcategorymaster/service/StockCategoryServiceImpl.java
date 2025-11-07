package com.alsharif.shipchandling.stockcategorymaster.service;

import com.alsharif.shipchandling.exceptions.ResourceAlreadyExistsException;
import com.alsharif.shipchandling.exceptions.ResourceNotFoundException;
import com.alsharif.shipchandling.group.repository.GroupRepository;
import com.alsharif.shipchandling.stockcategorymaster.dto.StockCategoryMasterDto;
import com.alsharif.shipchandling.stockcategorymaster.entity.StockCategoryMaster;
import com.alsharif.shipchandling.stockcategorymaster.repo.StockCategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StockCategoryServiceImpl implements StockCategoryService{

    @Autowired
    StockCategoryRepository stockCategoryRepository;

    @Autowired
    GroupRepository groupRepository;

    @Override
    public StockCategoryMasterDto getStockCategoryByPoid(Long categoryPoid) {
        if (!stockCategoryRepository.existsByCategoryPoid(categoryPoid)) {
            throw new ResourceNotFoundException("StockCategory", "stockCategoryPoid", categoryPoid);
        }
        StockCategoryMasterDto stockCategoryMasterDto = new StockCategoryMasterDto();

        StockCategoryMaster stockCategoryMaster = stockCategoryRepository.findByCategoryPoid(categoryPoid);
        BeanUtils.copyProperties(stockCategoryMaster,stockCategoryMasterDto);
        return stockCategoryMasterDto;
    }

    @Override
    public StockCategoryMasterDto createStockCategory(StockCategoryMasterDto stockCategoryMasterDto) {
        StockCategoryMasterDto responseDto = new StockCategoryMasterDto();


        if (stockCategoryRepository.existsByCategoryName(stockCategoryMasterDto.getCategoryName())) {
            throw new ResourceAlreadyExistsException("categoryName", stockCategoryMasterDto.getCategoryName());
        }

        if (stockCategoryMasterDto.getGroupPoid() != null) {
            groupRepository.findById(stockCategoryMasterDto.getGroupPoid())
                    .orElseThrow(() -> new ResourceNotFoundException("Group", "groupPoid", stockCategoryMasterDto.getGroupPoid()));
        }

        StockCategoryMaster entity = mapDtoToEntity(stockCategoryMasterDto);

        StockCategoryMaster responseEntity = stockCategoryRepository.save(entity);
        BeanUtils.copyProperties(responseEntity, responseDto);

        return responseDto;
    }

    @Override
    public StockCategoryMasterDto updateStockCategory(Long categoryPoid, StockCategoryMasterDto stockCategoryMasterDto) {
        StockCategoryMaster existingCategory = stockCategoryRepository.findByCategoryPoid(categoryPoid);
        if (existingCategory == null) {
            throw new ResourceNotFoundException("StockCategory", "categoryPoid", categoryPoid);
        }

        // Check if category code is being changed and if new code already exists
        if (stockCategoryMasterDto.getCategoryCode() != null && 
            !existingCategory.getCategoryCode().equals(stockCategoryMasterDto.getCategoryCode()) &&
            stockCategoryRepository.existsByCategoryCode(stockCategoryMasterDto.getCategoryCode())) {
            throw new ResourceAlreadyExistsException("categoryCode", stockCategoryMasterDto.getCategoryCode());
        }

        // Check if category name is being changed and if new name already exists
        if (stockCategoryMasterDto.getCategoryName() != null && 
            !existingCategory.getCategoryName().equals(stockCategoryMasterDto.getCategoryName()) &&
            stockCategoryRepository.existsByCategoryName(stockCategoryMasterDto.getCategoryName())) {
            throw new ResourceAlreadyExistsException("categoryName", stockCategoryMasterDto.getCategoryName());
        }

        // Validate group if provided
        if (stockCategoryMasterDto.getGroupPoid() != null) {
            groupRepository.findById(stockCategoryMasterDto.getGroupPoid())
                    .orElseThrow(() -> new ResourceNotFoundException("Group", "groupPoid", stockCategoryMasterDto.getGroupPoid()));
        }

        // Update entity fields
        updateEntityFromDto(existingCategory, stockCategoryMasterDto);
        existingCategory.setLastModifiedDate(LocalDateTime.now());

        StockCategoryMaster updatedEntity = stockCategoryRepository.save(existingCategory);
        StockCategoryMasterDto responseDto = new StockCategoryMasterDto();
        BeanUtils.copyProperties(updatedEntity, responseDto);

        return responseDto;
    }

    @Override
    public void deleteStockCategory(Long categoryPoid) {
        StockCategoryMaster stockCategoryMaster = stockCategoryRepository.findByCategoryPoid(categoryPoid);
        if (stockCategoryMaster == null) {
            throw new ResourceNotFoundException("StockCategory", "categoryPoid", categoryPoid);
        }

        // Soft delete - set deleted flag to "Y"
        stockCategoryMaster.setDeleted("Y");
        stockCategoryMaster.setLastModifiedDate(LocalDateTime.now());
        stockCategoryRepository.save(stockCategoryMaster);
    }

    private StockCategoryMaster mapDtoToEntity(StockCategoryMasterDto dto) {
        StockCategoryMaster entity = new StockCategoryMaster();
        entity.setCategoryCode(dto.getCategoryCode());
        entity.setCategoryName(dto.getCategoryName());
        entity.setCategoryName2(dto.getCategoryName2());
        entity.setGroupPoid(dto.getGroupPoid());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setActive(dto.getActive());
        entity.setSeqNo(dto.getSeqNo());
        entity.setDeleted("N");
        entity.setParentCategoryPoid(dto.getParentCategoryPoid());
        entity.setStockGlPoid(dto.getStockGlPoid());
        entity.setCostOfSalesGlPoid(dto.getCostOfSalesGlPoid());
        entity.setSalesGlPoid(dto.getSalesGlPoid());
        entity.setCategoryType(dto.getCategoryType());
        entity.setCostCenterPoid(dto.getCostCenterPoid());
        entity.setOutputTaxPoid(dto.getOutputTaxPoid());
        entity.setInputTaxPoid(dto.getInputTaxPoid());

        return entity;
    }

    private void updateEntityFromDto(StockCategoryMaster entity, StockCategoryMasterDto dto) {
        if (dto.getCategoryCode() != null) {
            entity.setCategoryCode(dto.getCategoryCode());
        }
        if (dto.getCategoryName() != null) {
            entity.setCategoryName(dto.getCategoryName());
        }
        if (dto.getCategoryName2() != null) {
            entity.setCategoryName2(dto.getCategoryName2());
        }
        if (dto.getGroupPoid() != null) {
            entity.setGroupPoid(dto.getGroupPoid());
        }
        if (dto.getLastModifiedBy() != null) {
            entity.setLastModifiedBy(dto.getLastModifiedBy());
        }
        if (dto.getActive() != null) {
            entity.setActive(dto.getActive());
        }
        if (dto.getSeqNo() != null) {
            entity.setSeqNo(dto.getSeqNo());
        }
        if (dto.getParentCategoryPoid() != null) {
            entity.setParentCategoryPoid(dto.getParentCategoryPoid());
        }
        if (dto.getStockGlPoid() != null) {
            entity.setStockGlPoid(dto.getStockGlPoid());
        }
        if (dto.getCostOfSalesGlPoid() != null) {
            entity.setCostOfSalesGlPoid(dto.getCostOfSalesGlPoid());
        }
        if (dto.getSalesGlPoid() != null) {
            entity.setSalesGlPoid(dto.getSalesGlPoid());
        }
        if (dto.getCategoryType() != null) {
            entity.setCategoryType(dto.getCategoryType());
        }
        if (dto.getCostCenterPoid() != null) {
            entity.setCostCenterPoid(dto.getCostCenterPoid());
        }
        if (dto.getOutputTaxPoid() != null) {
            entity.setOutputTaxPoid(dto.getOutputTaxPoid());
        }
        if (dto.getInputTaxPoid() != null) {
            entity.setInputTaxPoid(dto.getInputTaxPoid());
        }
    }
}
