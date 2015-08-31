package beehive_web.models.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import beehive_web.models.entity.Voucher;

public interface VoucherRepository extends CrudRepository<Voucher, Long>{
    List<Voucher> findByUserId(long userId);
}
