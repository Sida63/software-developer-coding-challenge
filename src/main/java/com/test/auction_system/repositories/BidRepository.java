package com.test.auction_system.repositories;

import com.test.auction_system.model.Bid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface BidRepository extends JpaRepository<Bid,Long> {
    @Query("select b from Bid b WHERE b.carid = ?1")
    List<Bid> findwinner(Long carid, Pageable pageable);
}
