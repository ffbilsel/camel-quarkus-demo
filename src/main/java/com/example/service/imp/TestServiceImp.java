package com.example.service.imp;

import com.example.dto.BaseDTO;
import com.example.entity.BaseEntity;
import com.example.repository.imp.TestRepositoryImp;
import com.example.service.TestService;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@ApplicationScoped
public class TestServiceImp implements TestService {

    private static final Logger log = LoggerFactory.getLogger(TestServiceImp.class);

    @Inject
    TestRepositoryImp repository;


    @Override
    @Transactional
//    @WithSpan
    public String process(BaseDTO dto) {
        log.info("Processing... : {}", dto);

        Optional<BaseEntity> optional = repository.findByInfo(dto.getInfo());
        return "a";
    }

}
