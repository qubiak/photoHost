package pl.qubiak.photoHosting.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.qubiak.photoHosting.Model.AppUser;
import pl.qubiak.photoHosting.Repo.AppUserRepo;
import pl.qubiak.photoHosting.Service.UserDetailsServiceImpl;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;
    private AppUserRepo appUserRepo;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, AppUserRepo appUserRepo) {
        this.userDetailsService = userDetailsService;
        this.appUserRepo = appUserRepo;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/test1").hasRole("USER")
                .antMatchers("/test2").hasRole("ADMIN")
                .antMatchers("/upload").hasRole("ADMIN")
                .antMatchers("/gallery").hasRole("USER")
                .antMatchers("/login").permitAll()
                .and()
                .csrf().disable();
    }


    @EventListener(ApplicationReadyEvent.class)
    public void get() {
        AppUser ADMIN = new AppUser("ADMIN", passwordEncoder().encode("ADMIN"), "ADMIN", "ADMIN@ADMIN.pl", true );
        AppUser appUserUser = new AppUser("UserJan", passwordEncoder().encode("UserJan"), "USER", "user@test.pl", true);
        AppUser appUserUser2 = new AppUser("UserMarian", passwordEncoder().encode("UserMarian"), "USER", "user@test.pl", false);
        AppUser appUserAdmin = new AppUser("AdminAngelika", passwordEncoder().encode("AdminAngelika"), "ADMIN", "admin@test.pl", true);
        AppUser appUserAdmin2 = new AppUser("AdminMariola", passwordEncoder().encode("AdminMariola"), "ADMIN", "admin@test.pl", false);
        appUserRepo.save(ADMIN);
        appUserRepo.save(appUserUser);
        appUserRepo.save(appUserUser2);
        appUserRepo.save(appUserAdmin);
        appUserRepo.save(appUserAdmin2);
    }
}
