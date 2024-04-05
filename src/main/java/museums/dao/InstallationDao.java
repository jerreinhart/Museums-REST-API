package museums.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import museums.entity.Installation;


public interface InstallationDao extends JpaRepository<Installation, Long>{

}
