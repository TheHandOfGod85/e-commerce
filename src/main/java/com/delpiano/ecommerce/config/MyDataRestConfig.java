package com.delpiano.ecommerce.config;

import com.delpiano.ecommerce.entity.Country;
import com.delpiano.ecommerce.entity.Product;
import com.delpiano.ecommerce.entity.ProductCategory;
import com.delpiano.ecommerce.entity.State;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
  private EntityManager entityManager;
  @Autowired
  public MyDataRestConfig(EntityManager theEntityManager){
    entityManager = theEntityManager;
  }

  @Override
  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config,
      CorsRegistry cors) {
    HttpMethod[] theUnsupportedActions = {HttpMethod.PUT,HttpMethod.POST, HttpMethod.DELETE};
    disableHttpMethods(Product.class, config, theUnsupportedActions);
    disableHttpMethods(ProductCategory.class, config, theUnsupportedActions);
    disableHttpMethods(State.class, config, theUnsupportedActions);
    disableHttpMethods(Country.class, config, theUnsupportedActions);
    // call an internal helper method
    exposeIds(config);
  }
  // disable HTTP methods for a Class: PUT, POST, DELETE
  private static void disableHttpMethods(Class theClass, RepositoryRestConfiguration config,
      HttpMethod[] theUnsupportedActions) {
    config.getExposureConfiguration()
        .forDomainType(theClass)
        .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)))
        .withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(
            theUnsupportedActions)));
  }
  // expose entity ids
  private void exposeIds(RepositoryRestConfiguration config){
    //get a list of all entity classes from the entity manager
    Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
    // create an array of the entity types
    List<Class> entityClasses = new ArrayList<>();
    // get the entity types for the entities
    for (EntityType tempEntityType: entities){
      entityClasses.add(tempEntityType.getJavaType());
    }
    // expose the entity ids for the array of the entity/domain types
    Class[] domainTypes = entityClasses.toArray(new Class[0]);
    config.exposeIdsFor(domainTypes);
  }
}
