package es.storeapp.business.entities;

import es.storeapp.common.Constants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = Constants.USER_ENTITY)
@Table(name = Constants.USERS_TABLE)
public class User implements Serializable {

    private static final long serialVersionUID = 570528466125178223L;

    public User() {
    }

    public User(String name, String email, String password, String address, String image) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.image = image;
    }

    private void validarName(String name) throws InvalidNameException {
        if (name == null || name.isEmpty()) {
            throw new InvalidNameException("El nombre es obligatorio.");
        }
    }

    private void validarEmail(String email) throws InvalidEmailException {
        if (email == null || email.isEmpty()) {
            throw new InvalidEmailException("El email es obligatorio.");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).find()) {
            throw new InvalidEmailException("El email no tiene un formato válido.");
        }
    }

    private void validarPassword(String password) throws InvalidPasswordException {
        if (password == null || password.isEmpty()) {
            throw new InvalidPasswordException("La contraseña es obligatoria.");
        } else if (password.length() < 8) {
            throw new InvalidPasswordException("La contraseña debe contener al menos 8 caracteres.");
        }
    }

    private void validarAddress(String address) throws InvalidAddressException {
        if (address == null || address.isEmpty()) {
            throw new InvalidAddressException("La dirección es obligatoria.");
        }
    }

    class InvalidNameException extends Exception {

        public InvalidNameException(String message) {
            super(message);
        }
    }

    class InvalidEmailException extends Exception {

        public InvalidEmailException(String message) {
            super(message);
        }
    }

    class InvalidPasswordException extends Exception {

        public InvalidPasswordException(String message) {
            super(message);
        }
    }

    class InvalidAddressException extends Exception {

        public InvalidAddressException(String message) {
            super(message);
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "name", nullable = true, unique = false)
    private String name;

    @Column(name = "email", nullable = true, unique = true)
    private String email;

    @Column(name = "password", nullable = true)
    private String password;

    @Column(name = "address", nullable = true)
    private String address;

    @Column(name = "resetPasswordToken")
    private String resetPasswordToken;

    @Embedded
    @AttributeOverrides(value = {
        @AttributeOverride(name = "card", column = @Column(name = "card")),
        @AttributeOverride(name = "cvv", column = @Column(name = "CVV")),
        @AttributeOverride(name = "expirationMonth", column = @Column(name = "expirationMonth")),
        @AttributeOverride(name = "expirationYear", column = @Column(name = "expirationYear"))
    })
    private CreditCard card;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CreditCard getCard() {
        return card;
    }

    public void setCard(CreditCard card) {
        this.card = card;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    @Override
    public String toString() {
        return String.format("User{userId=%s, name=%s, email=%s, password=%s, address=%s, resetPasswordToken=%s, card=%s, image=%s}",
                userId, name, email, password, address, resetPasswordToken, card, image);
    }

}
