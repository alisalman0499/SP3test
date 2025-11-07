import java.util.ArrayList;
import java.util.Objects;

public class User {
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

    public void addToSaved(String title) {
        savedTitles.add(title);
    }

    public void removeFromSaved(String title) {
        savedTitles.remove(title);
    }

    public void addToWatched(String title) {
        watchedTitles.add(title);
    }

    public ArrayList<String> getWatched() {
        return watchedTitles;
    }

    public ArrayList<String> getSaved() {
        return savedTitles;
    }
}