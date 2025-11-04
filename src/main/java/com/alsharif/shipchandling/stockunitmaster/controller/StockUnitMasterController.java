package com.alsharif.shipchandling.stockunitmaster.controller;

import com.alsharif.shipchandling.stockunitmaster.dto.StockUnitMasterDto;
import com.alsharif.shipchandling.stockunitmaster.service.StockUnitService;
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
import static com.alsharif.shipchandling.common.ApiResponse.*;

@RestController
@RequestMapping("stockunitmaster")
public class StockUnitMasterController {

    @Autowired
    private StockUnitService stockUnitService;


    @Operation(
            summary = "Get stock unit by ID",
            description = "Retrieves stock unit details based on the provided stockUnit POID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the stock unit details",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StockUnitMasterDto.class)
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
                            description = "Stock unit not found",
                            content = @Content(mediaType = "application/json")
                    )
            },
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/{stockUnitPoid}")
    public ResponseEntity<?> getStockUnitByPoid(
            @Parameter(description = "StockUnitPoid reference identifier", required = true)
            @PathVariable Long stockUnitPoid,
            @Parameter(description = "Document identifier", required = true, example = "800-320")
            @RequestParam String documentId,
            @Parameter(description = "Action requested", required = true)
            @RequestParam String actionRequested) {
        StockUnitMasterDto stockUnitMasterDto = stockUnitService.getStockUnitByPoid(stockUnitPoid);
        return  success("Task fetched successfully", stockUnitMasterDto);

    }

    @Operation(
            summary = "Create a new stock unit",
            description = "Creates a new stock unit with the provided details",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully created the stock unit",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StockUnitMasterDto.class)
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
                            description = "Country with the same code already exists",
                            content = @Content(mediaType = "application/json")
                    )
            },
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PostMapping("/create")
    public ResponseEntity<?> createStockUnit(

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Country object that needs to be created",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = StockUnitMasterDto.class)
                    )
            )
            @Parameter(description = "Stock unit details to be created", required = true)
            @Valid @RequestBody StockUnitMasterDto stockUnitMasterDto)
            {

        StockUnitMasterDto response = stockUnitService.createStockUnit(stockUnitMasterDto);

        return success("Stock unit created successfully", response);
    }

}
