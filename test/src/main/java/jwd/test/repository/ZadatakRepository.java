package jwd.test.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.test.model.Zadatak;

@Repository
public interface ZadatakRepository extends JpaRepository<Zadatak, Long>{

	
	@Query("SELECT z FROM Zadatak z WHERE "
			+ "(:ime IS NULL OR z.ime like :ime) AND "
			+ "(:sprintId IS NULL OR z.sprint.id = :sprintId)")
	Page<Zadatak>search(
			@Param("ime") String ime, 
			@Param("sprintId") Long sprintId,
			Pageable pageRequest
			);
}
