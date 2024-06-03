package projet_microservice.olympique;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "match")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Match {

    // data are like below
//drop table if exists planning;
//create table if not exists match
//(
//    nom_site varchar(100),
//    sport    varchar(50),
//    date       date,
//    latitude   double precision,
//    longitude  double precision,
//    primary key (date, nom_site, sport)
//);
//
//INSERT INTO match (date, nom_site, sport, latitude, longitude) VALUES ('2024-08-03 22:00:00.000', 'Arena Bercy', 'Basketball', 48.83863, 2.378597);

    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nom_site")
    private String nomSite;

    @Column(name = "sport")
    private String sport;

    @Column(name = "date")
    private Date date;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

}
