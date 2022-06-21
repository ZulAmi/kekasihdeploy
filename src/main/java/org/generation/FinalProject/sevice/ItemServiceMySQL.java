package org.generation.FinalProject.sevice;

import org.generation.FinalProject.repository.ItemRepository;
import org.generation.FinalProject.repository.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service //to inform spring boot that this is service class
public class ItemServiceMySQL implements ItemService {

    /*
            dependency injection
            -transferring the task of creating the OBJECT TO SOMEONE ELSE

            Normally how we create an instance object of another class ItemServiceMySQL depends on the CRUDRepository Class to perform the crud operation

            we are creating the instance object inside the itemservicemysql class
     */
    private final ItemRepository itemRepository;
    // We need to 'inject' the itemRepository obj into ItemServiceMySQL class obj
    public ItemServiceMySQL (@Autowired ItemRepository itemRepository)
    {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public void delete(int itemId) {
        itemRepository.deleteById(itemId);
    }

    @Override
    public List<Item> all() {
        List<Item> result = new ArrayList<>();
        itemRepository.findAll().forEach( result ::add);
        return result; //will update later
    }

    @Override
    public Item findbyId(int itemId) {
        Optional<Item> item = itemRepository.findById(itemId);
        Item itemresponse = item.get();
        return itemresponse;
    }

}
