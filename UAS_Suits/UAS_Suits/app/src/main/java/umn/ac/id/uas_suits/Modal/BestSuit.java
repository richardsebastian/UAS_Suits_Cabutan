package umn.ac.id.uas_suits.Modal;

public class BestSuit {

    String name;
    String price;
    Integer imageUrl;

    public BestSuit(String name, String price, Integer imageUrl, String rating, String tailorname) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.tailorname = tailorname;
    }

    String rating;
    String tailorname;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTailorname() {
        return tailorname;
    }

    public void setTailorname(String tailorname) {
        this.tailorname = tailorname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }
}
