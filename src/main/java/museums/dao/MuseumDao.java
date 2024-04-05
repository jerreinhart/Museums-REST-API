package museums.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import museums.entity.Museum;

public interface MuseumDao extends JpaRepository<Museum, Long>{

}
