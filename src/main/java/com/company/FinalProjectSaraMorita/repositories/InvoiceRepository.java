package com.company.FinalProjectSaraMorita.repositories;

import com.company.FinalProjectSaraMorita.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
