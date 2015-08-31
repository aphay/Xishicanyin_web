package beehive_web.models.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import beehive_web.models.entity.Dishes;

public interface DishesRepository extends CrudRepository<Dishes, Long>{
    //取分类全量数据，按销量倒排
    List<Dishes> findByIdInOrderBySalesDesc(Collection<Long> ids);
    
    //取Top10销量的菜品
    List<Dishes> findTop10ByOrderBySalesDesc();
}
