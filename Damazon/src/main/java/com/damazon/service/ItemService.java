package com.damazon.service;

import com.damazon.dto.ItemResponse;
import com.damazon.model.Item;
import com.damazon.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemService {
	 private final ItemRepository itemRepository;
	 
	 @Autowired
	    public ItemService(ItemRepository itemRepository) {
	        this.itemRepository = itemRepository;
	    }
	 
	 // Anyone can fetch doesn't even have to be user
	 public List<Item> findAllProducts(){
		 return itemRepository.findAllItems();
	 }
	 
	 // Only admin fetchable
	 public Item saveProduct(Item item) {
		 if (item == null || item.getName() == null || item.getName().isEmpty()) {
	            return null;
	        }
		 itemRepository.save(item);
		 return item;
	 }
	 
	 //Updating the product with the given product, Again only Admin can invoke this
	 //.map is used for optional field where If the option is satisfied the mapping function is invoked
	 //if Optional was empty, meaning in this case no such itemId  then .orElse is returned
	 @Transactional
	 public Item updateProduct(Integer itemId, Item item) {
	     return itemRepository.findById(itemId)
	             .map(existingItem -> {
	                 existingItem.setName(item.getName());
	                 existingItem.setDescription(item.getDescription());
	                 existingItem.setPrice(item.getPrice());
	                 existingItem.setRating(item.getRating());
	                 existingItem.setImgLocation(item.getImgLocation());
	                 return itemRepository.save(existingItem);
	             })
	             .orElse(null);  
	 }
	 
	 public boolean deleteProduct(Integer itemID) {
		 if (itemRepository.existsById(itemID)) {
	            itemRepository.deleteById(itemID); //According to Cruz it looks at the field of class @Id, making itemID the deleteById
	            return true;
		 }
		 return false; //When product doesn't exist :(
	 }

	 
	 public ItemResponse findItemByName(String productName) {
		    List<Item> itemsSame = itemRepository.findByProductName(productName);
		    if (itemsSame.isEmpty()) {
		        return null;  
		    }
		    Item item = itemsSame.get(0);  // Take the first item to get the details
		    ItemResponse response = new ItemResponse();
		    response.setItemId(item.getItemId());
		    response.setName(item.getName());
		    response.setDescription(item.getDescription());
		    response.setPrice(item.getPrice());
		    response.setProductAmount(itemsSame.size());  // Sets the count with amount in the list
		    return response;
		}

}
