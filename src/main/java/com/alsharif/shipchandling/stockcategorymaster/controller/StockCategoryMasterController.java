package com.alsharif.shipchandling.stockcategorymaster.controller;

import com.alsharif.shipchandling.stockcategorymaster.dto.StockCategoryMasterDto;
import com.alsharif.shipchandling.stockcategorymaster.service.StockCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.alsharif.shipchandling.common.ApiResponse.success;

@RestController
@RequestMapping("stockcatagorymaster")
public class StockCategoryMasterController {

    @Autowired
    StockCategoryService stockCategoryService;

    @Operation(
            summary = "Get stock category by ID",
            description = "Retrieves stock category details based on the provided category POID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the stock category details",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StockCategoryMasterDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input parameters",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized - Authentication required",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Stock category not found",
                            content = @Content(mediaType = "application/json")
                    )
            },
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/{categoryPoid}")
    public ResponseEntity<?> getStockCategoryByPoid(
            @Parameter(description = "CategoryPoid reference identifier", required = true)
            @PathVariable Long categoryPoid) {
        StockCategoryMasterDto stockCategoryMasterDto = stockCategoryService.getStockCategoryByPoid(categoryPoid);
        return success("Stock category fetched successfully", stockCategoryMasterDto);
    }

    @Operation(
            summary = "Create a new stock category",
            description = "Creates a new stock category with the provided details",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully created the stock category",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StockCategoryMasterDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input, object invalid",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized - Authentication required",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Stock category with the same code or name already exists",
                            content = @Content(mediaType = "application/json")
                    )
            },
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PostMapping("/create")
    public ResponseEntity<?> createStockCategory(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Stock category object that needs to be created",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = StockCategoryMasterDto.class)
                    )
            )
            @Parameter(description = "Stock category details to be created", required = true)
            @Valid @RequestBody StockCategoryMasterDto stockCategoryMasterDto) {
        StockCategoryMasterDto response = stockCategoryService.createStockCategory(stockCategoryMasterDto);
        return success("Stock category created successfully", response);
    }

    @Operation(
            summary = "Update stock category",
            description = "Updates an existing stock category with the provided details",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated the stock category",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StockCategoryMasterDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input parameters",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized - Authentication required",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Stock category not found",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Stock category with the same code or name already exists",
                            content = @Content(mediaType = "application/json")
                    )
            },
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PutMapping("/{categoryPoid}")
    public ResponseEntity<?> updateStockCategory(
            @Parameter(description = "CategoryPoid reference identifier", required = true)
            @PathVariable Long categoryPoid,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Stock category object that needs to be updated",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = StockCategoryMasterDto.class)
                    )
            )
            @Parameter(description = "Stock category details to be updated", required = true)
            @Valid @RequestBody StockCategoryMasterDto stockCategoryMasterDto) {
        StockCategoryMasterDto response = stockCategoryService.updateStockCategory(categoryPoid, stockCategoryMasterDto);
        return success("Stock category updated successfully", response);
    }

    @Operation(
            summary = "Delete stock category (Soft Delete)",
            description = "Performs a soft delete on the stock category by setting the deleted flag to 'Y'",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully deleted the stock category",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized - Authentication required",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Stock category not found",
                            content = @Content(mediaType = "application/json")
                    )
            },
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @DeleteMapping("/{categoryPoid}")
    public ResponseEntity<?> deleteStockCategory(
            @Parameter(description = "CategoryPoid reference identifier", required = true)
            @PathVariable Long categoryPoid) {
        stockCategoryService.deleteStockCategory(categoryPoid);
        return success("Stock category deleted successfully");
    }


}
