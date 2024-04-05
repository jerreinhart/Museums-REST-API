package museums.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import museums.entity.Exhibition;

public interface ExhibitionDao extends JpaRepository<Exhibition, Long>{
	
	Optional<Exhibition> findByNameOfExhibition(String nameOfExhibition);


}
