package cn.tuyucheng.taketoday.app.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Flower {

	private String name;
	private Integer petals;
}