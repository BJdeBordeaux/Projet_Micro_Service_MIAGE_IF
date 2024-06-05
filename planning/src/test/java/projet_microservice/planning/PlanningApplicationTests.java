package projet_microservice.planning;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PlanningApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testGetterAndSetter() {
        Date creationDate = null;
        try {
            creationDate = DateFormat.getInstance().parse("2021-01-01");
        } catch (Exception e) {
            System.out.println("Error while parsing date");
        }
        Planning planning = new Planning(1, 0, creationDate, "Escrime", "Grand Palais", 48.000001, 2.000001);
        assertEquals(1, planning.getId());
        assertEquals(0, planning.getIdPerson());
        assertEquals(creationDate, planning.getDate());
        assertEquals("Escrime", planning.getSport());
        assertEquals("Grand Palais", planning.getNomSite());
        assertEquals(48.000001, planning.getLatitude());
        assertEquals(2.000001, planning.getLongitude());
    }
}
