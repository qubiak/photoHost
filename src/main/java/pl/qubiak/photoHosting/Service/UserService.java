package pl.qubiak.photoHosting.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.qubiak.photoHosting.Model.AppUser;
import pl.qubiak.photoHosting.Model.Token;
import pl.qubiak.photoHosting.Repo.AppUserRepo;
import pl.qubiak.photoHosting.Repo.TokenRepo;

import javax.mail.MessagingException;
import java.util.UUID;

@Service
public class UserService {

    private TokenRepo tokenRepo;
    private MailService mailService;
    private AppUserRepo appUserRepo;
    private PasswordEncoder passwordEncoder;


    public UserService(AppUserRepo appUserRepo, PasswordEncoder passwordEncoder, TokenRepo tokenRepo, MailService mailService) {
        this.appUserRepo = appUserRepo;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepo = tokenRepo;
        this.mailService = mailService;
    }

    public void addUser(AppUser appUser) {
        appUserRepo.save(appUser);
        sendToken(appUser);
    }

    private void sendToken(AppUser appUser) {
        String tokenValue = UUID.randomUUID().toString();
        Token token = new Token();
        token.setValue(tokenValue);
        token.setAppUser(appUser);
        tokenRepo.save(token);
        String url = "http://localhost:8080/token?value=" + tokenValue;

        try {
            mailService.sendMail(appUser.getMail(), "Potwierdzenie rejestracji", url, false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
