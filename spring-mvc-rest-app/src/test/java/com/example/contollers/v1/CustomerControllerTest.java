package com.example.contollers.v1;

import com.example.api.v1.model.CustomerDTO;
import com.example.contollers.RestResponseEntityExceptionHandler;
import com.example.services.CustomerService;
import com.example.services.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static com.example.contollers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest {

    public static final String FIRSTNAME = "Joey";
    public static final String LASTNAME = "Tribbiani";
    public static final long ID = 1L;
    public static final String CUSTOMER_URL = "/api/v1/customer/";
    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).setControllerAdvice(new RestResponseEntityExceptionHandler()).build();
    }

    @Test
    public void getAllCustomers() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(FIRSTNAME);
        customerDTO.setLastname(LASTNAME);
        customerDTO.setCustomerurl(CUSTOMER_URL+ID);

        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setFirstname("Rachel");
        customerDTO1.setLastname("Green");
        customerDTO1.setCustomerurl(CUSTOMER_URL+2);

        List<CustomerDTO> customerDTOList = Arrays.asList(customerDTO,customerDTO1);

        when(customerService.getAllCustomer()).thenReturn(customerDTOList);

        mockMvc.perform(get(CustomerController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers",hasSize(2)));

    }


    @Test
    public void getCustomerById() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(FIRSTNAME);
        customerDTO.setLastname(LASTNAME);
        customerDTO.setCustomerurl(CUSTOMER_URL+ID);

        when(customerService.getCustomerById(anyLong())).thenReturn(customerDTO);

        mockMvc.perform(get(CustomerController.BASE_URL+"/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname",equalTo(FIRSTNAME)));

    }

    @Test
    public void createNewCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(FIRSTNAME);
        customerDTO.setLastname(LASTNAME);

        CustomerDTO returnedDTO = new CustomerDTO();
        returnedDTO.setFirstname(customerDTO.getFirstname());
        returnedDTO.setLastname(customerDTO.getLastname());
        returnedDTO.setCustomerurl(CUSTOMER_URL+ID);

        when(customerService.createNewCustomer(customerDTO)).thenReturn(returnedDTO);

        mockMvc.perform(post(CustomerController.BASE_URL+"/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname",equalTo("Joey")))
                .andExpect(jsonPath("$.customer_url",equalTo(CUSTOMER_URL+"1")));

    }

    @Test
    public void saveCustomer() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(FIRSTNAME);
        customerDTO.setLastname(LASTNAME);

        CustomerDTO returnedDTO = new CustomerDTO();
        returnedDTO.setFirstname(customerDTO.getFirstname());
        returnedDTO.setLastname(customerDTO.getLastname());
        returnedDTO.setCustomerurl(CUSTOMER_URL+ID);

        when(customerService.saveCustomerByDTO(anyLong(),any(CustomerDTO.class))).thenReturn(returnedDTO);

        mockMvc.perform(put(CustomerController.BASE_URL+"/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname",equalTo("Joey")))
                .andExpect(jsonPath("$.lastname",equalTo("Tribbiani")))
                .andExpect(jsonPath("$.customer_url",equalTo(CUSTOMER_URL+"1")));

    }

    @Test
    public void patchCustomer() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(FIRSTNAME);
        customerDTO.setLastname(LASTNAME);

        CustomerDTO returnedDTO = new CustomerDTO();
        returnedDTO.setFirstname(customerDTO.getFirstname());
        returnedDTO.setLastname(customerDTO.getLastname());
        returnedDTO.setCustomerurl(CUSTOMER_URL+ID);

        when(customerService.patchCustomer(anyLong(),any(CustomerDTO.class))).thenReturn(returnedDTO);

        mockMvc.perform(patch(CustomerController.BASE_URL+"/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname",equalTo("Joey")))
                .andExpect(jsonPath("$.lastname",equalTo("Tribbiani")))
                .andExpect(jsonPath("$.customer_url",equalTo(CUSTOMER_URL+"1")));

    }

    @Test
    public void deleteCustomer() throws Exception {

        mockMvc.perform(delete(CustomerController.BASE_URL+"/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService).deleteCustomerById(anyLong());
    }

    @Test
    public void testNotFoundException() throws Exception {

        when(customerService.getCustomerById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(CustomerController.BASE_URL + "/222")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}