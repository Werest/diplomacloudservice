package ru.werest.diplomacloudservice.integration;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.werest.diplomacloudservice.request.LoginRequest;

@Disabled
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@Slf4j
public class IntegrationTest {
    String HOST = "http://localhost:";
    int PORT = 5500;

    @Autowired
    TestRestTemplate restTemplate;

    @Container
    GenericContainer<?> transfer_service = new GenericContainer<>("backend")
            .withExposedPorts(8080);


    @Test
    void Positive() {
        Integer transferServicePort = transfer_service.getMappedPort(8080);

        LoginRequest request = new LoginRequest();
        request.setLogin("koko");
        request.setPassword("koko");

        ResponseEntity<String> response = restTemplate.postForEntity(
                HOST + transferServicePort + "/login",
                request,
                String.class
        );
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        log.info(response.getBody());
    }

//        ConfirmOperationRequest confirmOperationRequest = new ConfirmOperationRequest();
//        confirmOperationRequest.setOperationId("1");
//        confirmOperationRequest.setCode("0000");
//
//        response = restTemplate.postForEntity(
//                HOST + transferServicePort + "/confirmOperation",
//                confirmOperationRequest,
//                String.class
//        );
//        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
}
