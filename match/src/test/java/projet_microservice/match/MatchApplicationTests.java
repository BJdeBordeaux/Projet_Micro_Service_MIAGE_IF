package projet_microservice.match;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MatchApplicationTests {

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
        Match match = new Match(0, "Grand Palais", "Escrime", creationDate, 48.000001, 2.000001);
        assertEquals(0, match.getId());
        assertEquals("Grand Palais", match.getNomSite());
        assertEquals("Escrime", match.getSport());
        assertEquals(creationDate, match.getDate());
        assertEquals(48.000001, match.getLatitude());
        assertEquals(2.000001, match.getLongitude());

    }
}
