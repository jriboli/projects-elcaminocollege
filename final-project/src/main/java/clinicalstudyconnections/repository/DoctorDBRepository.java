package clinicalstudyconnections.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import clinicalstudyconnections.entity.Doctor;

public interface DoctorDBRepository extends JpaRepository<Doctor, Long> {

}
