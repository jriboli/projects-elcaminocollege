package clinicalstudyconnections.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import clinicalstudyconnections.entity.ClinicalStudy;
import clinicalstudyconnections.entity.Patient;
import clinicalstudyconnections.entity.Site;

public interface ClinicalStudyDBRepository extends JpaRepository<ClinicalStudy, Long> {

}
