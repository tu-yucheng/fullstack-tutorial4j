package cn.tuyucheng.taketoday.restassured.controller;

import cn.tuyucheng.taketoday.restassured.model.Movie;
import cn.tuyucheng.taketoday.restassured.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Set;
import java.util.UUID;

@RestController
public class AppController {

	@Autowired
	AppService appService;

	@GetMapping("/movies")
	public ResponseEntity<?> getMovies() {
		Set<Movie> result = appService.getAll();

		return ResponseEntity.ok()
				.body(result);
	}

	@PostMapping("/movie")
	@ResponseStatus(HttpStatus.CREATED)
	public Movie addMovie(@RequestBody Movie movie) {
		appService.add(movie);
		return movie;
	}

	@GetMapping("/movie/{id}")
	public ResponseEntity<?> getMovie(@PathVariable int id) {
		Movie movie = appService.findMovie(id);
		if (movie == null) {
			return ResponseEntity.badRequest()
					.body("Invalid movie id");
		}

		return ResponseEntity.ok(movie);
	}

	@GetMapping("/welcome")
	public ResponseEntity<?> welcome(HttpServletResponse response) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		headers.add("sessionId", UUID.randomUUID()
				.toString());

		Cookie cookie = new Cookie("token", "some-token");
		cookie.setDomain("localhost");

		response.addCookie(cookie);

		return ResponseEntity.noContent()
				.headers(headers)
				.build();
	}

	@GetMapping("/download/{id}")
	public ResponseEntity<Resource> getFile(@PathVariable int id) throws IOException {
		File file = appService.getFile(id);

		if (file == null) {
			return ResponseEntity.notFound().build();
		}

		InputStreamResource resource = new InputStreamResource(Files.newInputStream(file.toPath()));

		return ResponseEntity.ok()
				.contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.body(resource);
	}
}