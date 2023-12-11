package clinicalstudyconnections.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import clinicalstudyconnections.entity.ClinicalStudy;
import clinicalstudyconnections.entity.Specialty;

public interface ClinicalStudyDBRepository extends JpaRepository<ClinicalStudy, Long> {

	List<ClinicalStudy> findBySpecialty(Specialty specialty);
}
