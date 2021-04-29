package pl.qubiak.photoHosting.Service;

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
        AppUser appUser = new AppUser(username, passwordEncoder().encode(password), email, "USER", false);
        userService.addUser(appUser);

    }

    public class AuthException extends Exception {
    }



    public void authenticate(String username, String password) throws AuthException {
        AppUser appUser = appUserRepo.findByUsername(username);
        if (appUser != null && appUser.checkPassword(passwordEncoder().encode(password))) {
            //czy tu powinienem kodować hasło.
            if (appUser.getRole().equals("ADMIN")) {
                //TODO move to ADMIN viev
            } else {
                //TODO move to USER viev
            }

        } else {
            throw new AuthException();
        }
    }

}
