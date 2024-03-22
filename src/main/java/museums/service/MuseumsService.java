package museums.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import museums.controller.model.MuseumsData;
import museums.controller.model.MuseumsData.MuseumExhibition;
import museums.controller.model.MuseumsData.MuseumInstallation;
import museums.dao.ExhibitionDao;
import museums.dao.InstallationDao;
import museums.dao.MuseumDao;
import museums.entity.Exhibition;
import museums.entity.Installation;
import museums.entity.Museum;

@Service
public class MuseumsService {

	@Autowired
	private ExhibitionDao exhibitionDao;

	@Autowired
	private InstallationDao installationDao;

	@Autowired
	private MuseumDao museumDao;

	public MuseumsData saveMuseum(MuseumsData museumsData) {
		Long museumId = museumsData.getMuseumId();
		Museum museum = findOrCreateMuseum(museumId);
		copyMuseumFields(museum, museumsData);
		Museum dbMuseum = museumDao.save(museum);

		return new MuseumsData(dbMuseum);
	}

	private void copyMuseumFields(Museum museum, MuseumsData museumsData) {
		museum.setMuseumId(museumsData.getMuseumId());
		museum.setMuseumName(museumsData.getMuseumName());
		museum.setMuseumAddress(museumsData.getMuseumAddress());
		museum.setMuseumCity(museumsData.getMuseumCity());
		museum.setMuseumCountry(museumsData.getMuseumCountry());
		museum.setMuseumZip(museumsData.getMuseumZip());
		museum.setMuseumPhone(museumsData.getMuseumPhone());
		museum.setMuseumWebsite(museumsData.getMuseumWebsite());

	}

	private Museum findOrCreateMuseum(Long museumId) {
		Museum museum;

		if (Objects.isNull(museum)) {
			museum = new Museum();
		} else {
			museum = findMuseumById(museumId);
		}

		return museum;
	}

	private Museum findMuseumById(Long museumId) {
		return museumId.findById(museumId)
				.orElseThrow(() -> new NoSuchElementException("Museum with ID = " + museumId + " does not exist"));
	}

	@Transactional(readOnly = false)
	public MuseumExhibition saveExhibit(MuseumExhibition exhibitionData) {
		Long exhibitionId = exhibitionData.getExhibitionId();
		Exhibition exhibition = findOrCreateExhibition(exhibitionId, exhibitionData.getNameOfExhibition());

		setFieldsInExhibition(exhibition, exhibitionData);
		return new MuseumExhibition(exhibitionDao.save(exhibition));
	}

	private void setFieldsInExhibition(Exhibition exhibition, MuseumExhibition exhibitionData) {
			exhibition.setExhibitionId(exhibitionData.getExhibitionId());
			exhibition.setNameOfExhibition(exhibitionData.getNameOfExhibition());
			exhibition.setOwnerOfExhibition(exhibition.getOwnerOfExhibition());
			exhibition.setTypeOfExhibition(exhibition.getTypeOfExhibition());
			exhibition.setDateOfExhibition(exhibition.getDateOfExhibition());
		
		}

	private Exhibition findOrCreateExhibition(Long exhibitionId, String nameOfExhibition) {
		Exhibition exhibition;
		if (Objects.isNull(exhibitionId)) {
			Optional<Exhibition> opCust = exhibitionDao.findByNameOfExhibition(nameOfExhibition);
			if (opCust.isPresent()) {
				throw new DuplicateKeyException("Exhibition by the name of " + nameOfExhibition + " already exists");
			}
			exhibition = new Exhibition();
		} else {
			exhibition = findExhibitionById(exhibitionId);
		}
		return exhibition;
	}

	private Exhibition findExhibitionById(Long exhibitionId) {
		return exhibitionDao.findById(exhibitionId)
				.orElseThrow(() -> new NoSuchElementException("Exhibition with ID = " + exhibitionId + " was not found."));

	}

	public MuseumInstallation saveInstallation(Long museumId, MuseumInstallation museumInstallation) {
		Museum museum = findMuseumById(museumId);
		Installation installation = findOrCreateInstallation(museumId, museumInstallation.getInstallationId());
		copyInstallationFields(installation, museumInstallation);
		installation.setMuseum(museum);
		museum.getInstallations().add(installation);

		Installation dbInstallation = installationDao.save(installation);

		return new MuseumInstallation(dbInstallation);

	}

	private void copyInstallationFields(Installation installation, MuseumInstallation museumInstallation) {
		installation.setInstallationId(museumInstallation.getInstallationId());
		installation.setNameOfInstallation(museumInstallation.getNameOfInstallation());
		installation.setTypeOfInstallation(museumInstallation.getTypeOfInstallation());
	}

	private Installation findOrCreateInstallation(Long museumId, Long installationId) {
		Installation installation;

		if (Objects.isNull(installationId)) {
			installation = new Installation();
		} else {
			installation = findInstallationById(museumId, installationId);
		}

		return installation;
	}

	private Installation findInstallationById(Long museumId, Long installationId) {
		Installation installation = installationDao.findById(installationId)
				.orElseThrow(() -> new NoSuchElementException("Installation with ID = " + installationId + " does not exist"));

		if (installation.getMuseum().getMuseumId() == museumId) {
			return installation;
		} else {
			throw new IllegalArgumentException(
					"Museum with ID = " + museumId + " does not have an installation with ID = " + installationId);
		}
	}

	@Transactional
	public List<MuseumsData> retrieveAllMuseums() {
		List<Museum> museums = museumDao.findAll();
		List<MuseumsData> results = new LinkedList<>();

		for (Museum museum : museums) {
			MuseumsData museumsData = new MuseumsData(museum);

			museumsData.getInstallations().clear();
			museumsData.getExhibitions().clear();

			results.add(museumsData);
		}

		return results;
	}

	public MuseumsData retrieveMuseumById(Long museumId) {
		Museum museum = findMuseumById(museumId);
		return new MuseumsData(museum);
	}

	public void deleteMuseumById(Long museumId) {
		Museum museum = findMuseumById(museumId);
		museumDao.delete(museum);
	}
}
