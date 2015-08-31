package beehive_web.models.repository;

import org.springframework.data.repository.CrudRepository;

import beehive_web.models.entity.Restaurant;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long>{

}
