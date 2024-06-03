package projet_microservice.olympique;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SportRepository extends MongoRepository<Sport, ObjectId> {
    // CRUD operations are inherited from MongoRepository

    Sport findByCode(String code_sport);
}
