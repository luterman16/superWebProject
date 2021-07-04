package by.mmarshal.app.dao.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Product extends BaseEntity{
    private String name;
    private String description;
    private String image_path;
    private int price;
    private int category_id;

    private Product(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.image_path = builder.image_path;
        this.price = builder.price;
        this.category_id = builder.category_id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public static Builder productBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private int id;
        private String name;
        private String description;
        private String image_path;
        private int price;
        private int category_id;

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

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withImagePath(String image_path) {
            this.image_path = image_path;
            return this;
        }

        public Builder withPrice(int price) {
            this.price = price;
            return this;
        }

        public Builder withCategoryId(int category_id) {
            this.category_id = category_id;
            return this;
        }

        public Product buildFromResultSet(ResultSet resultSet) throws SQLException {
            this.withId(resultSet.getInt("id"))
                    .withName(resultSet.getString("NAME"))
                    .withDescription(resultSet.getString("DESCRIPTION"))
                    .withImagePath(resultSet.getString("IMAGE_PATH"))
                    .withPrice(resultSet.getInt("PRICE"))
                    .withCategoryId(resultSet.getInt("CATEGORY_ID"));

            return new Product(this);
        }

        public Product build() {
            return new Product(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price && category_id == product.category_id && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(image_path, product.image_path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, image_path, price, category_id);
    }
}
