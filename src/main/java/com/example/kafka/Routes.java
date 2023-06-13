package com.example.kafka;

import com.example.dto.BaseDTO;
import com.example.service.imp.TestServiceImp;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.opentelemetry.api.trace.Span;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.format.DateTimeFormatter;
import java.util.UUID;

@ApplicationScoped
public class Routes extends RouteBuilder {

    private static final DateTimeFormatter ISO_INSTANT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private static final Logger log = LoggerFactory.getLogger(Routes.class);

    @Inject
    ObjectMapper mapper;
    @Inject
    TestServiceImp testService;

    @Inject
    CamelContext camelContext;


    @Override
    public void configure() {

        camelContext.setUseMDCLogging(true);

        from("kafka:test").routeId("kafka")
                .log("Received : \"${body}\"")
                .process(exchange -> {
                    BaseDTO message = mapper.readValue(exchange.getIn().getBody(String.class), new TypeReference<>() {
                    });
                    String out = testService.process(message);
                    exchange.getMessage().setBody(out);
                    log.info(getTraceId());
                }).log("Send : \"${body}\"");
    }


    private void setHeader(Exchange exchange, String prefix, String type) {
        String ceId = prefix + "-" + UUID.randomUUID();
        exchange.getMessage().setMessageId(ceId);

    }

    private void setDirectHeader(Exchange exchange, String prefix, String type, String keyDescription, String source) {
        String ceId = prefix + "-" + UUID.randomUUID();
       exchange.getMessage().setMessageId(ceId);

    }
    private String getTraceId() {
        return Span.current().getSpanContext().getTraceId();
    }

}
