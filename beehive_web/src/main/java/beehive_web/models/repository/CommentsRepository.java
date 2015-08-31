package beehive_web.models.repository;

import org.springframework.data.repository.CrudRepository;

import beehive_web.models.entity.Comments;

public interface CommentsRepository extends CrudRepository<Comments, Long>{

}
