package museums.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import museums.controller.model.MuseumsData;
import museums.service.MuseumsService;

public class MuseumsController {
	@RestController
	@RequestMapping("/pet_store")
	@Slf4j
	public class StoreController {
		@Autowired
		private MuseumsService museumsService;

		@PostMapping()
		@ResponseStatus(code = HttpStatus.CREATED)
		public MuseumsData insertPetStore(@RequestBody MuseumsData museumsData) {
			log.info("Creating the museum {}", museumData);
			return museumsService.saveMuseum(museumsData);
		}

		@PutMapping("/{museumId}")
		public MuseumsData updateMuseum(@PathVariable Long museumId, @RequestBody MuseumsData museumsData) {
			museumsData.setMuseumId(museumId);
			log.info("Updating museum{}", museumsData);
			return museumsService.saveMuseum(museumsData);
		}

		@PostMapping("/{museumId}/installation")
		@ResponseStatus(code = HttpStatus.CREATED)
		public MuseumInstallation insertInstallation(@PathVariable Long museumId,
				@RequestBody MuseumInstallation museumInstallation) {
			log.info("Creating installation {} for museum with ID = {}", museumInstallation, museumId);
			return museumsService.saveInstallation(museumId, museumInstallation);
		}

		@PostMapping("/{museumId}/exhibition")
		@ResponseStatus(code = HttpStatus.CREATED)
		public MuseumExhibition insertExhibition(@PathVariable Long museumId,
				@RequestBody MuseumExhibition museumExhibition) {
			log.info("Creating exhibition {} for museum with ID = {}", museumExhibition, museumId);
			return museumsService.saveExhibition(museumExhibition);
		} 


		@GetMapping()
		public List<MuseumsData> retrieveAllMuseums() {
			log.info("Retrieve all museums.");
			return museumsService.retrieveAllMuseums();
		}

		@GetMapping("/{museumId}")
		public MuseumsData retrieveMuseumById(@PathVariable Long museumId) {
			log.info("Retrieving museum by ID = {}", museumId);
			return museumsService.retrieveMuseumById(museumId);
		}

		@DeleteMapping("/{museumId}")
		public Map<String, String> deleteMuseumById(@PathVariable Long museumId) {
			log.info("Deleting museum with ID = {}", museumId);
			museumsService.deleteMuseumById(museumId);

			return Map.of("message", "Museum with ID = " + museumId + " has been deleted");
		}
	}


}
