package com.delpiano.ecommerce.dao;

import com.delpiano.ecommerce.entity.State;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource
public interface StateRepository extends JpaRepository<State, Integer> {
  //will retrieve the state for a given id on http://localhost:8080/api/states/search/findByCountryCode?code=IN
  List<State> findByCountryCode(@Param("code") String code);

}
