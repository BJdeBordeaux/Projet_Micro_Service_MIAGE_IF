package projet_microservice.olympique;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "planning")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Planning {

    // data are like below
//    create table planning
//            (
//                    id         serial primary key,
//                    id_person  bigint,
//                    date       date,
//                    sport    varchar(255),
//                    nom_site varchar(255),
//                    latitude   double precision,
//                    longitude  double precision,
//);
//
//    INSERT INTO planning (id_person, date, sport, nom_site, latitude, longitude) VALUES (0, '2024-07-30 00:00:00.000000', 'Volleyball assis', 'Arena Paris Nord', 48.9721, 2.5149);

    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "id_person")
    private long idPerson;

    @Column(name = "date")
    private Date date;

    @Column(name = "sport")
    private String sport;

    @Column(name = "nom_site")
    private String nomSite;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;
}
