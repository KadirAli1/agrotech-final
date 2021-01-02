package com.agrobalkan.repository.jpa;

import com.agrobalkan.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {

}
