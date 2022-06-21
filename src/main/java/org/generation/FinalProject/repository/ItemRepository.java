package org.generation.FinalProject.repository;
//ITEM REPOSITORY INTERFACE
//The ItemRepository created is to extend the CRUDrepo provided by Spring data JPA package

import org.generation.FinalProject.repository.entity.Item;
import org.springframework.data.repository.CrudRepository;



    //Not only the item repo inherit all the methods from crud repo interface, Item repo also have it's own method.
    //So now can use the interface to perform CRUD operation (inherit from parent)

    public interface ItemRepository extends CrudRepository<Item, Integer> {
        //through the interface we know what methods there are
        //<Item, Integer> Item here refer to the Entity, integer can be Id. Key Value pair
        //After this set up go back to ItemService MySQL class to update

    }



