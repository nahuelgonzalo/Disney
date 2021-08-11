package alkemy.challenge.Challenge.Alkemy.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties.Credential;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import alkemy.challenge.Challenge.Alkemy.dto.UserDTO;
import alkemy.challenge.Challenge.Alkemy.service.UserService;



@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/login")
	public String logIn(@RequestBody Credential credential) throws Exception {
		return userService.logIn(credential);
	}
	
	@PostMapping("/signIn")
	public ResponseEntity<UserDTO> signIn(@RequestBody UserDTO user){
		if(userService.createUser(user)) {
			user.setPassword(null);
			return ResponseEntity.status(HttpStatus.CREATED).body(user);
		}
		return ResponseEntity.status(400).body(user);
	}
	
}
