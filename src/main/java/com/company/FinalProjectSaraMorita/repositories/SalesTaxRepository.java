package com.company.FinalProjectSaraMorita.repositories;

import com.company.FinalProjectSaraMorita.models.SalesTax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesTaxRepository extends JpaRepository<SalesTax, Integer> {
    SalesTax findRateByState(String state);
}
