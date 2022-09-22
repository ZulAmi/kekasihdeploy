package org.generation.FinalProject.repository.entity;

import org.generation.FinalProject.controller.dto.ItemDto;
import org.generation.FinalProject.controller.dto.ItemDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//This is in the model component
//Item Class obj will be used to map up with the item table from database
@Entity
public class Item {
    //We need to identify which attribute is the ID and how the id is generated
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //identity tells spring boot that it is automatically generated
    private Integer id; //using Integer (obj) instead of a primitive need to pass the ID to a method
    private String name;
    private String description;
    private String imageUrl;
    private double price;
    public Item() {}

    public Item( ItemDto itemDto )
    {
        this.name = itemDto.getName();
        this.description = itemDto.getDescription();
        this.imageUrl = itemDto.getImageUrl();
        this.price = itemDto.getPrice();
    }

    public Integer getId()
    {
        return id;
    }

    public void setId( Integer id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl( String imageUrl )
    {
        this.imageUrl = imageUrl;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice( double price )
    {
        this.price = price;
    }

    @Override
    public String toString()
    {
        return "Item{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\'' + ", imageUrl='"
                + imageUrl + '\'' + ", price='" + price + '}';
    }
}



