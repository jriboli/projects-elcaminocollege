package clinicalstudyconnections.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import clinicalstudyconnections.entity.Doctor;
import clinicalstudyconnections.entity.Site;

public interface SiteDBRepository extends JpaRepository<Site, Long> {

}
