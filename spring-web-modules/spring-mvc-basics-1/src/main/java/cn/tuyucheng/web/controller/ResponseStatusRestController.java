package cn.tuyucheng.web.controller;

import cn.tuyucheng.taketoday.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
public class ResponseStatusRestController {

	@GetMapping("/teapot")
	@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
	public void teaPot() {
	}

	@GetMapping("empty")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void emptyResponse() {
	}

	@GetMapping("empty-no-responsestatus")
	public void emptyResponseWithoutResponseStatus() {
	}

	@PostMapping("create")
	@ResponseStatus(HttpStatus.CREATED)
	public Book createEntity() {
		// here we would create and persist an entity
		int randomInt = ThreadLocalRandom.current().nextInt(1, 100);
		return new Book(randomInt, "author" + randomInt, "title" + randomInt);
	}

	@PostMapping("create-no-responsestatus")
	public Book createEntityWithoutResponseStatus() {
		// here we would create and persist an entity
		int randomInt = ThreadLocalRandom.current().nextInt(1, 100);
		return new Book(randomInt, "author" + randomInt, "title" + randomInt);
	}
}