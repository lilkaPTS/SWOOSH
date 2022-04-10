package com.SWOOSH.repository;

import com.SWOOSH.model.CarWash;
import com.SWOOSH.model.Employee;
import com.SWOOSH.model.Order;
import com.SWOOSH.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "select count(*) from orders where employee_id = :employee AND orders.car_wash_id = :carWash AND date >= :start AND date <= :end", nativeQuery = true)
    Integer countOrders(@Param("employee") Employee employee, @Param("carWash") CarWash carWash, @Param("start") Date start,@Param("end") Date end);

    @Query(value = "select * from orders o where o.employee_id = :employee AND o.car_wash_id = :carWash AND o.date >= :start AND o.date <= :end", nativeQuery = true)
    List<Order> getOrderForReturnAbility(@Param("employee") Employee employee, @Param("carWash") CarWash carWash, @Param("start") Date start, @Param("end") Date end);

    List<Order> getOrdersByCarWash(CarWash carWash);

    Integer countOrderByCarWashAndUser(CarWash carWash, User user);

    List<Order> getOrdersByUser(User user);
}
