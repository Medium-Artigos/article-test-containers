package br.com.ldf.article.test.container.infraestructure.persistence.mapper;

import br.com.ldf.article.test.container.domain.model.Employee;
import br.com.ldf.article.test.container.infraestructure.persistence.entity.EmployeeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee toModel(EmployeeEntity employeeEntity);

    EmployeeEntity toEntity(Employee employee);
}
