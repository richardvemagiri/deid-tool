package com.ecw.deidtool.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface DeIDConfigDAO extends JpaRepository<DeIDDBConfig, Integer> {

    //TODO: Implement DB caching
//    @Cacheable(cacheNames = "dbconfig", key="#xpath")
    public List<DeIDDBConfig> findAllByIsDeleted(int delflag);
}
