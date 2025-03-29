package rs.karajovic.milan.graphql_spring_boot.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Milan Karajovic <milan.karajovic.rs@gmail.com>
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserInput {
	
	@NotBlank(message = "Name is mandatory")
	@Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
	private String name;
	
	@NotBlank(message = "Email is mandatory")
    @Email(message = "Please provide a valid email address")
	private String email;
	
}
