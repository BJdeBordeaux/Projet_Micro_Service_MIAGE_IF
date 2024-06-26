package projet_microservice.planning;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PlanningRepository extends JpaRepository<Planning, Long> {

    List<Planning> findAllByIdPerson(long idPerson);

    List<Planning> findAllByIdPersonAndDateAndNomSiteAndSport(long id_person, Date date, String nomSite, String sport);
//
//    List<Planning> findAllByDateAndNomSite(Date date, String nomSite);
//
//    List<Planning> findAllByDateAndSport(Date date, String sport);
//
//    List<Planning> findAllByDate(Date date);

//    // aggregation functions
//    @Query(value="SELECT m.nom_site " +
//            "FROM Match m " +
//            "GROUP BY m.nom_site, m.date " +
//            "HAVING m.date = :date", nativeQuery = true)
//    List<Object> findSiteByDate(@Param("date") Date date);
//
//    @Query(value="SELECT m.nom_site " +
//            "FROM Match m " +
//            "GROUP BY m.nom_site, m.date, m.sport " +
//            "HAVING m.date = :date AND m.sport in :sports",
//            nativeQuery = true)
//    List<Object> findSitesByDateAndSports(@Param("date") Date date, @Param("sports") List<String> sports);
//
//    @Query(value="SELECT m.nom_site " +
//            "FROM Match m " +
//            "GROUP BY m.nom_site, m.sport " +
//            "HAVING m.sport in :sports",
//            nativeQuery = true)
//    List<Object> findSitesBySports(@Param("sports") List<String> sports);
//
//    @Query(value="SELECT distinct m.sport " +
//            "FROM Match m " +
//            "WHERE m.date = :date AND m.nom_site = :site ",
//            nativeQuery = true)
//    List<Object> findSportsByDateAndSite(@Param("date") Date parsedDate, @Param("site") String site);
//
//    @Query(value="SELECT distinct m.sport " +
//            "FROM Match m " +
//            "WHERE m.date = :date",
//            nativeQuery = true)
//    List<Object> findSportsByDate(@Param("date") Date date);
//
//    @Query(value="SELECT m.sport " +
//            "FROM Match m " +
//            "WHERE m.nom_site = :site",
//            nativeQuery = true)
//    List<Object> findSportsBySite(@Param("site") String site);
}
