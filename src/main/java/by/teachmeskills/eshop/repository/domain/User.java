package by.teachmeskills.eshop.repository.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "users")
@NamedQuery(name = "findAllUser", query = "select u from User u order by u.email desc")
public class User extends BaseEntity {
    private String name;
    private String surname;

    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Email must be valid")
    private String email;

    @NotEmpty(message = "Password must not be empty")
    @Size(min = 5, max = 30, message = "Password must be between 5 and 30 characters")
    @Pattern(regexp = "\\S+", message = "Spaces are not allowed")
    private String password;
    private String dateOfBirthday;
    private int balance;

    public User() {
    }

    private User(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.email = builder.email;
        this.password = builder.password;
        this.dateOfBirthday = builder.dateOfBirthday;
        this.balance = builder.balance;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    @Column(name = "email", unique = true)
    public String getEmail() {
        return email;
    }

    @Column(name = "password", unique = true)
    public String getPassword() {
        return password;
    }

    @Column(name = "date_of_birthday")
    public String getDateOfBirthday() {
        return dateOfBirthday;
    }

    @Column(name = "balance")
    public int getBalance() {
        return balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDateOfBirthday(String dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public static Builder userBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private int id;
        private String name;
        private String surname;
        private String email;
        private String password;
        private String dateOfBirthday;
        private int balance;

        private Builder() {
        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withDateOfBirthday(String dateOfBirthday) {
            this.dateOfBirthday = dateOfBirthday;
            return this;
        }

        public Builder withBalance(int balance) {
            this.balance = balance;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", dateOfBirthday='" + dateOfBirthday + '\'' +
                ", balance=" + balance +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getBalance() == user.getBalance() &&
                getName().equals(user.getName()) &&
                getSurname().equals(user.getSurname()) &&
                getEmail().equals(user.getEmail()) &&
                getPassword().equals(user.getPassword()) &&
                getDateOfBirthday().equals(user.getDateOfBirthday());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSurname(), getEmail(), getPassword(), getDateOfBirthday(), getBalance());
    }
}