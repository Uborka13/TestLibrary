package ubi.soft.testlibraries.items.barsitems;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class BarsItems extends RealmObject {
    @PrimaryKey
    @Required
    private String id;
    @Required
    private String name;
    private float rating;
    @Required
    private int population;

    public String getId() {
        return id;
    }

    public BarsItems withId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public BarsItems withName(String name) {
        this.name = name;
        return this;
    }

    public float getRating() {
        return rating;
    }

    public BarsItems withRating(float rating) {
        this.rating = rating;
        return this;
    }

    public int getPopulation() {
        return population;
    }

    public BarsItems withPopulation(int population) {
        this.population = population;
        return this;
    }
}
