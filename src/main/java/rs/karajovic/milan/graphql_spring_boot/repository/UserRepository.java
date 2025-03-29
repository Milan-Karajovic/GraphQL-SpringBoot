package rs.karajovic.milan.graphql_spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.karajovic.milan.graphql_spring_boot.domain.User;

/**
 * 
 * @author Milan Karajovic <milan.karajovic.rs@gmail.com>
 *
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
}
