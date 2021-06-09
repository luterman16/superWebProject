package by.mmarshal.app.bootstrap.model;

import java.util.Objects;

public class Item {
    int id;
    String name;
    String image;
    String descriptor;
    double price;
    String type;

    public Item(int id, String name, String image, String descriptor, double price, String type) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.descriptor = descriptor;
        this.price = price;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", descriptor='" + descriptor + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Double.compare(item.price, price) == 0 && Objects.equals(image, item.image) && Objects.equals(descriptor, item.descriptor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(image, descriptor, price);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
