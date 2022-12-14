package cn.tuyucheng.taketoday.tags;

import cn.tuyucheng.taketoday.junit.tags.example.Employee;
import cn.tuyucheng.taketoday.junit.tags.example.EmployeeDAO;
import cn.tuyucheng.taketoday.junit.tags.example.SpringJdbcConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringJdbcConfig.class}, loader = AnnotationConfigContextLoader.class)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class EmployeeDAOIntegrationTest {

	@Autowired
	private EmployeeDAO employeeDao;

	@Mock
	private JdbcTemplate jdbcTemplate;
	private EmployeeDAO employeeDAO;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		employeeDAO = new EmployeeDAO();
		employeeDAO.setJdbcTemplate(jdbcTemplate);
	}

	@Test
	@Tag("IntegrationTest")
	void testAddEmployeeUsingSimpleJdbcInsert() {
		final Employee emp = new Employee();
		emp.setId(12);
		emp.setFirstName("testFirstName");
		emp.setLastName("testLastName");
		emp.setAddress("testAddress");

		assertEquals(employeeDao.addEmployeeUsingSimpleJdbcInsert(emp), 1);
	}

	@Test
	@Tag("UnitTest")
	void givenNumberOfEmployeeWhenCountEmployeeThenCountMatch() {
		// given
		Mockito.when(jdbcTemplate.queryForObject(Mockito.any(String.class), Mockito.eq(Integer.class))).thenReturn(1);

		// when
		int countOfEmployees = employeeDAO.getCountOfEmployees();

		// then
		assertEquals(1, countOfEmployees);
	}
}