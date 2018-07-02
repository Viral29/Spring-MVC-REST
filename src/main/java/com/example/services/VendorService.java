package com.example.services;

import com.example.api.v1.model.VendorDTO;
import com.example.api.v1.model.VendorListDTO;


public interface VendorService {

    VendorListDTO getAllVendor();

    VendorDTO getVendorById(Long id);

    VendorDTO createNewVendor(VendorDTO vendorDTO);

    VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO);

    VendorDTO patchVendor(Long id, VendorDTO vendorDTO);

    void deleteVendorById(Long id);
}
