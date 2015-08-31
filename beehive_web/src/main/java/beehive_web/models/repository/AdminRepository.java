package beehive_web.models.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import beehive_web.models.entity.Admin;

public interface AdminRepository extends CrudRepository<Admin, Long>{
    public Admin findByLoginName(String loginName);
    public List<Admin> findByRestaurantId(long restaurantId);
}
