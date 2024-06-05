package projet_microservice.sport;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SportApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testGetterAndSetter() {
        projet_microservice.sport.Sport sport = new Sport(new ObjectId(), "FEN", "Escrime", "L'escrime est un sport de combat utilisant une épée, un sabre ou un fleuret.");
        assertEquals("FEN", sport.getCode());
        assertEquals("Escrime", sport.getNom());
        assertEquals("L'escrime est un sport de combat utilisant une épée, un sabre ou un fleuret.", sport.getPresentation());
    }

}
