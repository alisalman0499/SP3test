import java.util.ArrayList;

public class Movie extends Media{

    public Movie(String name, String releaseDate, String categories, double rating){
        super(name, releaseDate, categories, rating);
    }


    public ArrayList<String> getCategories(){
        ArrayList<String> newCategories = new ArrayList<>();
        String[] categoryList = categories.split(",");
        for(String c : categoryList){
            newCategories.add(c);
        }
        return newCategories;
    }

    @Override
    String getTitleName() {
        return this.name;
    }

    public String toString(){
        return this.name + ", " + releaseDate + ", " + categories + ", " + rating;
    }
}