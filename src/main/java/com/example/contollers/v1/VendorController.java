package com.example.contollers.v1;

import com.example.api.v1.model.VendorDTO;
import com.example.api.v1.model.VendorListDTO;
import com.example.services.VendorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(description = "This is a Vendor Controller")
@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

    public static final String BASE_URL = "/api/v1/vendors";

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @ApiOperation(value = "Lists all the Vendor")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO getAllVendor(){

        return vendorService.getAllVendor();
    }

    @ApiOperation("Get Vendor by Id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getVendorById(@PathVariable Long id){

        return vendorService.getVendorById(id);
    }

    @ApiOperation("Create a Vendor")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO){

        return vendorService.createNewVendor(vendorDTO);
    }

    @ApiOperation("Replace a Vendor by new data")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO saveVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO){

        return vendorService.saveVendorByDTO(id,vendorDTO);

    }

    @ApiOperation("Update a Vendor")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO){

        return vendorService.patchVendor(id,vendorDTO);
    }

    @ApiOperation("Delete a Vendor")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVendor(@PathVariable Long id){

        vendorService.deleteVendorById(id);
    }
}
