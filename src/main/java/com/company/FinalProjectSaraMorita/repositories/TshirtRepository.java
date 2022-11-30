package com.company.FinalProjectSaraMorita.repositories;

import com.company.FinalProjectSaraMorita.models.Tshirt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class TshirtRepository extends JpaRepository<Tshirt, Integer> {

    List<Tshirt> findTshirtByColor(String color);
    List<Tshirt> findTshirtBySize(String size);


}
