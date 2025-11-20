import util.FileIO;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class User {
    FileIO io = new FileIO();
    private final String userName;
    private String password;
    private ArrayList<String> watchedTitles = new ArrayList<>();
    private ArrayList<String> savedTitles = new ArrayList<>();


    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return this.userName;
    }

    public void changePassword(String newPassword) {
        password = newPassword;
    }

    @Override
    public String toString() {
        return userName + ", " + password; //TO-DO
    }

    public void loadUser(){
        //Create directory for the user if it doesnt exist
        String filePath = "data/Users/" + userName;
        System.out.println(filePath);
        File dir = new File(filePath);
        if (!dir.exists()) {
            boolean created = dir.mkdirs(); // or dir.mkdir() for only one level
            if (!created) {
                System.err.println("Failed to create directory");
            }
        }
        //Load directory for the user if it does
        //Load files
    }
    public void addToSaved(String title) {
        savedTitles.add(title);
        io.saveData(savedTitles, "data/savedList.csv", "Saved Titles");
    }
    public void removeFromSaved(String title) {
        savedTitles.remove(title);
    }

    public void addToWatched(String title) {
        watchedTitles.add(title);
        io.saveData(watchedTitles, "data/watchedList.csv", "Watched Titles");
    }

    public ArrayList<String> getWatched() {
        return watchedTitles;
    }

    public ArrayList<String> getSaved() {
        return savedTitles;
    }

}