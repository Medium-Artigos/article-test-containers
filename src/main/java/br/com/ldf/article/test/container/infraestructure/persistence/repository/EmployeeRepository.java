package br.com.ldf.article.test.container.infraestructure.persistence.repository;

import br.com.ldf.article.test.container.infraestructure.persistence.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
