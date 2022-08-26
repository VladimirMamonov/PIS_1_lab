package entities;

import java.util.Objects;

public class Exposition {

    private long id;
    private long showroom_id;
    private long exposition_topic_id;
    private String name;



    public Exposition(long id, String name, long showroom_id, long exposition_topic_id) {
        this.id = id;
        this.showroom_id = showroom_id;
        this.exposition_topic_id = exposition_topic_id;
        this.name = name;
    }

    public Exposition(String name, int showroom_id, int exposition_topic_id) {
        this.showroom_id = showroom_id;
        this.exposition_topic_id = exposition_topic_id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getShowroom_id() {
        return showroom_id;
    }

    public void setShowroom_id(long showroom_id) {
        this.showroom_id = showroom_id;
    }

    public long getExposition_topic_id() {
        return exposition_topic_id;
    }

    public void setExposition_topic_id(long exposition_topic_id) {
        this.exposition_topic_id = exposition_topic_id;
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
        Exposition that = (Exposition) o;
        return id == that.id && showroom_id == that.showroom_id && exposition_topic_id == that.exposition_topic_id && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, showroom_id, exposition_topic_id, name);
    }

    public Exposition() {
    }

    @Override
    public String toString() {
        return "Exposition{" +
                "id=" + id +
                ", showroom_id=" + showroom_id +
                ", exposition_topic_id=" + exposition_topic_id +
                ", name='" + name + '\'' +
                '}';
    }
}