package clinicalstudyconnections;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.gson.Gson;

import clinicalstudyconnections.model.ClinicalStudyData;
import clinicalstudyconnections.model.DoctorData;
import clinicalstudyconnections.model.OwnerData;
import clinicalstudyconnections.model.PatientData;
import clinicalstudyconnections.model.SiteData;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		// Writing some code to call the API to simulate usage
		Gson gson = new Gson();
		
		// ----------------------------------------------------------------------------------------
		// Create a New Owner
		// ----------------------------------------------------------------------------------------
		String ownerBody = "{\r\n"
				+ "    \"ownerFirstName\" : \"Kryus\",\r\n"
				+ "    \"ownerLastName\" : \"Lagoon\",\r\n"
				+ "    \"companyName\" : \"Friendship Industries\"\r\n"
				+ "}";
		
		String ownerUrl = "http://localhost:8080/clinical-study-connection/owner";
		
		//Map<String, String> headers = new HashMap<>();
		//headers.put("Content-Type", "application/json");
		//headers.put("Host", "localhost:8080");
		
//		HttpRequest createOwnerReq = HttpRequest.newBuilder()
//				.uri(URI.create("http://localhost:8080/clinical-study-connection/owner"))
//				.POST(BodyPublishers.ofString(ownerBody))
//				//.headers(headers.entrySet().stream()
//				//		.map(e -> e.getKey() + ":" + e.getValue())
//				//		.toArray(String[]::new))
//				.header("Content-Type", "application/json" )
//				.build();
//		HttpResponse<String> createOwnerRes = null;
//		
//		try {
//			createOwnerRes = HttpClient.newHttpClient().send(createOwnerReq, HttpResponse.BodyHandlers.ofString());
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		System.out.println(createOwnerRes.body());
		
		String createOwnerResponse = HttpPOST(ownerUrl, ownerBody);		
		OwnerData ownerObj = gson.fromJson(createOwnerResponse, OwnerData.class);
		System.out.println("New Owner ID is : " + ownerObj.getOwnerId());
		
		// ----------------------------------------------------------------------------------------
		// Create a Site for New Owner
		// ----------------------------------------------------------------------------------------
		String siteBody = "{\r\n"
				+ "    \"siteName\" : \"Testing 001\",\r\n"
				+ "    \"siteAddress\" : \"Somewhere\",\r\n"
				+ "    \"siteCity\" : \"Somehow\",\r\n"
				+ "    \"siteState\" : \"CA\",\r\n"
				+ "    \"siteZip\" : \"11111\",\r\n"
				+ "    \"sitePhone\" : \"8885554444\"\r\n"
				+ "}";
		
		String siteUrl = "http://localhost:8080/clinical-study-connection/owner/"+ownerObj.getOwnerId()+"/site";
	
		String createSiteResponse = HttpPOST(siteUrl, siteBody); 
		SiteData siteObj = gson.fromJson(createSiteResponse, SiteData.class);	
		System.out.println("New Site ID is : " + siteObj.getSiteId());
		
		// ----------------------------------------------------------------------------------------
		// Create Doctors
		// ----------------------------------------------------------------------------------------
		String doctorBody = "{\r\n"
				+ "    \"doctorFirstName\" : \"James\",\r\n"
				+ "    \"doctorLastName\" : \"Gunn\"\r\n"
				+ "}";
		
		String doctorUrl = "http://localhost:8080/clinical-study-connection/owner/"+ownerObj.getOwnerId()+"/doctor";
		
		String createDoctorResponse = HttpPOST(doctorUrl, doctorBody);
		DoctorData doctorObj = gson.fromJson(createDoctorResponse, DoctorData.class);
		System.out.println("New Doctor ID is : " + doctorObj.getDoctorId());
		
		// ----------------------------------------------------------------------------------------
		// Enroll Doctor to Site
		// ----------------------------------------------------------------------------------------
		String enrollDoctor = "";
		String enrollDoctorUrl = "http://localhost:8080/clinical-study-connection/owner/"+ownerObj.getOwnerId()+"/site/"+siteObj.getSiteId()+"/doctor/"+doctorObj.getDoctorId();
		
		String enrollDoctorResponse = HttpPOST(enrollDoctorUrl, enrollDoctor);
		System.out.println(enrollDoctorResponse);
		// Check for 200 HTTP CODE
		
		// ----------------------------------------------------------------------------------------
		// Find Study
		// ----------------------------------------------------------------------------------------
		String studyBody = "";
		String studyUrl = "http://localhost:8080/clinical-study-connection/study/4";
		
		String studyResponse = HttpGET(studyUrl);
		ClinicalStudyData studyObj = gson.fromJson(studyResponse, ClinicalStudyData.class);
		System.out.println("Found Study ID is : " + studyObj.getClinicalStudyId());
		
		// ----------------------------------------------------------------------------------------
		// Apply to Study
		// ----------------------------------------------------------------------------------------
		String enrollSite4StudyBody = "";
		String enrollSite4StudyUrl = "http://localhost:8080/clinical-study-connection/study/"+studyObj.getClinicalStudyId()+"/site/"+siteObj.getSiteId();
		
		String enrollSiteResponse = HttpPOST(enrollSite4StudyUrl, enrollSite4StudyBody);
		System.out.println(enrollSiteResponse);
		// Check for 200 HTTP CODE
		
		// ----------------------------------------------------------------------------------------
		// Create Patient
		// ----------------------------------------------------------------------------------------
		String patientBody = "{\r\n"
				+ "    \"patientFirstName\" : \"Sonic\",\r\n"
				+ "    \"patientLastName\" : \"The Hedgehog\",\r\n"
				+ "    \"patientAge\" : 109,\r\n"
				+ "    \"patientSex\" : \"MALE\"\r\n"
				+ "}";
		
		String patientUrl = "http://localhost:8080/clinical-study-connection/patient";
		
		String createPatientResponse = HttpPOST(patientUrl, patientBody);
		PatientData patientObj = gson.fromJson(createPatientResponse, PatientData.class);
		System.out.println("New Patient ID is : " + patientObj.getPatientId());
		
		// ----------------------------------------------------------------------------------------
		// Enroll Patient for Study
		// ----------------------------------------------------------------------------------------
		String enrollPatient4StudyBody = "";
		String enrollPatient4StudyUrl = "http://localhost:8080/clinical-study-connection/study/"+studyObj.getClinicalStudyId()+"/patient/"+patientObj.getPatientId();
		
		String enrollPatientResponse = HttpPOST(enrollPatient4StudyUrl, enrollPatient4StudyBody);
		System.out.println(enrollPatientResponse);
		// Check for 200 HTTP CODE

	}
	
	public static String HttpPOST(String url, String body) {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.POST(BodyPublishers.ofString(body))
				.header("Content-Type", "application/json" )
				.build();
		HttpResponse<String> response = null;
		
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return response.body();
	}
	
	public static String HttpGET(String url) {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = null;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return response.body();
	}

}
