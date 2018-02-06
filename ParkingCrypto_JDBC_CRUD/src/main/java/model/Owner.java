package model;

public class Owner {
   private int id;
   private String name;

    public Owner(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Owner() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
