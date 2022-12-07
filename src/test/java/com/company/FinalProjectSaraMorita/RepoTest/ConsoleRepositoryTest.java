package com.company.FinalProjectSaraMorita.RepoTest;

import com.company.FinalProjectSaraMorita.models.Console;
import com.company.FinalProjectSaraMorita.repositories.ConsoleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ConsoleRepositoryTest {

    @Autowired
    ConsoleRepository consoleRepository;

    @Before
    public void setUp() throws Exception
    {
        consoleRepository.deleteAll();
    }

    @Test
    public void shouldAddGetDeleteConsole()
    {
        Console console = new Console();
        console.setModel("Nintendo Switch");
        console.setManufacturer("Nintendo");
        console.setMemoryAmount("100 GB");
        console.setProcessor("New CPI");
        console.setPrice(new BigDecimal("399.50"));
        console.setQuantity(10);

        console = consoleRepository.save(console);

        Optional<Console> testConsole = consoleRepository.findById(console.getId());
        assertEquals(testConsole.get(),console);

        consoleRepository.deleteById(console.getId());
        testConsole = consoleRepository.findById(console.getId());
        assertFalse(testConsole.isPresent());
    }

    @Test
    public void shouldUpdateConsole()
    {
        Console console = new Console();
        console.setModel("Nintendo Switch");
        console.setManufacturer("Nintendo");
        console.setMemoryAmount("100 GB");
        console.setProcessor("New CPI");
        console.setPrice(new BigDecimal("399.50"));
        console.setQuantity(10);

        console = consoleRepository.save(console);

        console.setQuantity(1);
        consoleRepository.save(console);

        Optional<Console> testConsole = consoleRepository.findById(console.getId());
        assertEquals(testConsole.get(), console);
    }

    @Test
    public void shouldFindAllConsoles()
    {
        Console console = new Console();
        console.setModel("Nintendo Switch");
        console.setManufacturer("Nintendo");
        console.setMemoryAmount("100 GB");
        console.setProcessor("New CPI");
        console.setPrice(new BigDecimal("399.50"));
        console.setQuantity(10);
        console = consoleRepository.save(console);

        Console console2 = new Console();
        console2.setModel("Nintendo Switch 2");
        console2.setManufacturer("Nintendo");
        console2.setMemoryAmount("200 GB");
        console2.setProcessor("New CPI");
        console2.setPrice(new BigDecimal("499.50"));
        console2.setQuantity(5);
        console2 = consoleRepository.save(console2);

        Console console3 = new Console();
        console3.setModel("Nintendo Switch 3");
        console3.setManufacturer("Nintendo");
        console3.setMemoryAmount("100 GB");
        console3.setProcessor("Nintendo INC");
        console3.setPrice(new BigDecimal("599.50"));
        console3.setQuantity(3);
        console3 = consoleRepository.save(console3);

        List<Console> allConsoles = consoleRepository.findAll();
        assertEquals(allConsoles.size(),3);
    }






}
