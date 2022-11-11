package cn.tuyucheng.taketoday.boot.converter.controller;

import cn.tuyucheng.taketoday.boot.domain.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/string-to-employee")
public class StringToEmployeeConverterController {

	@GetMapping
	public ResponseEntity<Object> getStringToEmployee(@RequestParam("employee") Employee employee) {
		return ResponseEntity.ok(employee);
	}
}