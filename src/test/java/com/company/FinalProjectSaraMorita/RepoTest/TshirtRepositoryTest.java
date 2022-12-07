package com.company.FinalProjectSaraMorita.RepoTest;

import com.company.FinalProjectSaraMorita.models.Tshirt;
import com.company.FinalProjectSaraMorita.repositories.TshirtRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TshirtRepositoryTest {

    @Autowired
    TshirtRepository tshirtRepository;

    @Before
    public void setUp() throws Exception
    {
        tshirtRepository.deleteAll();
    }

    @Test
    public void shouldAddGetDeleteTshirt()
    {
        Tshirt tshirt= new Tshirt();
        tshirt.setSize("Small");
        tshirt.setColor("White");
        tshirt.setDescription("A simple tshirt.");
        tshirt.setPrice(new BigDecimal(9.99));
        tshirt.setQuantity(10);

        tshirt = tshirtRepository.save(tshirt);

        Optional<Tshirt> testTshirt = tshirtRepository.findById(tshirt.getTshirtID());
        assertEquals(testTshirt.get(),tshirt);

        tshirtRepository.deleteById(tshirt.getTshirtID());
        testTshirt = tshirtRepository.findById(tshirt.getTshirtID());
        assertFalse(testTshirt.isPresent());
    }

    @Test
    public void shouldUpdateTshirt()
    {
        Tshirt tshirt= new Tshirt();
        tshirt.setSize("Medium");
        tshirt.setColor("Black");
        tshirt.setDescription("A simple black shirt");
        tshirt.setPrice(new BigDecimal(10.99));
        tshirt.setQuantity(20);

        tshirt = tshirtRepository.save(tshirt);

        tshirt.setQuantity(13);
        tshirtRepository.save(tshirt);

        Optional<Tshirt> testTshirt = tshirtRepository.findById(tshirt.getTshirtID());
        assertEquals(testTshirt.get(),tshirt);
    }





}
