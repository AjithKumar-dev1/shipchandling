package com.alsharif.shipchandling.stockcategorymaster.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class StockCategoryMasterDto {

    private Long categoryPoid;
    private String categoryCode;
    private String categoryName;
    private String categoryName2;
    private Long groupPoid;
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
    private String active;
    private String seqNo;
    private String deleted;
    private Long parentCategoryPoid;
    private Long stockGlPoid;
    private Long costOfSalesGlPoid;
    private Long salesGlPoid;
    private String categoryType;
    private Long costCenterPoid;
    private String outputTaxPoid;
    private Long inputTaxPoid;

}
