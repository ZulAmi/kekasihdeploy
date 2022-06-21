package org.generation.FinalProject.sevice;
//Interface as a guide to see what are the methods avail that the controller can call and perform action.

import org.generation.FinalProject.repository.entity.Item;

import java.util.List;

public interface ItemService {

    //save method is for 2 purposes - Create new item & Update existing item
    Item save(Item item);

    //Delete item from database - based on item Id
    void delete(int itemId);

    //Read all item from database
    List<Item> all();

    //Read an item from database - based on item Id
    Item findbyId(int itemId);
}


