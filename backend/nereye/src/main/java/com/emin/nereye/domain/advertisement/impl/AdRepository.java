package com.emin.nereye.domain.advertisement.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdRepository extends JpaRepository<Advertisement, Integer> {
    List<Advertisement> findByOwner_Id(int ownerId);

    List<Advertisement> findByTenet_Id(int tenetId);

}
