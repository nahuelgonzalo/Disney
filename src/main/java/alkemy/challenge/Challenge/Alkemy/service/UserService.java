package alkemy.challenge.Challenge.Alkemy.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties.Credential;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import alkemy.challenge.Challenge.Alkemy.dto.UserDTO;
import alkemy.challenge.Challenge.Alkemy.model.User;
import alkemy.challenge.Challenge.Alkemy.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserService {
	
	@Autowired
	MailService mailService;
	
	@Autowired
	UserRepository userRepository;
	
	public Boolean createUser(UserDTO userDTO) {
		User userEntity = userDTO.buildEntity();
		userRepository.saveAndFlush(userEntity);
		return true;
	}
	
	public String logIn(Credential credential) throws Exception {
		User user = userRepository.findByUserName(credential.getUsername()).orElseThrow();
		if(user.getPassword().equals(credential.getPassword())) {
			String token = getJWTToken(credential.getUsername());
			return token;
		} else {
			throw new Exception();
		}
		
		
	}

	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
}
