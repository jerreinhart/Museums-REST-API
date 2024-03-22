package museums.controller.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;
import museums.entity.Exhibition;
import museums.entity.Installation;
import museums.entity.Museum;
	
@Data
@NoArgsConstructor
public class MuseumsData {
	private Long museumId;
	private String museumName;
	private String museumAddress;
	private String museumCity;
	private String museumCountry;
	private String museumZip;
	private String museumPhone;
	private String museumWebsite;
	
	private Set<MuseumInstallation> installations = new HashSet<>();
	private Set<MuseumExhibition> exhibitions = new HashSet<>();
	
	public MuseumsData(Museum museum) {
	museumId = museum.getMuseumId();
	museumName = museum.getMuseumName();
	museumAddress = museum.getMuseumAddress();
	museumCity = museum.getMuseumCity();
	museumCountry = museum.getMuseumCountry();
	museumZip = museum.getMuseumZip();
	museumPhone = museum.getMuseumPhone();
	museumWebsite = museum.getMuseumWebsite();
	
	for (Exhibition exhibition : museum.getExhibitions()) {
		exhibitions.add(new MuseumExhibition(exhibition));
	}
	for (Installation installation : museum.getInstallations()) {
		installations.add(new MuseumInstallation(installation));
	}
	}
	
	@Data
	@NoArgsConstructor
	public static class MuseumExhibition {
		private Long exhibitionId;
		private String nameOfExhibition;	
		private String ownerOfExhibition;
		private String typeOfExhibition;
		private Date dateOfExhibition;
		
	public MuseumExhibition(Exhibition exhibition) {
		exhibitionId = exhibition.getExhibitionId();
		nameOfExhibition = exhibition.getNameOfExhibition();
		ownerOfExhibition = exhibition.getOwnerOfExhibition();
		typeOfExhibition = exhibition.getTypeOfExhibition();
		dateOfExhibition = exhibition.getDateOfExhibition();
	}
	}
	
	@Data
	@NoArgsConstructor
	public static class MuseumInstallation {
		private Long installationId;
		private String nameOfInstallation;
		private String typeOfInstallation;
		
	public MuseumInstallation(Installation installation) {
		installationId = installation.getInstallationId();
		nameOfInstallation = installation.getNameOfInstallation();
		typeOfInstallation = installation.getTypeOfInstallation();
	}
	}
	
	
}
