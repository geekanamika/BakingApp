
package com.example.android.bakingapp.data.entities;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

public class RecipeResponse implements Parcelable
{

    @Expose
    private int id;
    @Expose
    private String name;
    @Expose
    private List<Ingredient> ingredients = null;
    @Expose
    private List<Step> steps = null;
    @Expose
    private int servings;
    @Expose
    private String image;
    public final static Creator<RecipeResponse> CREATOR = new Creator<RecipeResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public RecipeResponse createFromParcel(Parcel in) {
            return new RecipeResponse(in);
        }

        public RecipeResponse[] newArray(int size) {
            return (new RecipeResponse[size]);
        }

    }
    ;

    protected RecipeResponse(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.ingredients, (Ingredient.class.getClassLoader()));
        in.readList(this.steps, (com.example.android.bakingapp.data.entities.Step.class.getClassLoader()));
        this.servings = ((int) in.readValue((int.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
    }

    public RecipeResponse() {
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeList(ingredients);
        dest.writeList(steps);
        dest.writeValue(servings);
        dest.writeValue(image);
    }

    public int describeContents() {
        return  0;
    }

}
