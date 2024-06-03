package projet_microservice.olympique;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("sport")
@AllArgsConstructor
@Data
public class Sport {

    // data are like below
//    "Code_Sport": "FEN",
//    "Sport": "Escrime",
//    "presentation": "L'escrime est un sport de combat utilisant une épée, un sabre ou un fleuret."

    @Id
    @Field("_id")
    private ObjectId id;

    @Field("Code_Sport")
    private String code;

    @Field("Sport")
    private String nom;

    private String presentation;

}
