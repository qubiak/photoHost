An application that works like hosting of image files. Upload files to the server. 
Use SpringBoot, Vaadin, cloudinary, 2FA, JPA Repository, remotemysql.

    http: // localhost: 8080 / login
Logging in, taking over to registration if the user is not registered.

    http: // localhost: 8080 / register
New user registration

    http: // localhost: 8080 / upload
Upload pictures, only for the role: ADMIN

    http: // localhost: 8080 / gallery
Viewing uploaded images.




Test users:


(1)

    Login: UserJan

    Password: UserJan

    Role: USER

    isEnabled: true


User with access to the gallery with the option of uploading files.


(2)

    Login: UserMarian

    Password: UserMarian

    Role: USER

    isEnabled: false


User who has not confirmed registration via email. No accesses.

(3)

    Login: AdminAngelika

    Password: AdminAngelika

    Role: ADMIN

    isEnabled: true


User who can browse the gallery and add new pictures.


(4)

    Login: AdminMariola

    Password: AdminMariola

    Role: ADMIN

    isEnabled: false


User who has not confirmed registration via email. No accesses.


create by qubiak