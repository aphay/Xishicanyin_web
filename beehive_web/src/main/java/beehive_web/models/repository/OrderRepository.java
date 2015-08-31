package beehive_web.models.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import beehive_web.models.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{
    public List<Order> findByUserIdOrderByCreateTimeDesc(long userId);
    public List<Order> findByRestaurantIdAndStatusAndHasPaidAndUpdateTimeGreaterThanOrderByUpdateTimeDesc(long restaurantId, int status, boolean hasPaid, String time);
    public List<Order> findByStatusAndHasPaidAndUpdateTimeGreaterThanOrderByUpdateTimeDesc(int status, boolean hasPaid, String time);
    public List<Order> findByStatusAndUpdateTimeBetween(int status, String startTime, String endTime);
    
    public List<Order> findByRestaurantIdAndHasPaidAndUpdateTimeGreaterThanOrderByUpdateTimeDesc(long restaurantId, boolean hasPaid, String time);
    public List<Order> findByHasPaidAndUpdateTimeGreaterThanOrderByUpdateTimeDesc(boolean hasPaid, String time);
    //管理页面首页显示数据
    public int countByCreateTimeGreaterThan(String time);
    public int countByStatusAndCreateTimeGreaterThan(int status, String time);
    public List<Order> findByStatusAndHasPaidAndCreateTimeGreaterThan(int status, boolean hasPaid, String time);
    
    public List<Order> findByCreateTimeBetween(String today, String tomorrow);
    public List<Order> findByTelAndCreateTimeBetween(String tel, String today, String tomorrow);
}
