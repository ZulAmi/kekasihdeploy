package org.generation.FinalProject;

import org.generation.FinalProject.repository.ItemRepository;
import org.generation.FinalProject.repository.entity.Item;
import org.generation.FinalProject.sevice.ItemService;
import org.generation.FinalProject.sevice.ItemServiceMySQL;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ItemServiceMySQLTest {

    ItemService itemService;    //actual object to test

    //ItemRepository is an interface, it extends CrudRepository (given by Spring framework

    @Mock
    ItemRepository itemRepository;

    //this mock item is for testing findById method from the ItemRepository
    Item itemMock;

    @BeforeEach
    public void setup() {
        itemService = new ItemServiceMySQL(itemRepository);
        itemMock = Mockito.mock(Item.class);
    }

    @Test
    public void saveCallsItemsRepositorySave() {
        itemService.save(itemMock);                                  Mockito.verify(itemRepository).save(itemMock);
    }

    @Test
    public void deleteCallsItemsRepositoryDelete() {
        int itemId = 2;
        itemService.delete(itemId);
        Mockito.verify(itemRepository).deleteById(itemId);
    }

    @Test
    public void findByIdCallsItemsRepositoryFindById()
    {
        int itemId = 10;

        //In mockito, we can specify what to return when a method is called.
        //The test is against the return from the actual object vs the mock object

        //itemRepository.findById will return a mock item to compare with the actual itemService.findById
        Mockito.when(itemRepository.findById(itemId)).thenReturn(Optional.of(itemMock));

        //Actual object to be tested
        Item item = itemService.findbyId(itemId);

        //assertEquals is to check if both actual item object and the mock item object is equal
        Assertions.assertEquals(item, itemMock);

    }
    @Test
    public void listCallsItemsRepositoryList()
    {
        itemService.all();
        Mockito.verify(itemRepository).findAll();
    }






}
