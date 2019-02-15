package ubi.soft.testlibraries.items.drinksitems;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class DrinksItems extends RealmObject {
    @PrimaryKey
    @Required
    private String id;
    @Required
    private String name;
    private float rating;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public enum FieldNames {
        ID("id"),
        NAME("name"),
        RATING("rating");

        private String fieldName;

        FieldNames(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getFieldName() {
            return fieldName;
        }
    }
}
