package com.alsharif.shipchandling.stockcategorymaster.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "STOCK_CATEGORY_MASTER")
public class StockCategoryMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_POID")
    private Long categoryPoid;

    @Column(name = "CATEGORY_CODE", length = 20)
    private String categoryCode;

    @Column(name = "CATEGORY_NAME", length = 100)
    private String categoryName;

    @Column(name = "CATEGORY_NAME2", length = 100)
    private String categoryName2;

    @Column(name = "GROUP_POID", length = 22)
    private Long groupPoid;

    @Column(name = "CREATED_BY", length = 20)
    private String createdBy;

    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;

    @Column(name = "LASTMODIFIED_BY")
    private String lastModifiedBy;

    @Column(name = "LASTMODIFIED_DATE")
    private LocalDateTime lastModifiedDate;

    @Column(name = "ACTIVE", length = 1)
    private String active;

    @Column(name = "SEQNO")
    private String seqNo;

    @Column(name = "DELETED")
    private String deleted;

    @Column(name = "PARENT_CATEGORY_POID")
    private Long parentCategoryPoid;

    @Column(name = "STOCK_GL_POID")
    private Long stockGlPoid;

    @Column(name = "COST_OF_SALES_GL_POID")
    private Long costOfSalesGlPoid;

    @Column(name = "SALES_GL_POID")
    private Long salesGlPoid;

    @Column(name = "CATEGORY_TYPE")
    private String categoryType;

    @Column(name = "COST_CENTER_POID")
    private Long costCenterPoid;

    @Column(name = "OUTPUT_TAX_POID")
    private String outputTaxPoid;

    @Column(name = "INPUT_TAX_POID")
    private Long inputTaxPoid;



}
