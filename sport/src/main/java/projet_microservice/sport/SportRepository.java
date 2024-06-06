package projet_microservice.sport;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SportRepository extends MongoRepository<Sport, ObjectId> {
    // CRUD operations are inherited from MongoRepository

    Sport findByCode(String code_sport);

    Sport findByNom(String nom_sport);
}
