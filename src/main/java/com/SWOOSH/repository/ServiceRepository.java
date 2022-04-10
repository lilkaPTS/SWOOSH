package com.SWOOSH.repository;

import com.SWOOSH.model.CarWash;
import com.SWOOSH.model.Service;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ServiceRepository extends JpaRepository<Service, Long> {

    @Query("FROM Service s WHERE s.name IN :services")
    List<Service> findAllByName(@Param("services") List<String> services);

    List<Service> findAllByCarWash(CarWash carWash);
}
