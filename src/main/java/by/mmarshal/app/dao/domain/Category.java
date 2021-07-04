package by.mmarshal.app.dao.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Category extends BaseEntity{
    private String name;
    private int rating;
    private String image_path;

    public static Builder categoryBuilder() {
        return new Builder();
    }

    private Category(Category.Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.rating = builder.rating;
        this.image_path = builder.image_path;
    }

    public static Category.Builder CategoryBuilder() {
        return new Category.Builder();
    }

    public static final class Builder {
        private int id;
        private String name;
        private int rating;
        private String image_path;

        private Builder() {
        }

        public Category.Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Category.Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Category.Builder withRating(int rating) {
            this.rating = rating;
            return this;
        }

        public Category.Builder withImagePath(String image_path) {
            this.image_path = image_path;
            return this;
        }


        public Category buildFromResultSet(ResultSet resultSet) throws SQLException {
            this.withId(resultSet.getInt("id"))
                    .withName(resultSet.getString("name"))
                    .withRating(resultSet.getInt("rating"))
                    .withImagePath(resultSet.getString("image_path"));

            return new Category(this);
        }

        public Category build() {
            return new Category(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return rating == category.rating && Objects.equals(name, category.name) && Objects.equals(image_path, category.image_path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rating, image_path);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRATING() {
        return rating;
    }

    public void setRATING(int rating) {
        this.rating = rating;
    }

    public String getIMAGE_PATH() {
        return image_path;
    }

    public void setIMAGE_PATH(String image_path) {
        this.image_path = image_path;
    }
}
