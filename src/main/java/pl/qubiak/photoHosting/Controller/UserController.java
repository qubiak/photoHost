package pl.qubiak.photoHosting.Controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.qubiak.photoHosting.Model.AppUser;
import pl.qubiak.photoHosting.Model.Token;
import pl.qubiak.photoHosting.Repo.AppUserRepo;
import pl.qubiak.photoHosting.Repo.TokenRepo;
import pl.qubiak.photoHosting.Service.UserService;


@Controller
public class UserController {

    private UserService userService;
    private AppUserRepo appUserRepo;
    private TokenRepo tokenRepo;

    public UserController(UserService userService, AppUserRepo appUserRepo, TokenRepo tokenRepo) {
        this.userService = userService;
        this.appUserRepo = appUserRepo;
        this.tokenRepo = tokenRepo;
    }


    @GetMapping("/sing-up")
    public String singup(Model model) {
        model.addAttribute("user", new AppUser());
        return "sing-up";
    }

    @PostMapping("/register")
    public String register(AppUser appUser) {
        userService.addUser(appUser);
        return "sing-up";
    }

    @GetMapping("/token")
    public String singup(@RequestParam String value) {
        Token byValue = tokenRepo.findByValue(value);
        AppUser appUser = byValue.getAppUser();
        appUser.setEnabled(true);
        appUserRepo.save(appUser);
        return "Good Job!!";
    }
}
