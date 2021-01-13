package com.agrobalkan.repository.jpa;

import com.agrobalkan.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.Arrays;

@Service
public class DbInit implements CommandLineRunner {
    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    ProductTypeRepository productTypeRepository;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        // Insert user roles
        UserRole userRole1 = new UserRole(1L, "ROLE_ADMIN");
        UserRole userRole2 = new UserRole(2L, "ROLE_USER");

        userRoleRepository.saveAll(Arrays.asList(userRole1, userRole2));
        userRoleRepository.flush();

        // Insert product types
        ProductType productType1 = new ProductType(1L, "Tractor", "{\"No of cylinder\":\"No of cylinder\",\"Capacity CC\":\"Capacity CC\",\"Transmission Type\":\"Transmission Type\",\"Brake Type\":\"Brake Type\",\"Steering Type\":\"Steering Type\",\"Fuel Tank Capacity LIT\":\"Fuel Tank Capacity LIT\"}");
        ProductType productType2 = new ProductType(2L, "Motocultivator", "{\"Motor\":\"Motor\",\"Cylinder\":\"Cylinder\",\"Working width\":\"Working width\",\"Gears\":\"Gears\",\"Wheels\":\"Wheels\",\"Weight\":\"Weight\"}");

        productTypeRepository.saveAll(Arrays.asList(productType1, productType2));
        productTypeRepository.flush();

        Country country1 = new Country(1L, "Macedonia");
        Country country2 = new Country(2L, "Albania");
        Country country3 = new Country(3L, "Kosova");

        countryRepository.saveAll(Arrays.asList(country1, country2, country3));
        countryRepository.flush();

        User user = new User(1L, "kadir", "Kadir", "$2a$16$xh2ALPPKdVaTxfJs2Ex1rekvY4g3yeJczVH6CR23XW5wEEQVK3yL6", "Ali", "kadir.ali@hotmail.com", userRoleRepository.findById(1L).get());
        userRepository.save(user);
        userRepository.flush();

        // Insert products
        // Tractors
        Product p1 = new Product(1L, /*new Date("2018-08-15 14:15:11")*/ new Date(), "arjun_605.jpg", "ARJUN NOVO 605 DI-PS", readImage("arjun_605.jpg"), 55000.0, "{\"No of cylinder\":\"4\",\"Capacity CC\":\"3531\",\"Transmission Type\":\"Mechanical, Synchromesh\",\"Brake Type\":\"Mechanical, oil immersed multi disc brakes\",\"Steering Type\":\"Power Steering\",\"Fuel Tank Capacity LIT\":\"66\"}",  countryRepository.findById(2L).get(), productTypeRepository.findById(1L).get());
        Product p2 = new Product(2L, /*new Date("2018-08-24 09:34:10")*/ new Date(), "arjun_international.jpg", "ARJUN INTERNATIONAL", readImage("arjun_international.jpg"), 75000.0, "{\"No of cylinder\":\"4\",\"Capacity CC\":\"3329\",\"Transmission Type\":\"Full Synchromesh\",\"Brake Type\":\"Oil immersed brakes\",\"Steering Type\":\"Power Steering\",\"Fuel Tank Capacity LIT\":\"60\"}",  countryRepository.findById(3L).get(), productTypeRepository.findById(1L).get());
        Product p3 = new Product(3L, /*new Date("2018-10-22 10:34:15")*/ new Date(), "mahindra_555.jpg",  "MAHINDRA 555 DI POWER PLUS", readImage("mahindra_555.jpg"), 52500.0, "{\"No of cylinder\":\"4\",\"Capacity CC\":\"3532\",\"Transmission Type\":\"Full constant Mesh (Optional)\",\"Brake Type\":\"Oil Immersed Multi Disc Brakes\",\"Steering Type\":\"Double acting Power steering\",\"Fuel Tank Capacity LIT\":\"69\"}",  countryRepository.findById(2L).get(), productTypeRepository.findById(1L).get());
        Product p4 = new Product(4L, /*new Date("2018-12-24 22:34:22")*/ new Date(), "mahindra_265.jpg",  "MAHINDRA 265 DI", readImage("mahindra_265.jpg"), 27500.0, "{\"No of cylinder\":\"3\",\"Capacity CC\":\"2048\",\"Transmission Type\":\"Partial Constant Mesh (Optional)\",\"Brake Type\":\"Oil Brakes\",\"Steering Type\":\"Power steering (optional)\",\"Fuel Tank Capacity LIT\":\"45\"}",  countryRepository.findById(1L).get(), productTypeRepository.findById(1L).get());

        //   Motocultivator
        Product p5 = new Product(5L, /*new Date("2018-12-25 08:08:15")*/ new Date(), "Bertolini_400_E.jpg",  "Bertolini 400 E", readImage("Bertolini_400_E.jpg"), 2500.0, "{\"Motor\":\"Emak K 700H OHV\",\"Cylinder\":\"182 cm³\",\"Working width\":\"50 cm\",\"Gears\":\"1 forwards + 1 reverse\",\"Wheels\":\"4.00-8”\",\"Weight\":\"75 kg\"}",  countryRepository.findById(1L).get(), productTypeRepository.findById(2L).get());
        Product p6 = new Product(6L, /*new Date("2018-12-26 22:22:22")*/ new Date(), "Benassi_MC2300_H.jpg",  "Benassi MC2300 H", readImage("Benassi_MC2300_H.jpg"), 3200.0, "{\"Motor\":\"Honda GX160\",\"Cylinder\":\"163 cm³\",\"Working width\":\"50 cm\",\"Gears\":\"1 forwards + 1 reverse\",\"Wheels\":\"3.50-8”\",\"Weight\":\"71 kg\"}",  countryRepository.findById(1L).get(), productTypeRepository.findById(2L).get());
        Product p7 = new Product(7L, /*new Date("2019-01-01 11:34:15")*/ new Date(), "Bertolini_401S_H.jpg",  "Bertolini 401S H", readImage("Bertolini_401S_H.jpg"), 4100.0, "{\"Motor\":\"Honda GX160 OHV\",\"Cylinder\":\"163 cm³\",\"Working width\":\"50 cm\",\"Gears\":\"1 forwards + 1 reverse\",\"Wheels\":\"4.00-8”\",\"Weight\":\"75 kg\"}",  countryRepository.findById(2L).get(), productTypeRepository.findById(2L).get());
        Product p8 = new Product(8L, /*new Date("2019-01-01 17:34:22")*/ new Date(), "Bertolini_407S_H_BM.jpg",  "Bertolini 407S H BM", readImage("Bertolini_407S_H_BM.jpg"), 2100.0, "{\"Motor\":\"Honda GX200 OHV\",\"Cylinder\":\"196 cm³\",\"Working width\":\"60 cm\",\"Gears\":\"2 forwards + 2 reverse\",\"Wheels\":\"4.00-10”\",\"Weight\":\"120 kg\"}",  countryRepository.findById(3L).get(), productTypeRepository.findById(2L).get());

        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8));
        productRepository.flush();
    }

    private byte[] readImage(String imageName) throws IOException {
        String fileName = "static/assets/images/products/" + imageName;
        ClassLoader classLoader = getClass().getClassLoader();

        File file = new File(classLoader.getResource(fileName).getFile());

        //File is found
        System.out.println("File Found : " + file.exists());

        //Read File Content
        return Files.readAllBytes(file.toPath());
    }
}
