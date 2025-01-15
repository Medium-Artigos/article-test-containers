package br.com.ldf.article.test.container.application.mapper;

import br.com.ldf.article.test.container.application.dto.EmployeeRequest;
import br.com.ldf.article.test.container.domain.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeApplicationMapper {

    @Mapping(target = "id", ignore = true)
    Employee mapToModel(EmployeeRequest request);

}
