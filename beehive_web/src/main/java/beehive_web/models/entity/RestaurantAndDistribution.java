package beehive_web.models.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RestaurantAndDistribution extends BaseEntity{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private long restaurantId;
    private long distributionId;
    
    protected RestaurantAndDistribution() {}
    
    public RestaurantAndDistribution(long restaurantId, long distributionId) {
        this.restaurantId = restaurantId;
        this.distributionId = distributionId;
    }
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getRestaurantId() {
        return restaurantId;
    }
    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }
    public long getDistributionId() {
        return distributionId;
    }
    public void setDistributionId(long distributionId) {
        this.distributionId = distributionId;
    }
}
