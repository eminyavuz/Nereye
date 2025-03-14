package com.emin.nereye.domain.advertisement.impl;

import com.emin.nereye.domain.advertisement.impl.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdRepository extends JpaRepository<Advertisement, Integer> {
}
