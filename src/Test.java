import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import util.*;

public class Test {

    public static FileIO io = new FileIO();
    public static ArrayList<Media> movies = new ArrayList<>();
    public static HashMap<String, Movie> moviePositions = new HashMap<>();


    public static void main(String[] args) {
        loadMovies();
        //System.out.println(moviePositions);
        //System.out.println(moviePositions.get("The Shawshank Redemption"));
        System.out.println(moviePositions);
    }

    public static void loadMovies(){
        ArrayList<String> movieData = io.readData("data/film.csv");
        //System.out.println(movieData);
        for(String m : movieData){
            String[] values =  m.split(";");//  "tess, 0"
            //Movie movie = new Movie(values[0], values[1].trim(), values[2].trim(), Integer.parseInt(values[3]));
            //movies.add(movie);
            //Movie movie = new Movie("The Shawshank Redemption", "1994", "Drama", parseDoubleComma("9,3"));
            Movie movie = new Movie(
                    values[0].trim(), values[1].trim(), values[2].trim(), parseDoubleComma(values[3].trim()));
            moviePositions.put(values[0], movie);
            //ArrayListen behøves i realitet ikke længere men den beholdes for en sikkerhedsskyld hvis den skulle bruges
            //Fjernes senere hvis den ikke bruges
            movies.add(movie);
        }

    }

    public static void searchByCategory(){
        //Hvilken kategori vil vi gerne have
        //Hvilke film har vores kategori
            //Hvordan får vi kategori fra vores film?
            //Hvad gør vi når vi har fundet kategorien?

        //Ask user for input
        Client client = new Client();
        String input = client.getString("Enter the category you want to search for: ");



    }

    public static double parseDoubleComma(String numberToParse){
        //Skifter locale da der er komma i tallene fra filen
        NumberFormat nf = NumberFormat.getInstance(Locale.GERMANY); // or Locale.FRANCE, etc.
        try {
            Number number = nf.parse(numberToParse);
            return number.doubleValue();
            // Output: 3.14
        } catch (ParseException e) {
            System.err.println("Cannot parse: " + numberToParse);
        }
        return 0;
    }
}
