package projet_microservice.olympique;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("site")
@AllArgsConstructor
@Data
public class Site {

    // data are like below
//    "Code_Site": "GRP",
//    "Nom_Site": "Grand Palais",
//    "category_id": "venue-olympic",
//    "latitude": 48.86616355,
//    "longitude": 2.3125474

    @Id
    @Field("_id")
    private ObjectId id;

    @Field("Code_Site")
    private String code;

    @Field("Nom_Site")
    private String nom;

    private String category_id;

    private double latitude;

    private double longitude;


}
