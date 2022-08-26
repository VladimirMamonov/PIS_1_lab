package entities;

import java.util.Objects;

public class Visitor {


    private long id;
    private String name;

    public Visitor() {
    }

    public Visitor(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;

    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Visitor(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visitor visitor = (Visitor) o;
        return id == visitor.id && name.equals(visitor.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
