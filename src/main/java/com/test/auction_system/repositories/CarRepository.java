package com.test.auction_system.repositories;

import com.test.auction_system.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarRepository extends JpaRepository<Car,Long> {
    Page<Car> findAll(Pageable pageable);
}
