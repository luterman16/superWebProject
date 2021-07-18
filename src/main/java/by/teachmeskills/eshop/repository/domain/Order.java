package by.teachmeskills.eshop.repository.domain;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    private Date date;
    private int price;
    private User user;
    private List<Product> productsList;

    private Order(Builder builder) {
        this.id = builder.id;
        this.date = builder.date;
        this.price = builder.price;
        this.user = builder.user;
        this.productsList = builder.productsList;
    }

    public Order() {
    }

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "orders_products", joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    public List<Product> getProductsList() {
        return productsList;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setProductsList(List<Product> productsList) {
        this.productsList = productsList;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private int id;
        private Date date;
        private int price;
        private User user;
        private List<Product> productsList;

        private Builder() {
        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withDate(Date date) {
            this.date = date;
            return this;
        }

        public Builder withPrice(int price) {
            this.price = price;
            return this;
        }

        public Builder withUser(User user) {
            this.user = user;
            return this;
        }

        public Builder withListOfProducts(List<Product> productList) {
            this.productsList = productList;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

}
