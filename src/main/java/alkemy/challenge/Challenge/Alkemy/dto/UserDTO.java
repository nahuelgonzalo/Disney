package alkemy.challenge.Challenge.Alkemy.dto;





import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import alkemy.challenge.Challenge.Alkemy.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class UserDTO {
	
	private String userName;
	
	private String password;
	
	private String name;
	
	private String lastName;
	
	private String email;
	
	public User buildEntity() {
		return User.builder()
				.userName(this.userName)
				.password(this.password)
				.name(this.name)
				.lastName(this.lastName)
				.email(this.email)
				.build();
	}
}
