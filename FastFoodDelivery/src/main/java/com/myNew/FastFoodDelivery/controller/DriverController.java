package com.myNew.FastFoodDelivery.controller;

import com.myNew.FastFoodDelivery.converter.DriverConverter;
import com.myNew.FastFoodDelivery.dto.DriverDto;
import com.myNew.FastFoodDelivery.model.Driver;
import com.myNew.FastFoodDelivery.service.DriverService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/drivers")
public class DriverController {

    private final DriverService driverService;
    private final DriverConverter driverConverter;

    public DriverController(DriverService driverService, DriverConverter driverConverter) {
        this.driverService = driverService;
        this.driverConverter = driverConverter;
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DriverDto> update(@RequestBody @Valid DriverDto driverDto,
                                            @PathVariable Long id) {
        Driver driver = driverConverter.toDriver(driverDto);
        Driver updatedDriver = driverService.update(driver, id);
        return ResponseEntity.ok(driverConverter.toDriverDto(updatedDriver));
    }

    @PostMapping
    public ResponseEntity<DriverDto> save(@RequestBody @Valid DriverDto driverDto) {
        Driver driver = driverConverter.toDriver(driverDto);
        Driver savedDriver = driverService.save(driver);
        return ResponseEntity.ok(driverConverter.toDriverDto(savedDriver));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        driverService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/efficiencyLevel/{efficiencyLevel}")
    public ResponseEntity<DriverDto> findByEfficiencyLevel(@PathVariable double efficiencyLevel) {
        return ResponseEntity.ok
                (driverConverter.toDriverDto(driverService.findByEfficiencyLevel(efficiencyLevel)));
    }
}
