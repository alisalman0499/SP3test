import util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Client {

    //Virker som god stil at oprette scanneren engang og bruge den på tværs af filen
    private static final Scanner scanner = new Scanner(System.in);
    private static final FileIO io = new FileIO();
    private static final TextUI ui = new TextUI();

    private static User mainUser;
    private static ArrayList<User> userProfiles =  new ArrayList<>();
    private static ArrayList<String> userProfilesData =  new ArrayList<>();

    private static HashMap<String, Media> titles = new HashMap<>();
    private static ArrayList<Media> movies = new ArrayList<>();


    public Client(){
    }

    public void startClient(){
        System.out.println("*** Welcome ***");
        String[] options = {"Log in", "Create user"};
        displayOptions(options,"You have 3 options, please enter an integer: ");
        System.out.println("0. Exit");
        int decision = getInteger();
        switch (decision){
            case 0:
                exitProgram();
            case 1:
                login();
                break;
            case 2:
                createUser();
                break;
            default:
                System.out.println("Not a valid option...");
                decision = getInteger();
                break;
        }
    }

    public void login(){
        System.out.println("Logging in...");
        String username = getUserName();
        String password = getPassword();
        if(validateUser(username, password)){
            ui.displayMsg("Welcome " + username);
            mainMenu();
        }
        else{
            ui.displayMsg("Invalid username or password...");
        }
    }

    public void createUser(){
        System.out.println("Creating user...");
        userProfilesData = io.readData("data/userLogin.csv");

        String username = getUserName();
        String password = getPassword();

        User user = new User(username, password);
        userProfiles.add(user);
        userProfilesData.add(user.toString());

        io.saveData(userProfilesData, "data/userLogin.csv", "Username, Password");

        login();

    }

    public boolean validateUser(String username, String password){
        HashMap<String,String> users = new HashMap<>();
        ArrayList<String> playerData = io.readData("data/userLogin.csv");
        for(String m : playerData){
            String[] values =  m.split(",");
            User user = new User(username, password);
            users.put(values[0], values[1].trim());
        }
        System.out.println(users);
        boolean comparison = users.get(username).equals(password);
        System.out.println(comparison);
        if (users.containsKey(username) && comparison){
            mainUser = new User(username, password);
            return true;
        }
        else {
            return false;
        }
    }


    public void mainMenu(){
        while(true){
            System.out.println("*** Main Menu ***");
            String[] options = {"Search for a title", "Search in category", "Watched titles", "Saved titles" };
            displayOptions(options, "Please choose an option: ");
            System.out.println("0. Exit program");
            int decision = getInteger();
            switch (decision){
                case 0:
                    exitProgram();
                case 1:
                    searchTitle();
                    break;
                case 2:
                    searchCategory();
                    break;
                case 3:
                    System.out.println(mainUser.getWatched());
                    break;
                case 4:
                    System.out.println(mainUser.getSaved());
                    break;
                default:
                    System.out.println("Not a valid option...");
                    break;
            }
        }
    }

    public void searchTitle(){
        loadMovies(); //Skift til loadTitles/loadMedia efter loadMovies er test
        boolean titleFound = false;
        while(!titleFound){
            String title = getString("Please enter a title: ");
            if(title.isEmpty() || title.equals("0")){
                break;
            }
            else if (titles.containsKey(title)){
                movieOptions(title);
                titleFound = true;
            }
            else {
                System.out.println("Not a valid title");
            }
        }

    }

    public void searchCategory(){
        loadMovies();
        String category = getString("Please enter a category: ");
        ArrayList<String> categoryList = new ArrayList<>();
        for (Media movie : movies){
            if (movie.getCategories().contains(category)){
                categoryList.add(movie.getTitleName());
            }
        }
        //System.out.println(categoryList);
        for (String title :  categoryList){
            ui.displayMsg(title);
        }

    }

    public String getUserName(){
        String username = getString("Username: ");
        return username;
    }

    public String getPassword(){
        String password = getString("Password: ");
        return password;
    }

    public static int getInteger(){
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please enter a valid integer:");
            }
        }
    }

    public String getString(String prompt){
        ui.displayMsg(prompt);
        return scanner.nextLine();
    }

    public void exitProgram(){
        ui.displayMsg("Quitting program...");
        System.exit(0);
    }

    public static void loadMovies(){
        ArrayList<String> movieData = io.readData("data/film.csv");
        for(String m : movieData){
            String[] values =  m.split(";");
            Movie movie = new Movie(
                    values[0].trim(), values[1].trim(), values[2].trim(), Test.parseDoubleComma(values[3].trim()));
            titles.put(values[0], movie);
            movies.add(movie);
        }
    }

    public void displayOptions(String[] options, String message){
        System.out.println(message);
        for(int i = 0; i < options.length; i++){
            System.out.println(i+1 + ". " + options[i]);
        }
    }

    public void movieOptions(String title){
        ui.displayMsg("*** Movie Options ***");
        ui.displayMsg("1. Play movie");
        boolean saved = checkIfSaved(title);
        if (!saved) {
            ui.displayMsg("2. Add to saved");
        }
        else {
            ui.displayMsg("2. Remove from saved");
        }
        ui.displayMsg("0. Go back");

        int decision = getInteger();

        if (decision == 0) {
            ui.displayMsg("Going back");
        } else if  (decision == 1) {
            playTitle(title);
        } else if (decision == 2) {
            if (saved) {
                removeTitle(title);
            }
            else {
                saveTitle(title);
            }
        } else {
            ui.displayMsg("Invalid option");
        }
    }

    public boolean checkIfSaved(String title){
        return mainUser.getSaved().contains(title);
    }

    public void playTitle(String title){
        ui.displayMsg(title + " is now playing...");
        mainUser.addToWatched(title);
    }

    public void saveTitle(String title){
        ui.displayMsg(title + " has now been saved");
        mainUser.addToSaved(title);
    }

    public void removeTitle(String title){
        ui.displayMsg(title + " has now been removed from saved");
        mainUser.removeFromSaved(title);
    }

}
