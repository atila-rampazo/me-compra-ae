package br.com.zensolutions.MeCompraAe.integrations.controllers;

import br.com.zensolutions.MeCompraAe.configs.AbstractIntegrationTest;
import br.com.zensolutions.MeCompraAe.configs.TestConfigs;
import br.com.zensolutions.MeCompraAe.domains.entities.categories.Category;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class CategoryControllerTest extends AbstractIntegrationTest {
    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;
    private static Category category;

    @BeforeAll
    public static void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        category = new Category();
    }

    @Test
    @Order(1)
    public void testCreate() throws JsonProcessingException {
        mockCategory();
        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_LOCAL)
                .setBasePath("/api/v1/category")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();
        var content = given()
                .spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(category)
                .when()
                .post()
                .then()
                .statusCode(200).extract().body().asString();

        Category categoryCreated = objectMapper.readValue(content, Category.class);
        category = categoryCreated;
        assertTrue(categoryCreated.getId() > 0);
        Assertions.assertNotNull(categoryCreated);
        Assertions.assertNotNull(categoryCreated.getId());
    }

    @Test
    @Order(2)
    public void testFindByID() throws JsonProcessingException {
        mockCategory();
        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_LOCAL)
                .setBasePath("/api/v1/category")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();
        var content = given()
                .spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .pathParam("id", category.getId())
                .when()
                .get("{id}")
                .then()
                .statusCode(200).extract().body().asString();

        Category categoryCreated = objectMapper.readValue(content, Category.class);
        assertTrue(categoryCreated.getId() > 0);
        Assertions.assertNotNull(categoryCreated);
        Assertions.assertNotNull(categoryCreated.getId());

        Assertions.assertEquals("Higiene", categoryCreated.getName());
    }
    @Test
    @Order(3)
    public void testUpdate() throws JsonProcessingException {
        mockCategory();
        category.setName("Higiene e Banho");
        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_LOCAL)
                .setBasePath("/api/v1/category")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();
        var content = given()
                .spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .pathParam("id", category.getId())
                .body(category)
                .when()
                .put("{id}")
                .then()
                .statusCode(200).extract().body().asString();

        Category categoryCreated = objectMapper.readValue(content, Category.class);
        assertTrue(categoryCreated.getId() > 0);
        Assertions.assertNotNull(categoryCreated);
        Assertions.assertNotNull(categoryCreated.getId());
        Assertions.assertEquals("Higiene e Banho",categoryCreated.getName());
    }
    private void mockCategory() {
        category.setName("Higiene");
        category.setStatus(true);
    }
}
