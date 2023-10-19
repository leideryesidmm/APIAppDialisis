package com.dialisis.dialisisperitoneal.persistence.repository;

import com.dialisis.dialisisperitoneal.persistence.entity.Eps;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpsRepository extends JpaRepository<Eps, Integer> {

    public List<Eps> findAll();
}
