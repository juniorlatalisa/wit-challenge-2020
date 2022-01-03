package br.dev.juniorlatalisa.wit;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class WitChallenge2020RestApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	protected void operations(String operation, long a, long b, long expected) {
		assertThat(this.restTemplate.getForObject(
				String.format("http://localhost:%d/%s?a=%d&b=%d", port, operation, a, b), BigDecimal.class))
						.isEqualByComparingTo(BigDecimal.valueOf(expected));
	}

	@Test
	public void add() {
		operations("sum", 3, 7, 10);
	}

	@Test
	public void subtract() {
		operations("min", 11, 21, -10);
	}

	@Test
	public void divide() {
		operations("div", 15, 3, 5);
	}

	@Test
	public void times() {
		operations("mult", 3, 7, 21);
	}
}
