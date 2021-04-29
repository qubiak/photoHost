package pl.qubiak.photoHosting.Giu;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import pl.qubiak.photoHosting.Service.AuthService;

@Route(value = "login")
@PageTitle("Login")
public class LoginViev extends Div {


    public LoginViev(AuthService authService) {

        setId("login-viev");
        var username = new TextField("Username");
        var password = new PasswordField("Password");
        var textArea = new TextArea("");
        textArea.setPlaceholder("Login like Admin. Username: ADMIN, Password: ADMIN");
        add(
                new VerticalLayout(
                        new H1("WELCOME"),
                        username,
                        password,
                        new Button("Login", event -> {
                            try {
                                authService.authenticate(username.getValue(), password.getValue());

                            } catch (AuthService.AuthException e) {
                                Notification.show("Wrong credentials.");
                            }

                        }),
                        new RouterLink("Register", RegisterView.class),
                        textArea
                ));
    }
}
