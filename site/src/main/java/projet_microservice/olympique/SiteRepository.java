package projet_microservice.olympique;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SiteRepository extends MongoRepository<Site, ObjectId> {
    // CRUD operations are inherited from MongoRepository

    Site findByCode(String code_site);
}
