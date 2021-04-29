package pl.qubiak.photoHosting.Service;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.qubiak.photoHosting.Model.AppUser;
import pl.qubiak.photoHosting.Repo.AppUserRepo;
import pl.qubiak.photoHosting.WebSecurityConfig;

@Service
public class AuthService {

    private final AppUserRepo appUserRepo;
    private final UserService userService;

    public AuthService(AppUserRepo appUserRepo, UserService userService) {
        this.appUserRepo = appUserRepo;
        this.userService = userService;
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void register(String username, String password, String email) {
        AppUser appUser = new AppUser(username, passwordEncoder().encode(password), email, "USER", false);
        userService.addUser(appUser);

    }

    public class AuthException extends Exception {
        //TODO
    }

    public void authenticate(String username, String password) throws AuthException {
        //System.out.println(username);
        //System.out.println(passwordEncoder().encode(password));
        AppUser appUser = appUserRepo.findByUsername(username);
        //System.out.println(appUser.getUsername() + "    DB");
        //System.out.println(appUser.getPassword() + "    DB");
        if (appUser != null && passwordEncoder().matches(password, appUser.getPassword())) {
            //czy tu powinienem kodować hasło?. Czy dopiero w AppUser.checkPassword?
            if (appUser.getRole().equals("ADMIN") && appUser.isEnabled() == true) {
                UI.getCurrent().navigate("upload");
            } else if (appUser.getRole().equals("USER") && appUser.isEnabled() == true) {
                UI.getCurrent().navigate("gallery");
            } else if (appUser.isEnabled() == false) {
                Notification.show("Check your e-mail and confirm registration");
            }

        } else {
            throw new AuthException();
        }
    }

}
