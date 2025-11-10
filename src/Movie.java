import java.util.ArrayList;

public class Movie extends Media{
    boolean saved = false;
    boolean watched = false;

    public Movie(String name, String releaseDate, String categories, double rating){
        super(name, releaseDate, categories, rating);
    }

    @Override
    public int titleOptions() {
        System.out.println("*** Movie Options ***");
        System.out.println("1. Play movie");
        //Afspil medie
        //Print at mediet bliver afspillet
        //Tilføj mediet til liste over sete
        if (!saved) {
            System.out.println("2. Add to saved");
        }
        else {
            System.out.println("2. Remove from saved");
        }
        System.out.println("0. Go back");

        int decision = Client.getInteger();
        return decision;
    }

    public ArrayList<String> getCategories(){
        ArrayList<String> newCategories = new ArrayList<>();
        String[] categoryList = categories.split(",");
        for(String c : categoryList){
            newCategories.add(c);
        }
        return newCategories;
    }

//        if (decision == 0) {
//            System.out.println("Going back");
//        } else if  (decision == 1) {
//            System.out.println(this.name + " is now playing...");
//        } else if (decision == 2 && !saved) {
//            System.out.println(this.name + " has been saved");
//        } else if (decision == 2 && saved) {
//            System.out.println(this.name + " has been removed from saved");
//        } else {
//            System.out.println("Invalid option");
//        }


        //Gem info i users.csv
        //Gem medie i watchList
        //Opdater watchList i users.csv
        //Hvis mediet allerede er gemt så giv mulighed for at fjerne fra listen istedet

    @Override
    String getTitleName() {
        return this.name;
    }



    public String toString(){
        return this.name + ", " + releaseDate + ", " + categories + ", " + rating;
    }
}