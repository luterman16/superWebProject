package by.teachmeskills.eshop.repository.domain;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {
    private String name;
    private int rating;
    private String imageName;
    private List<Product> productList;

    private Category(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.rating = builder.rating;
        this.productList = builder.productList;
    }

    public Category() {
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "rating")
    public int getRating() {
        return rating;
    }

    @Column(name = "image_path")
    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST)
    public List<Product> getProductList() {
        return productList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private int id;
        private String name;
        private int rating;
        private List<Product> productList;

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

        public Builder withRating(int rating) {
            this.rating = rating;
            return this;
        }

        public Builder withProductList(List<Product> productList) {
            this.productList = productList;
            return this;
        }

        public Category build() {
            return new Category(this);
        }
    }

}