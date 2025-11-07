abstract class Media {
    protected String name;
    protected String type;
    protected String releaseDate;
    protected String categories;
    protected double rating;

    public Media(String name, double rating){
        this.name = name;
        this.rating = rating;
    }

    public Media(String name, String releaseDate, String categories, double rating){
        this.name = name;
        this.releaseDate = releaseDate;
        this.categories = categories;
        this.rating = rating;
    }

    abstract int titleOptions();
    abstract String getTitleName();


}
