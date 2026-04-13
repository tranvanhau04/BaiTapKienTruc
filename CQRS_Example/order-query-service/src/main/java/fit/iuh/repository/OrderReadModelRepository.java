package fit.iuh.repository;

import fit.iuh.readmodel.OrderReadModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderReadModelRepository extends JpaRepository<OrderReadModel, Long> {
}

