package org.generation.FinalProject;

import org.generation.FinalProject.controller.ItemController;
import org.generation.FinalProject.sevice.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.*;

//Lifecycle.PER_METHOD (the default) is the @TestInstance
// - All @Test method will have a new/clean state of the ItemControllerTest object that will be created

//LifeCycle.PER_CLASS - The ItemControllerTest object created will be shared and resued with all the @Test methods

@SpringBootTest
public class ItemControllerTest {

    //1) Identify the actual object to be tested, and the dependencies objects to be mock for DI

    ItemController itemController; //actual object to be tested
    ItemService itemService; //dependent object - mock object

    @BeforeEach
    public void setup()
    {

        itemService = Mockito.mock(ItemService.class);
        itemController = new ItemController(itemService);
    }

    @Test
    public void listItemService()
    {
        itemController.getItems();
        Mockito.verify(itemService).all();

        //Mockito verify methods are used to check certain behaviour/methods that happens. Verify method in the testing is to make sure that specified methods are called.
    }

    @Test
    public void findItemService()
    {
        Mockito.reset(itemService);
        int id = 2;
        itemController.findItemById(id);
        Mockito.verify(itemService).findbyId(id);
    }

    @Test
    public void deleteItemService() {
        Mockito.reset(itemService);
        int id = 2;
        itemController.delete(id);
        Mockito.verify(itemService).delete(id);


    }


}
