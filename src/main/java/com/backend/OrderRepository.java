package com.backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderTicket, Long> {
    Optional<OrderTicket> findByPhoneNumber(String phoneNumber);
}