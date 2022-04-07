package com.SWOOSH.repository;

import com.SWOOSH.model.CarWash;
import com.SWOOSH.model.Employee;
import com.SWOOSH.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "select count(*) from orders where employee_id = :employee AND orders.car_wash_id = :carWash AND date >= :start AND date <= :end", nativeQuery = true)
    Integer countOrders(@Param("employee") Employee employee, @Param("carWash") CarWash carWash, @Param("start") Date start,@Param("end") Date end);
}
