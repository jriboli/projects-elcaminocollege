package clinicalstudyconnections.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
/*
 * --- BEGINNING of EXPERIMENTATION 001 -----------------------------------------------------------------
 * 
 * This is an intermediate entity that holds additional information about the Study to Patient relationship
 */

/*
 * @Entity
 * 
 * @Data public class StudyPatient {
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
 * 
 * @ManyToOne
 * 
 * @JoinColumn(name = "study_id") private ClinicalStudy clinicalStudy;
 * 
 * @ManyToOne
 * 
 * @JoinColumn(name = "patient_id") private Patient patient;
 * 
 * private Date enrollmentDate;
 * 
 * public StudyPatient() { // Default constructor }
 * 
 * public StudyPatient(ClinicalStudy clinicalStudy, Patient patient, Date
 * enrollmentDate) { this.clinicalStudy = clinicalStudy; this.patient = patient;
 * this.enrollmentDate = enrollmentDate; } }
 */

/*
 * --- END of EXPERIMENTATION 001 -----------------------------------------------------------------
 */
