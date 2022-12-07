package com.company.FinalProjectSaraMorita.repositories;

import com.company.FinalProjectSaraMorita.models.ProcessingFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessingFeeRepository extends JpaRepository<ProcessingFee, Integer> {
    ProcessingFee findFeeByProductType(String productType);
}
