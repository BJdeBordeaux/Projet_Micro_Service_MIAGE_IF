package projet_microservice.olympique;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/planning")
@AllArgsConstructor
public class PlanningController {

    private final PlanningRepository planningRepository;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/{person_id}")
    public List<Planning> getPlanning(@PathVariable long person_id) {
        return planningRepository.findAllByIdPerson(person_id);
    }

    @DeleteMapping("/{id}")
    public Planning deletePlanning(@PathVariable long id) {
        Optional<Planning> planning = planningRepository.findById(id);
        // if the sport is not found, return null
        // else delete the planning and return the deleted planning
        if (planning.isEmpty()) {
            return null;
        } else {
            planningRepository.deleteById(id);
            return planning.get();
        }
    }

    @PutMapping("/{id}")
    public Planning updatePlanning(@RequestBody Planning planning, @PathVariable long id) {
        Optional<Planning> planningOptional = planningRepository.findById(id);
        String[] modifiedFields = {"nom_site", "sport", "date", "latitude", "longitude"};
        if (planningOptional.isPresent()) {
            // update the sport from the request body
            Planning planningEntity = planningOptional.get();
            if (planning.getNomSite() != null) {
                planningEntity.setNomSite(planning.getNomSite());
            }
            if (planning.getSport() != null) {
                planningEntity.setSport(planning.getSport());
            }
            if (planning.getDate() != null) {
                planningEntity.setDate(planning.getDate());
            }
            if (planning.getLatitude() < 0.0000001 && planning.getLatitude() > -0.0000001) {
                planningEntity.setLatitude(planning.getLatitude());
            }
            if (planning.getLongitude() < 0.0000001 && planning.getLongitude() > -0.0000001) {
                planningEntity.setLongitude(planning.getLongitude());
            }
            // save the updated sport
            planningRepository.save(planningEntity);
            return planningEntity;
        } else {
            return null;
        }
    }

    @GetMapping
    public List<Planning> getAllPlannings() {
        return planningRepository.findAll();
    }

    @PostMapping
    public Planning createPlanning(@RequestBody Planning planning) {
        // check if the sport already exists (same date, same site, same sport)
        List<Planning> planning1 = planningRepository.findAllByIdPersonAndDateAndNomSiteAndSport(planning.getIdPerson(), planning.getDate(), planning.getNomSite(), planning.getSport());
        if (!planning1.isEmpty()) {
            return null;
        }
        // check if all the fields are not null
        if (planning.getNomSite() == null || planning.getSport() == null || planning.getDate() == null
                || (planning.getLatitude() < 0.0000001 && planning.getLatitude() > -0.0000001)
                || planning.getLongitude() < 0.0000001 && planning.getLongitude() > -0.0000001) {
            return null;
        }
        // save the sport
        planningRepository.save(planning);
        return planning;
    }
}