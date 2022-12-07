package com.company.FinalProjectSaraMorita.repositories;

import com.company.FinalProjectSaraMorita.models.Console;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsoleRepository extends JpaRepository<Console, Integer> {

    List<Console> findAllConsolesByManufacturer(String manufacturer);
}
