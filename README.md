# ORM JPA Hibernate Spring Data

## Creation of the JPA entity Patient with the following attributes:
````Java
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private Date dateNaissance;
    private boolean malade;
    private int score;
}
````
## Creation of the PatientRepository interface that extends the JpaRepository interface:

````Java
import org.example.springboottp2.ex1_7.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
````
## SpringBootApp class

````Java
import org.example.springboottp2.ex1_7.entities.Patient;
import org.example.springboottp2.ex1_7.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class SpringBootTp2Application implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTp2Application.class, args);
    }

    public void run(String... args) throws Exception {

        //add patients
        patientRepository.save(new Patient(null, "Hassan", new Date(), false, 0));
        patientRepository.save(new Patient(null, "mohamed", new Date(), true, 29));
        patientRepository.save(new Patient(null, "khalid", new Date(), true, 19));

        //find all patients
        System.out.println(patientRepository.findAll());

        //find patient by id
        System.out.println(patientRepository.findById(1L));

        //delete patient by id
        patientRepository.delete(patientRepository.findById(1L).get());

        //update patient
        Patient patient = patientRepository.findById(2L).get();
        patient.setNom("mohamed updated");
        patientRepository.save(patient);
        System.out.println(patientRepository.findAll());


    }
}
````
## H2 Database:
- Add the following properties to the application.properties file:
````properties
server.port=8086
spring.datasource.url=jdbc:h2:mem:patients-db
spring.h2.console.enabled=true
````
To access your H2 database, enter http://localhost:8086/h2-console in your browser, enter the correct JDBC URL, and then click Connect
## Migration from H2 Database to MySQL Database:
- Add the following properties to the application.properties file:
````properties
spring.datasource.url=jdbc:mysql://localhost:3306/patientdb?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
````
- Add the following dependency to the pom.xml file:
````xml
<dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <scope>runtime</scope>
</dependency>
````
