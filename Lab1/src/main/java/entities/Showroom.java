package entities;

import java.sql.Timestamp;
import java.util.Objects;

public class Showroom {
    private long id;
    private String name;


    public Showroom(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Showroom(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Showroom showroom = (Showroom) o;
        return id == showroom.id && name.equals(showroom.name);
    }

    public Showroom() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Showroom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


}