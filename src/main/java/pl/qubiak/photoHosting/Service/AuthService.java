package pl.qubiak.photoHosting.Service;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.qubiak.photoHosting.Model.AppUser;
import pl.qubiak.photoHosting.Repo.AppUserRepo;

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
        AppUser appUser = new AppUser(username, passwordEncoder().encode(password), "USER", email, false);
        userService.addUser(appUser);

    }

    public class AuthException extends Exception {

        public void authException(Exception e) {
            Notification.show("Something goes Wrong. Try again");
            UI.getCurrent().navigate("login");
            e.printStackTrace();
        }
    }

    public void authenticate(String username, String password) throws AuthException {
        AppUser appUser = appUserRepo.findByUsername(username);
        if (appUser != null && passwordEncoder().matches(password, appUser.getPassword())) {
            if (appUser.getRole().equals("ADMIN") && appUser.isEnabled()) {
                UI.getCurrent().navigate("upload");
            } else if (appUser.getRole().equals("USER") && appUser.isEnabled()) {
                UI.getCurrent().navigate("gallery");
            } else if (!appUser.isEnabled()) {
                Notification.show("Check your e-mail and confirm registration");
            }

        } else {
            throw new AuthException();
        }
    }

}
