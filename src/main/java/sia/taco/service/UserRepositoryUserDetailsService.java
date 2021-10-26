package sia.taco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import sia.taco.data.UserRepository;
import sia.taco.models.User;

@Slf4j
@Service
public class UserRepositoryUserDetailsService implements UserDetailsService  {

		@Autowired
	 private UserRepository userRepo;
	 @Autowired
	 public UserRepositoryUserDetailsService(UserRepository userRepo) {
	 this.userRepo = userRepo;
	 }
	 @Override
	 public UserDetails loadUserByUsername(String username)
	 throws UsernameNotFoundException {
		 System.out.print(username);
	 User user = userRepo.findByUsername(username);
	 log.info("okkkkkkkkkkkkkkkkkkkkkkkkkkk");
	 if (user != null) {
	 return user;
	 }
	 throw new UsernameNotFoundException(
	 "User '" + username + "' not found");
	 }
}
