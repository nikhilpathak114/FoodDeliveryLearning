package com.learning.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.dto.EROLE;
import com.learning.dto.Role;
import com.learning.dto.Register;
import com.learning.payload.request.LoginRequest;
import com.learning.payload.request.SignupRequest;
import com.learning.payload.response.JwtResponse;
import com.learning.payload.response.MessageResponse;
import com.learning.repository.RoleRepo;
import com.learning.repository.UserRepository;
import com.learning.security.jwt.JwtUtils;
import com.learning.security.services.UserDetailsImpl;
import com.learning.service.RegisterService;

import org.springframework.security.core.Authentication;
@RestController // 4 @ResponseBody @Controller
//REST API: RESPONSE wherever we have to share the response that method 
//must be marked with @ResponseBody
//100methods ---> @Responsebody ---> 1000times
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class UserController {

	@Autowired
	RegisterService uservService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepo roleRepo;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), 
						loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateToken(authentication);
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
		
		List<String> roles = userDetailsImpl.getAuthorities()
				.stream()
				.map(i->i.getAuthority())
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(new JwtResponse(jwt, 
				userDetailsImpl.getId(), 
				userDetailsImpl.getUsername(), 
				userDetailsImpl.getEmail(), 
				roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {

		if (userRepository.existsByUsername(signupRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signupRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// user's account

		Register user = new Register(signupRequest.getUsername(), signupRequest.getEmail(),
				passwordEncoder.encode(signupRequest.getPassword()), signupRequest.getName(),signupRequest.getAddress());
		// retrieving the roles details

		Set<String> strRoles = signupRequest.getRole();

		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepo.findByRoleName(EROLE.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error:role not found"));
			roles.add(userRole);
		}

		else {
			strRoles.forEach(e -> {
				switch (e) {
				case "admin":
					Role roleAdmin = roleRepo.findByRoleName(EROLE.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error:role not found"));
					roles.add(roleAdmin);
					break;

				default:
					Role userRole = roleRepo.findByRoleName(EROLE.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error:role not found"));
					roles.add(userRole);
				}
			});

		}
		user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity.status(201).body(new MessageResponse("user created successfully"));

	}
	// adding user info into the table
	// info will be shared by the client in terms of JSON object
	// do we need to read that json Object? ===> yes
	// where is this json object is available in request? ==> requestbody
	// do we need to read that requestbody content? yes
	// do we need to transform json object object to java object? yes ==>
	// @RequestBody

//	@PostMapping("/addUser")
//	public ResponseEntity<?> addUser(@Valid @RequestBody User register) throws SQLException, AlreadyExistsException {
//		User result = uservService.addUser(register);
//		// validation
//		return ResponseEntity.status(201).body(result);
//	}
//
//	@GetMapping("/{id}")
//	public ResponseEntity<?> getUserById(@Valid @PathVariable("id") Long id) throws IdNotFoundException {
//		Optional<User> register = uservService.getUserById(id);
//		return ResponseEntity.ok(register);
//	}
//
//	@GetMapping("/all")
//	public ResponseEntity<?> getAllUsersDetails() {
//		Optional<List<User>> optional = uservService.getAllUserDetails();
//		if (optional.isEmpty()) {
//			Map<String, String> map = new HashMap<>();
//			map.put("message", "no record found");
//			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
//		}
//		return ResponseEntity.ok(optional.get());
//	}
}
