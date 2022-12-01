package cn.tuyucheng.taketoday.mockito.objectmapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flower {

	private String name;
	private Integer petals;
}