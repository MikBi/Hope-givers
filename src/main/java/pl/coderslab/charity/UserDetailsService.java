package pl.coderslab.charity;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService extends UserDetails {
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
}
