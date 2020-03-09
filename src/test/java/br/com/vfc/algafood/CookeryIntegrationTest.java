package br.com.vfc.algafood;

import br.com.vfc.algafood.domain.model.Cookery;
import br.com.vfc.algafood.domain.service.CookeryService;
import br.com.vfc.algafood.util.DatabaseCleaner;
import br.com.vfc.algafood.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("it")
@TestPropertySource("/application-it.yml")
class CookeryIntegrationTest {

	private static Long NON_EXISTENT_COOKERY = 100L;

	@Autowired
	private Environment environment;

	@LocalServerPort
	private Integer serverPort;

	@Autowired
	private DatabaseCleaner databaseCleaner;

	@Autowired
	private CookeryService cookeryService;

	private Integer numCookeries;

	@BeforeEach
	void setUp() {
		RestAssured.basePath = "";
		RestAssured.port = this.serverPort;
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		Assumptions.assumeTrue(Arrays.asList(this.environment.getActiveProfiles()).contains("it"));

		databaseCleaner.clearTables();
		initData();
	}

	@Test
	void shouldReturnStatus200_WhenGetCookeries() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/cookeries")
		.then()
			.statusCode(HttpStatus.OK.value());
	}

	@Test
	void shouldReturnCookeries_WhenGetCookeries() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/cookeries")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("", hasSize(numCookeries))
			.body("name", hasItems("Indiana", "Tailandesa"));
	}

	@Test
	void shouldReturnBodyAndStatus200_WhenGetExistentCookery() {
		given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("id", 1L)
				.when()
				.get("/cookeries/{id}")
				.then()
				.statusCode(HttpStatus.OK.value())
				.body("name", equalTo("Indiana"));
	}

	@Test
	void shouldReturnStatus404_WhenGetNonExistentCookery() {
		given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("id", NON_EXISTENT_COOKERY)
				.when()
				.get("/cookeries/{id}")
				.then()
				.statusCode(HttpStatus.NOT_FOUND.value());
	}

	@Test
	void shouldReturnStatus201_WhenPostCookeries() {
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(ResourceUtils.getContentFromResource("/json/createCookery.json"))
		.when()
			.post("/cookeries")
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}

	@Test
	void shouldReturnStatus200_WhenPutExistentCookery() {
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("id", 2L)
			.body(ResourceUtils.getContentFromResource("/json/updateCookery.json"))
		.when()
			.put("/cookeries/{id}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("name", equalTo("Baiana"));
	}

	private void initData() {
		Cookery cookery1 = new Cookery();
		cookery1.setName("Indiana");
		cookeryService.save(cookery1);

		Cookery cookery2 = new Cookery();
		cookery2.setName("Tailandesa");
		cookeryService.save(cookery2);

		this.numCookeries = 2;
	}
}
