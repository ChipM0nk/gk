package com.personal.exam.gk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.exam.gk.entity.Orders_AK;

@Repository
public interface OrderRepository extends JpaRepository<Orders_AK, Integer> {

}
