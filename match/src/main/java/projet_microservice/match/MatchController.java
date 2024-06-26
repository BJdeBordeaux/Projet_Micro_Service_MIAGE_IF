package projet_microservice.match;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/match")
@AllArgsConstructor
public class MatchController {

    private final MatchRepository matchRepository;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/{id}")
    public Optional<Match> getMatch(@PathVariable long id) {
        return matchRepository.findById(id).map(Optional::of).orElse(null);
    }

    @GetMapping("/find")
//    public List<Match> getMatchByDateAndNomSiteAndSport(@RequestBody String date, @RequestBody String nomSite, @RequestBody String sport) {
    public List<Match> getMatchByDateAndNomSiteAndSport(@RequestBody Match match) {
        Date date1 = null;
        String nomSite = match.getNomSite();
        String sport = match.getSport();

        // convert the date string to a Date object
        try {
            date1 = match.getDate();
        } catch (Exception e) {
            return null;
        }
        if (date1 == null) {
            return null;
        }

        // verify if the sport is valide
        boolean sportValide = !(sport == null || sport.isBlank());

        // verify if the site is valide
        boolean siteValide = !(nomSite == null || nomSite.isBlank());

        if (sportValide && siteValide) {
            return matchRepository.findAllByDateAndNomSiteAndSport(date1, nomSite, sport);
        } else if (siteValide) {
            return matchRepository.findAllByDateAndNomSite(date1, nomSite);
        } else if (sportValide) {
            return matchRepository.findAllByDateAndSport(date1, sport);
        } else {
            return matchRepository.findAllByDate(date1);
        }
    }

    @DeleteMapping("/{id}")
    public Match deleteMatch(@PathVariable long id) {
        Optional<Match> match = matchRepository.findById(id);
        // if the sport is not found, return null
        // else delete the sport and return the sport
        if (match.isEmpty()) {
            return null;
        } else {
            matchRepository.deleteById(id);
            return match.get();
        }
    }

    @PutMapping("/{id}")
    public Match updateMatch(@RequestBody Match match, @PathVariable long id) {
        Optional<Match> match1 = matchRepository.findById(id);
        String[] modifiedFields = {"nom_site", "sport", "date", "latitude", "longitude"};
        if (match1.isPresent()) {
            // update the sport from the request body
            Match matchEntity = match1.get();
            if (match.getNomSite() != null) {
                matchEntity.setNomSite(match.getNomSite());
            }
            if (match.getSport() != null) {
                matchEntity.setSport(match.getSport());
            }
            if (match.getDate() != null) {
                matchEntity.setDate(match.getDate());
            }
            if (match.getLatitude() != 0) {
                matchEntity.setLatitude(match.getLatitude());
            }
            if (match.getLongitude() != 0) {
                matchEntity.setLongitude(match.getLongitude());
            }
            // save the updated sport
            matchRepository.save(matchEntity);
            return matchEntity;
        } else {
            return null;
        }
    }

    @GetMapping
    public List<Match> getMatches() {
        return matchRepository.findAll();
    }

    @PostMapping
    public Match createMatch(@RequestBody Match match) {
        // check if the sport already exists (same date, same site, same sport)
        List<Match> match1 = matchRepository.findAllByDateAndNomSiteAndSport(match.getDate(), match.getNomSite(), match.getSport());
        if (!match1.isEmpty()) {
            return null;
        }
        // check if all the fields are not null
        if (match.getNomSite() == null || match.getSport() == null || match.getDate() == null || match.getLatitude() == 0 || match.getLongitude() == 0) {
            return null;
        }
        // save the sport
        matchRepository.save(match);
        return match;
    }

    // verify if a match's sport and site is valide
    @GetMapping("/verify")
    public boolean verifyMatch(@RequestBody Match match) {
        String sport = match.getSport();
        String site = match.getNomSite();
        boolean sendVerification = !(sport == null || sport.isBlank() || site == null || site.isBlank());
        if (!sendVerification) {
            return false;
        }

        // verify if the sport and site are valide, sent a request to the sport and site microservices
        // if the sport and site are valide, return true
        // else return false
        RestTemplate restTemplate = new RestTemplate();
        String urlSport = "http://localhost:7902/sport/search_verif/" + sport;
        // send a request to the sport microservice, and parse the result as json
        Map<String, String> sportResult = restTemplate.getForObject(urlSport, Map.class);
        if (sportResult == null) {
            return false;
        }

        String nomSport = sportResult.get("nom");
        if (nomSport == null) {
            return false;
        }
        String urlSite = "http://localhost:7901/site/search_verif/" + site;
        Map<String, String> siteResult = restTemplate.getForObject(urlSite, Map.class);
        if (siteResult == null) {
            return false;
        }
        String nomSite = siteResult.get("nom");
        return nomSite != null;
    }
}