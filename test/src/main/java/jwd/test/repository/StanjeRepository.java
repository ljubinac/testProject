package jwd.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.test.model.Stanje;

@Repository
public interface StanjeRepository extends JpaRepository<Stanje, Long>{

}
