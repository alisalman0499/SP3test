import java.util.ArrayList;

public class Show extends Media{
    private String episodes;
    private ArrayList<Episode> episo


    public Show(String name, String releaseDate, String categories, double rating, String episodes){
        super(name, releaseDate, categories, rating);
        this.episodes = episodes;
    }

    public loadEpisodes(){
        Episode[][] = new Episode[getEpisodeList().size()][];

    }

    public ArrayList<String> getEpisodeList(){
        ArrayList<String> allEpisodes = new ArrayList<>();
        String[] seasonEpisodes = episodes.split(",");
        for(String s : seasonEpisodes){
            String[] episodeNumber = s.split("-");
            allEpisodes.add(s);
        }
        return allEpisodes;
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