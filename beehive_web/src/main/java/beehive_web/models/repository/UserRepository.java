package beehive_web.models.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import beehive_web.models.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{
    User findByOpenId(String openId);
    List<User> findByTel(String tel);
}
