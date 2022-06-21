package org.generation.FinalProject.controller.dto;

//Data transfer object for Item
//In the controller component (MVC), this class obj is going to receive the information that is send from the client (through HTTP POST request)

//This is somewhere where they hold the information before passing into the database
public class ItemDto {
//id is missing here because it is auto generated and the information is not external input
    private String name;
    private String description;
    private String imageUrl;
    private double price;

    public ItemDto( String name, String description, String imageUrl, double price )
    {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}


