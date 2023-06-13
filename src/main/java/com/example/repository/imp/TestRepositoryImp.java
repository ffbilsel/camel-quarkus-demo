package com.example.repository.imp;


import com.example.entity.BaseEntity;
import com.example.repository.TestRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class TestRepositoryImp implements TestRepository {
    @Override
    public Optional<BaseEntity> findByInfo(String info) {
        return find("info", info).firstResultOptional();

    }

}
