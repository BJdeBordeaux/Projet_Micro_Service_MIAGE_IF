package projet_microservice.site;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SiteApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testGetterAndSetter() {
        projet_microservice.site.Site site = new Site(new ObjectId(), "GRP", "Grand Palais", "venue-olympic", 48.86616355, 2.3125474);
        assertEquals("GRP", site.getCode());
        assertEquals("Grand Palais", site.getNom());
        assertEquals("venue-olympic", site.getCategory_id());
        assertEquals(48.86616355, site.getLatitude());
        assertEquals(2.3125474, site.getLongitude());
    }

}
