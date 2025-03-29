package rs.karajovic.milan.graphql_spring_boot.fetcher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.exceptions.QueryException;

import rs.karajovic.milan.graphql_spring_boot.common.AppConstants;
import rs.karajovic.milan.graphql_spring_boot.domain.User;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class UserMutationTest {

	@Autowired
    DgsQueryExecutor executor;
	
	@Test
	@Order(1)
	public void test_createUser_success() {
		
		// given
		String query = "mutation { createUser(input: { email: \"user.test@gmail.com\", name: \"User Test\" }) { name email } }";
		
		// when
		User user = executor
                .executeAndExtractJsonPathAsObject(query, "data.createUser", User.class);
		
		// then
		assertNotNull(user);
		assertEquals("User Test", user.getName());
		assertEquals("user.test@gmail.com", user.getEmail());
		
	}
	
	@Test
	@Order(2)
	public void test_updateUser_success() {
		
		// given
		String query = "mutation { updateUser(input: { email: \"mk@gmail.com\", name: \"MK\" }, id: \"1\") { email name} }";
		
		// when
		User user = executor
                .executeAndExtractJsonPathAsObject(query, "data.updateUser", User.class);
		
		// then
		assertNotNull(user);
		assertEquals("MK", user.getName());
		assertEquals("mk@gmail.com", user.getEmail());
		
	}
	
	@Test
	@Order(3)
	public void test_updateUser_badId() {
		
		// given
		String query = "mutation { updateUser(input: { email: \"mk@gmail.com\", name: \"MK\" }, id: \"100\") { email name} }";
		
		// when
		QueryException exception = assertThrows(QueryException.class, () -> {
				executor
                .executeAndExtractJsonPathAsObject(query, "data.updateUser", User.class);
		});
		
		// then
		assertEquals(AppConstants.USER_CAN_NOT_BE_UPDATED_BY_ID + 100, exception.getMessage());
		
	}
	
	@Test
	public void test_deleteUser_success() {
		
		// given
		String query = "mutation { deleteUser(id: \"1\") { email name} }";
		
		// when
		User user = executor
                .executeAndExtractJsonPathAsObject(query, "data.deleteUser", User.class);
		
		// then
		assertNotNull(user);
		assertNotNull(user.getName());
		assertNotNull(user.getEmail());
		
	}
	
	@Test
	public void test_deleteUser_badId() {
		
		// given
		String query = "mutation { deleteUser(id: \"100\") { email name} }";
		
		// when
		QueryException exception = assertThrows(QueryException.class, () -> {
				executor
                .executeAndExtractJsonPathAsObject(query, "data.deleteUser", User.class);
		});
		
		// then
		assertEquals(AppConstants.USER_CAN_NOT_BE_DELETED_BY_ID + 100, exception.getMessage());
		
	}
	
}
