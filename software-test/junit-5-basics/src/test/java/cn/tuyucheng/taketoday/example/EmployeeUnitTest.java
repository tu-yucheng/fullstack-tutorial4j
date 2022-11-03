package cn.tuyucheng.taketoday.example;

import cn.tuyucheng.taketoday.junit.tags.example.EmployeeDAO;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.hamcrest.MatcherAssert.assertThat;

public class EmployeeUnitTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    private EmployeeDAO employeeDAO;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        employeeDAO = new EmployeeDAO();
        employeeDAO.setJdbcTemplate(jdbcTemplate);
    }

    @Test
    public void givenNumberOfEmployeeWhenCountEmployeeThenCountMatch() {
        // given
        Mockito.when(jdbcTemplate.queryForObject(Mockito.any(String.class), Mockito.eq(Integer.class))).thenReturn(1);

        // when
        int countOfEmployees = employeeDAO.getCountOfEmployees();

        // then
        assertThat(countOfEmployees, CoreMatchers.is(1));
    }
}