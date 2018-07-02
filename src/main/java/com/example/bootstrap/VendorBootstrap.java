package com.example.bootstrap;

import com.example.domain.Vendor;
import com.example.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class VendorBootstrap implements CommandLineRunner {

    private final VendorRepository vendorRepository;

    public VendorBootstrap(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Vendor vendor = new Vendor();
        vendor.setName("Home Fruits");

        Vendor vendor1 = new Vendor();
        vendor1.setName("Nuts & Nuts");

        vendorRepository.save(vendor);
        vendorRepository.save(vendor1);

        System.out.println("Vendor Data loaded = "+ vendorRepository.count());

    }
}
