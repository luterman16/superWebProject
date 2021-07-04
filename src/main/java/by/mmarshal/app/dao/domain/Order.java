package by.mmarshal.app.dao.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;

public class Order extends BaseEntity {
    private int price;
    private Date date;
    private int user_id;


    private Order(Builder builder) {
        this.id = builder.id;
        this.price = builder.price;
        this.date = builder.date;
        this.user_id = builder.user_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public java.sql.Date getDate() {
        return (java.sql.Date) date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public static Builder orderBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private int id;
        private int price;
        private Date date;
        private int user_id;

        private Builder() {
        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withPrice(int price) {
            this.price = price;
            return this;
        }

        public Builder withDate(Date date) {
            this.date = date;
            return this;
        }

        public Builder withUserId(int user_id) {
            this.user_id = user_id;
            return this;
        }

        public Order buildFromResultSet(ResultSet resultSet) throws SQLException {
            this.withId(resultSet.getInt("id"))
                    .withPrice(resultSet.getInt("PRICE"))
                    .withDate(resultSet.getDate("DATE"))
                    .withUserId(resultSet.getInt("USER_ID"));

            return new Order(this);
        }

        public Order build() {
            return new Order(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return price == order.price && user_id == order.user_id && Objects.equals(date, order.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, date, user_id);
    }
}
