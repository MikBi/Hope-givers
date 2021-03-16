package pl.coderslab.charity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/person/**").hasRole("USER_PERSON")
                .antMatchers("/secretCave/**").hasRole("ADMIN_MEN")
                .and().formLogin().usernameParameter("email").loginPage("/login").successHandler(myAuthenticationSuccessHandler())
                .and().logout().logoutSuccessUrl("/logout")
                .and().exceptionHandling().accessDeniedPage("/403");
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringDataUserDetailsService customUserDetailsService() {
        return new SpringDataUserDetailsService();
    }
    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new MySimpleUrlAuthenticationSuccessHandler();
    }
}
