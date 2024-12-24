package com.emin.nereye.repository;

import com.emin.nereye.entity.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdRepository extends JpaRepository<Advertisement,Integer> {
}
