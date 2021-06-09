package by.mmarshal.app.bootstrap.model;

import java.util.Objects;

public class Category {
    private String name;
    private String imageName;



    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", imageName='" + imageName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name) && Objects.equals(imageName, category.imageName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, imageName);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Category(String name, String imageName) {
        this.name = name;
        this.imageName = imageName;
    }
}
