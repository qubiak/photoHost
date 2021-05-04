package pl.qubiak.photoHosting.Controller;



import com.vaadin.flow.component.UI;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.qubiak.photoHosting.Model.AppUser;
import pl.qubiak.photoHosting.Model.Token;
import pl.qubiak.photoHosting.Repo.AppUserRepo;
import pl.qubiak.photoHosting.Repo.TokenRepo;

@Controller
public class TokenController {

    private AppUserRepo appUserRepo;
    private TokenRepo tokenRepo;

    public TokenController(AppUserRepo appUserRepo, TokenRepo tokenRepo) {
        this.appUserRepo = appUserRepo;
        this.tokenRepo = tokenRepo;
    }

    @GetMapping("/token")
    public String singup(@RequestParam String value) {
        Token byValue = tokenRepo.findByValue(value);
        AppUser appUser = byValue.getAppUser();
        appUser.setEnabled(true);
        appUserRepo.save(appUser);
        return "login";
    }
}






