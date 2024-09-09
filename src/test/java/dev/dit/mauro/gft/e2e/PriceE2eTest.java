package dev.dit.mauro.gft.e2e;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PriceE2eTest {

	private static final Integer BRAND_ID = 1;
	private static final Integer PRODUCT_ID = 35455;

	@LocalServerPort
	private int port;

	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = "http://localhost/";
	}

	@Test
	public void testE2eFirstCase() {
		final String testDate = "2020-06-14T10:00:00";
		final Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("date", testDate);
		queryParams.put("productId", PRODUCT_ID);

		final int expectedPriceId = 1;
		final Float expectedPriceValue = 35.50F;

		checkRequest(queryParams, expectedPriceId, expectedPriceValue);
	}

	@Test
	public void testE2eSecondCase() {
		final String testDate = "2020-06-14T16:00:00";
		final Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("date", testDate);
		queryParams.put("productId", PRODUCT_ID);

		final int expectedPriceId = 2;
		final Float expectedPriceValue = 25.45F;

		checkRequest(queryParams, expectedPriceId, expectedPriceValue);
	}

	@Test
	public void testE2eThirdCase() {
		final String testDate = "2020-06-14T21:00:00";
		final Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("date", testDate);
		queryParams.put("productId", PRODUCT_ID);

		final int expectedPriceId = 1;
		final Float expectedPriceValue = 35.50F;

		checkRequest(queryParams, expectedPriceId, expectedPriceValue);
	}

	@Test
	public void testE2eFourthCase() {
		final String testDate = "2020-06-15T10:00:00";
		final Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("date", testDate);
		queryParams.put("productId", PRODUCT_ID);

		final int expectedPriceId = 3;
		final Float expectedPriceValue = 30.50F;

		checkRequest(queryParams, expectedPriceId, expectedPriceValue);
	}

	@Test
	public void testE2eFifthCase() {
		final String testDate = "2020-06-16T21:00:00";
		final Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("date", testDate);
		queryParams.put("productId", PRODUCT_ID);

		final int expectedPriceId = 4;
		final Float expectedPriceValue = 38.95F;

		checkRequest(queryParams, expectedPriceId, expectedPriceValue);
	}

	private void checkRequest(final Map<String, Object> queryParams, final int expectedPriceId, final Float expectedPriceValue) {
		given()
				.port(port)
				.pathParam("brandId", BRAND_ID)
				.queryParams(queryParams)
				.log().all()
				.when()
				.get("price/{brandId}")
				.then()
				.statusCode(200)
				.body("priceId", equalTo(expectedPriceId))
				.body("priceValue.amount", equalTo(expectedPriceValue))
		;
	}

}
