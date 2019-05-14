package ucll.project.domain.user;

import ucll.project.db.DatabaseEntity;
import ucll.project.db.DatabaseException;
import ucll.project.domain.DomainException;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class User extends DatabaseEntity<Integer> {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private static int globalKey;

    // hashed password
    private transient String hashedPassword;

    public User(Integer primaryKey) {
        super(primaryKey);
    }

    public User(Integer primaryKey, String userName, String firstName, String lastName, String email, Gender gender){
        super(primaryKey);
        setUserName(userName);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setGender(gender);
    }

    public User(String userName, String firstName, String lastName, String email, Gender gender){
        super(globalKey++);
        setUserName(userName);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setGender(gender);
    }

    public void hashAndSetPassword(String password) {
        if (password.length() < 4) {
            throw new IllegalArgumentException("Too short password!");
        }
        String hashed = getPasswordToHashedPassword(password);
        setHashedPassword(hashed);
    }

    // This function will hash the password
    protected String getPasswordToHashedPassword(String password) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
        digest.update(password.getBytes(StandardCharsets.UTF_8));
        String hash = DatatypeConverter.printHexBinary(digest.digest()).toUpperCase();
        return hash;
    }

    public boolean isValidPassword(String password) {
        if (getHashedPassword() == null) {
            return false;
        }
        return getPasswordToHashedPassword(password).equals(getHashedPassword());
    }

    // Getters and setters and toString
    public String getUserName() {
        return this.userName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public Gender getGender() {
        return this.gender;
    }

    public String getHashedPassword() {
        return this.hashedPassword;
    }

    public String getFullName() {
        return this.lastName + " " + this.firstName;
    }

    public void setUserName(String userName) {
        if (userName == null || userName.trim().isEmpty()) {
            throw new DomainException("username can't be empty");
        }
        this.userName = userName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new DomainException("firstName can't be empty");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new DomainException("lastName can't be empty");
        }
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new DomainException("email can't be empty");
        }
        this.email = email;
    }

    public void setGender(Gender gender) {
        if (gender == null) {
            throw new DomainException("gender can't be empty");
        }
        this.gender = gender;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String toString() {
        return "(userName=" + this.getUserName() + ", firstName=" + this.getFirstName() + ", lastName=" + this.getLastName() + ", email=" + this.getEmail() + ", gender=" + this.getGender() + ", hashedPassword=" + this.getHashedPassword() + ")";
    }
}
