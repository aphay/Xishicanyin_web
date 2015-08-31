package beehive_web.models.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import beehive_web.models.entity.RestaurantAndDistribution;

public interface RestaurantAndDistributionRepository extends CrudRepository<RestaurantAndDistribution, Long>{
    public List<RestaurantAndDistribution> findByRestaurantId(long restaurantId);
    public List<RestaurantAndDistribution> findByDistributionId(long distributionId);
    public RestaurantAndDistribution findByRestaurantIdAndDistributionId(long restaurantId, long distributionId);
    
    public void deleteByRestaurantId(long restaurantId);
    public void deleteByDistributionId(long distributionId);
    public void deleteByRestaurantIdAndDistributionId(long restaurantId, long distributionId);
}
