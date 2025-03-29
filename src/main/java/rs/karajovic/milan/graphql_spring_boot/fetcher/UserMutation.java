package rs.karajovic.milan.graphql_spring_boot.fetcher;

import org.springframework.beans.factory.annotation.Autowired;


import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;

import jakarta.validation.Valid;
import rs.karajovic.milan.graphql_spring_boot.domain.CreateUserInput;
import rs.karajovic.milan.graphql_spring_boot.domain.UpdateUserInput;
import rs.karajovic.milan.graphql_spring_boot.domain.User;
import rs.karajovic.milan.graphql_spring_boot.service.UserService;

/**
 * 
 * @author Milan Karajovic <milan.karajovic.rs@gmail.com>
 *
 */

@DgsComponent
public class UserMutation {
	
	@Autowired
	UserService userService;
	
	@DgsData(parentType = "Mutation", field = "createUser")
    public User createUser(@InputArgument("input") CreateUserInput userInput) {
		return userService.save(new User(null, userInput.getName(), userInput.getEmail()));
	}
	
	@DgsData(parentType = "Mutation", field = "updateUser")
    public User updateUser(@InputArgument("id") Integer id, @Valid @InputArgument("input") UpdateUserInput userInput) {
		return userService.updateUser(id, userInput.getName(), userInput.getEmail());
	}
	
	@DgsData(parentType = "Mutation", field = "deleteUser")
    public User deleteUser(@InputArgument("id") Integer id) {
		return userService.deleteUserById(id);
	}
	
}