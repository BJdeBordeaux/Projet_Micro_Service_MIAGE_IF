package projet_microservice.match;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/search")
@AllArgsConstructor
public class SearchController {

    private final MatchRepository matchRepository;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/site")
    public List<Object> searchSitesWithDateAndSports(@RequestParam(required = false) String date, @RequestParam(required = false) String[] sports) {
        // select only sports that are not empty
        List<String> sportSelected = null;
        if (sports != null && sports.length > 0) {
            sportSelected = new ArrayList<>();
            for (String sport : sports) {
                if (sport != null && !sport.isBlank()) {
                    sportSelected.add(sport);
                }
            }
        }

        boolean isSportSelected = sportSelected != null && !sportSelected.isEmpty();

        // convert the date string to a Date object and see if the date is valid
        Date date1 = null;
        boolean isDateValid = true;
        try {
            date1 = Date.valueOf(date);
        } catch (Exception e) {
            isDateValid = false;
        }
        if (date1 == null) {
            isDateValid = false;
        }

        // see if sport is set
        if (isDateValid && isSportSelected) {
            return matchRepository.findSitesByDateAndSports(date1, sportSelected);
        } else if (isDateValid) {
            return matchRepository.findSiteByDate(date1);
        } else if (isSportSelected) {
            return matchRepository.findSitesBySports(sportSelected);
        } else {
            return new ArrayList<>();
        }
    }

    @GetMapping("/sport")
    public List<Object> searchSportsWithDateAndSite(@RequestParam(required = false) String date, @RequestParam(required = false) String site) {
        // input validation
        boolean siteValid = site != null && !site.isBlank();
        Date parsedDate = null;
        boolean dateValid = true;
        try {
            parsedDate = Date.valueOf(date);
        } catch (Exception e) {
            dateValid = false;
        }
        if (parsedDate == null) {
            dateValid = false;
        }

        if (dateValid && siteValid) {
            return matchRepository.findSportsByDateAndSite(parsedDate, site);
        } else if (dateValid) {
            return matchRepository.findSportsByDate(parsedDate);
        } else if (siteValid) {
            return matchRepository.findSportsBySite(site);
        } else {
            return new ArrayList<>();
        }
    }
}