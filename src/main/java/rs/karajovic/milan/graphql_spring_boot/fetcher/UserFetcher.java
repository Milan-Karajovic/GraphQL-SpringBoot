package rs.karajovic.milan.graphql_spring_boot.fetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;

import rs.karajovic.milan.graphql_spring_boot.domain.User;
import rs.karajovic.milan.graphql_spring_boot.service.UserService;

/**
 * 
 * @author Milan Karajovic <milan.karajovic.rs@gmail.com>
 *
 */

@DgsComponent
public class UserFetcher {
	
	@Autowired
	UserService userService;
	
	
	@DgsData(parentType = "Query", field = "users")
    public List<User> findAllUsers() {
        return userService.findAll();
    }
	
	@DgsData(parentType = "Query", field = "user")
    public User findUserById(@InputArgument("id") Integer id) {
		return userService.findUserById(id);
    }
	
}