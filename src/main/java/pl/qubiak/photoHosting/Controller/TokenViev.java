package pl.qubiak.photoHosting.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.qubiak.photoHosting.Model.AppUser;
import pl.qubiak.photoHosting.Model.Token;
import pl.qubiak.photoHosting.Repo.AppUserRepo;
import pl.qubiak.photoHosting.Repo.TokenRepo;

@Controller
public class TokenViev {

    private AppUserRepo appUserRepo;
    private TokenRepo tokenRepo;

    public TokenViev(AppUserRepo appUserRepo, TokenRepo tokenRepo) {
        this.appUserRepo = appUserRepo;
        this.tokenRepo = tokenRepo;
    }

    @GetMapping("/token")
    public String singup(String value) {
        Token byValue = tokenRepo.findByValue(value);
        System.out.println(byValue.getAppUser());
        AppUser appUser = byValue.getAppUser();
        appUser.setEnabled(true);
        appUserRepo.save(appUser);
        return "email confirmed, now pleas login";
    }
}




