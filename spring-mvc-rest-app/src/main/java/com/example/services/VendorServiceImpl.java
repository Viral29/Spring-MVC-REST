package com.example.services;

import com.example.api.v1.mapper.VendorMapper;
import com.example.api.v1.model.VendorDTO;
import com.example.api.v1.model.VendorListDTO;
import com.example.domain.Vendor;
import com.example.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private VendorRepository vendorRepository;
    private VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    private String getVendorURL(Long id){

        return "/shop/v1/vendors/"+id;

    }

    @Override
    public VendorListDTO getAllVendor() {

        List<VendorDTO> vendorDTOList = vendorRepository.findAll()
                .stream()
                .map(
                        vendor -> {
                            VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                            vendorDTO.setVendorurl(getVendorURL(vendor.getId()));
                            return vendorDTO;
                        }
                )
                .collect(Collectors.toList());
        return new VendorListDTO(vendorDTOList);
    }

    @Override
    public VendorDTO getVendorById(Long id) {

        return vendorRepository.findById(id)
                .map(
                        vendor -> {
                            VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                            vendorDTO.setVendorurl(getVendorURL(id));
                            return vendorDTO;
                        }
                )
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {

        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);

        Vendor savedVendor = vendorRepository.save(vendor);

        VendorDTO returnedDTO = vendorMapper.vendorToVendorDTO(savedVendor);

        returnedDTO.setVendorurl(getVendorURL(savedVendor.getId()));

        return returnedDTO;

    }

    @Override
    public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {

        vendorDTO.setVendorurl(getVendorURL(id));
        return createNewVendor(vendorDTO);

    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {

        return vendorRepository.findById(id).map(

                vendor -> {

                    if(vendorDTO.getName()!=null)
                    {
                        vendor.setName(vendorDTO.getName());
                    }

                    Vendor savedVendor = vendorRepository.save(vendor);

                    VendorDTO savedDTO = vendorMapper.vendorToVendorDTO(savedVendor);
                    savedDTO.setVendorurl(getVendorURL(savedVendor.getId()));
                    return savedDTO;
                }
        ).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendorById(Long id) {

        vendorRepository.deleteById(id);

    }
}
