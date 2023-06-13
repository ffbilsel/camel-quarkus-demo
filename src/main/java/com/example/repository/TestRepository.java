package com.example.repository;


import com.example.entity.BaseEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.Optional;

public interface TestRepository extends PanacheRepository<BaseEntity> {

    Optional<BaseEntity> findByInfo(String info);

}
