package projet_microservice.olympique;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sport")
@AllArgsConstructor
public class SportController {

    private final SportRepository sportRepository;

    @GetMapping("/{code_sport}")
    public Sport getSport(@PathVariable String code_sport) {
        return sportRepository.findByCode(code_sport);
    }

    @DeleteMapping("/{code_sport}")
    public Sport deleteSport(@PathVariable String code_sport) {
        Sport sport = sportRepository.findByCode(code_sport);
        if (sport == null) {
            return null;
        }
        sportRepository.deleteById(sport.getId());
        // if the sport is not found, return sport, successfully deleted
        // else return null, failed to delete
        if (sportRepository.findById(sport.getId()).isEmpty()) {
            return sport;
        } else {
            return null;
        }
    }

    @PutMapping("/{code_sport}")
    public Sport updateSport(@RequestBody Sport sport, @PathVariable String code_sport) {
        Sport sport1 = sportRepository.findByCode(code_sport);
        if (sport1 != null) {
            // update the sport from the request body
            if (sport.getNom() != null) {
                sport1.setNom(sport.getNom());
            }
            if (sport.getPresentation() != null) {
                sport1.setPresentation(sport.getPresentation());
            }
            sportRepository.save(sport1);
            return sport1;
        } else {
            return null;
        }
    }

    @GetMapping
    public List<Sport> getSports() {
        return sportRepository.findAll();
    }

    @PostMapping
    public Sport createSport(@RequestBody Sport sport) {
        // check if the sport already exists
        if (sportRepository.findByCode(sport.getCode()) != null) {
            return null;
        }
        sportRepository.save(sport);
        return sport;
    }
}