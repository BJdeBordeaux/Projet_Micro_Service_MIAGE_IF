package projet_microservice.site;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/site")
@AllArgsConstructor
public class SiteController {

    private final SiteRepository siteRepository;

    @GetMapping("/{code_site}")
    public Site getSite(@PathVariable String code_site) {
        return siteRepository.findByCode(code_site);
    }

    @DeleteMapping("/{code_site}")
    public Site deleteSite(@PathVariable String code_site) {
        Site site = siteRepository.findByCode(code_site);
        if (site == null) {
            return null;
        }
        siteRepository.deleteById(site.getId());
        // if the site is not found, return site, successfully deleted
        // else return null, failed to delete
        if (siteRepository.findById(site.getId()).isEmpty()) {
            return site;
        } else {
            return null;
        }
    }

    @PutMapping("/{code_site}")
    public Site updateSite(@RequestBody Site site, @PathVariable String code_site) {
        Site site1 = siteRepository.findByCode(code_site);
        if (site1 != null) {
            // update the site from the request body
            if (site.getNom() != null) {
                site1.setNom(site.getNom());
            }
            if (site.getCategory_id() != null) {
                site1.setCategory_id(site.getCategory_id());
            }
            if (site.getLatitude() != 0) {
                site1.setLatitude(site.getLatitude());
            }
            if (site.getLongitude() != 0) {
                site1.setLongitude(site.getLongitude());
            }
            siteRepository.save(site1);
            return site1;
        } else {
            return null;
        }
    }

    @GetMapping
    public List<Site> getSites() {
        return siteRepository.findAll();
    }

    @PostMapping
    public Site createSite(@RequestBody Site site) {
        // check if the site already exists
        if (siteRepository.findByCode(site.getCode()) != null) {
            return null;
        }
        siteRepository.save(site);
        return site;
    }
}