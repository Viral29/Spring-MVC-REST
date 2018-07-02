package com.example.api.v1.mapper;

import com.example.api.v1.model.VendorDTO;
import com.example.domain.Vendor;
import org.junit.Test;

import static org.junit.Assert.*;

public class VendorMapperTest {

    public static final String NAME = "Home Fruits";
    public static final long ID = 1L;
    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    public void vendorToVendorDTO() {
        Vendor vendor = new Vendor();
        vendor.setName(NAME);
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        assertEquals(NAME,vendorDTO.getName());
    }

    @Test
    public void vendorDTOToVendor(){

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);

        assertEquals(vendorDTO.getName(),vendor.getName());
    }
}