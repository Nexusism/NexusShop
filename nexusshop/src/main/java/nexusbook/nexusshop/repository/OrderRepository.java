package nexusbook.nexusshop.repository;

import lombok.RequiredArgsConstructor;
import nexusbook.nexusshop.domain.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long id){
        return em.find(Order.class, id);
    }

//      public List<Order> findAll(OrderSearch orderSearch) { ... }
}
