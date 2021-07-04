package by.mmarshal.app.dao.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class User extends BaseEntity {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String dateOfBirthday;
    private int balance;

    private User(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.email = builder.email;
        this.password = builder.password;
        this.dateOfBirthday = builder.dateOfBirthday;
        this.balance = builder.balance;
    }


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDateOfBirthday() {
        return dateOfBirthday;
    }

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

        public User buildFromResultSet(ResultSet resultSet) throws SQLException {
            this.withId(resultSet.getInt("id"))
                    .withName(resultSet.getString("name"))
                    .withSurname(resultSet.getString("surname"))
                    .withEmail(resultSet.getString("email"))
                    .withPassword(resultSet.getString("password"))
                    .withDateOfBirthday(resultSet.getString("date_of_birthday"))
                    .withBalance(resultSet.getInt("balance"));

            return new User(this);
        }

        public User build() {
            return new User(this);
        }
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
