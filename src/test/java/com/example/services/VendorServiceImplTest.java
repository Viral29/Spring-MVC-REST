package com.example.services;

import com.example.api.v1.mapper.VendorMapper;
import com.example.api.v1.model.VendorDTO;
import com.example.api.v1.model.VendorListDTO;
import com.example.domain.Vendor;
import com.example.repositories.VendorRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

public class VendorServiceImplTest {


    public static final String NAME = "Home Fruits";
    public static final long ID = 1L;
    VendorService vendorService;

    @Mock
    VendorRepository vendorRepository;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        vendorService = new VendorServiceImpl(vendorRepository,VendorMapper.INSTANCE);
    }

    @Test
    public void getAllVendor() {

        List<Vendor> vendorList = Arrays.asList(new Vendor(),new Vendor());

        given(vendorRepository.findAll()).willReturn(vendorList);

        VendorListDTO vendorDTOList = vendorService.getAllVendor();

        then(vendorRepository).should(times(1)).findAll();
        assertThat(vendorDTOList.getVendors().size(),Matchers.is(equalTo(2)));
    }

    @Test
    public void getVendorById() {
        Vendor vendor = new Vendor();
        vendor.setName(NAME);
        vendor.setId(ID);

        given(vendorRepository.findById(anyLong())).willReturn(java.util.Optional.ofNullable(vendor));

        VendorDTO vendorDTO = vendorService.getVendorById(ID);

        then(vendorRepository).should(times(1)).findById(anyLong());
        assertThat(vendorDTO.getName(),is(equalTo(NAME)));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getVendorByIdNotFound(){

        given(vendorRepository.findById(anyLong())).willReturn(Optional.empty());

        VendorDTO vendorDTO = vendorService.getVendorById(ID);

        then(vendorRepository).should(times(1)).findById(anyLong());

    }


    @Test
    public void findVendorByName() {
    }

    @Test
    public void createNewVendor() {

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor savedVendor = new Vendor();
        savedVendor.setId(ID);
        savedVendor.setName(NAME);

        given(vendorRepository.save(any(Vendor.class))).willReturn(savedVendor);

        VendorDTO savedDTO = vendorService.createNewVendor(vendorDTO);

        then(vendorRepository).should().save(any(Vendor.class));
        assertThat(savedDTO.getVendorurl(),containsString("1"));

    }

    @Test
    public void saveVendorByDTO() {

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor savedVendor = new Vendor();
        savedVendor.setId(ID);
        savedVendor.setName(NAME);

        given(vendorRepository.save(any(Vendor.class))).willReturn(savedVendor);

        VendorDTO savedDTO = vendorService.saveVendorByDTO(ID,vendorDTO);

        then(vendorRepository).should().save(any(Vendor.class));
        assertThat(savedDTO.getVendorurl(),containsString("1"));
    }

    @Test
    public void deleteVendorById() {

        vendorService.deleteVendorById(ID);

        then(vendorRepository).should(times(1)).deleteById(anyLong());
    }

    @Test
    public void patchVendor() {

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor vendor = new Vendor();
        vendor.setName(NAME);
        vendor.setId(ID);

        given(vendorRepository.findById(anyLong())).willReturn(java.util.Optional.ofNullable(vendor));
        given(vendorRepository.save(any(Vendor.class))).willReturn(vendor);

        VendorDTO savedDTO = vendorService.patchVendor(ID,vendorDTO);

        then(vendorRepository).should().save(any(Vendor.class));
        then(vendorRepository).should(times(1)).findById(anyLong());
        assertThat(savedDTO.getVendorurl(),containsString("1"));

    }
}