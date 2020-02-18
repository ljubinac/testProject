package jwd.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.test.model.Sprint;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long>{

}
