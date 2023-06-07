package com.unicom.security.auth;

import com.unicom.security.config.JwtService;
import com.unicom.security.models.RequestStatus;
import com.unicom.security.token.Token;
import com.unicom.security.token.TokenRepository;
import com.unicom.security.token.TokenType;
import com.unicom.security.user.Role;
import com.unicom.security.user.User;
import com.unicom.security.user.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    var user = User.builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.MANAGER)
        .status(RequestStatus.PENDING)
        
        .build();
    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    saveUserToken(savedUser, jwtToken);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {

	  try {
		  authenticationManager.authenticate(
			        new UsernamePasswordAuthenticationToken(
			            request.getEmail(),
			            request.getPassword()
			        )
			    );
	  } catch(Exception e) {
		  System.out.println(e.getMessage());
	  }
    
    System.out.println(request.getEmail() + " "+ request.getPassword());

    var user = repository.findByEmailAndStatus(request.getEmail(),RequestStatus.APPROVED)
        .orElseThrow();
  
    Map<String,Object> claims=new HashMap<String,Object>();
    claims.put("role", user.getRole());
    claims.put("username", user.getEmail());

    var jwtToken = jwtService.generateToken(claims, user);
    
    
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }
  public User getAuthenticatedUser() {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if (authentication != null && authentication.getPrincipal() instanceof User) {
          return (User) authentication.getPrincipal();
      }
      return null; 
  }
}
