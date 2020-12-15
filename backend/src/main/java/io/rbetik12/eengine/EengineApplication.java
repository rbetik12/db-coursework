package io.rbetik12.eengine;

import io.rbetik12.eengine.entity.*;
import io.rbetik12.eengine.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
public class EengineApplication {

	@Autowired
	private CurrencyService currencyService;

	@Autowired
	private RegionService regionService;

	@Autowired
	private ActorService actorService;

	@Autowired
	private ItemTypeService itemTypeService;

	@Autowired
	private ClanService clanService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private FactoryInputItemService factoryInputItemService;

	@Autowired
	private FactoryService factoryService;

	@Autowired
	private PropertyService propertyService;

	@Autowired
	private ItemListingService itemListingService;

	public static void main(String[] args) {
		SpringApplication.run(EengineApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void test() {
		List<Currency> currencyList = currencyService.getCurrencyByName("Ruble");
		System.out.println(currencyList.get(0).getPrice());
		List<Region> regionList = regionService.getRegionByName("Landia");


		List<Region> allRegions = regionService.getAll();
		System.out.println(allRegions.size());

		List<Actor> allActors = actorService.getAll();
		System.out.println(allActors.size());

		List<ItemType> allItemTypes = itemTypeService.getAll();
		System.out.println(allItemTypes.size());

		List<Clan> allClans = clanService.getAll();
		System.out.println(allClans.get(0).getRegion().getName());

		List<Item> allItems = itemService.getAll();
		System.out.println(allItems.get(0).getType().getName());

		List<ActorInventory> allInventories = inventoryService.getAll();
		System.out.println(allInventories.get(0).getActor().getName());
		System.out.println(allInventories.get(0).getItem().getName());

		List<FactoryInputItem> factoryInputItems = factoryInputItemService.getAll();
		System.out.println(factoryInputItems.size());

		List<Factory> factories = factoryService.getAll();
		System.out.println(factories.size());

		List<Property> properties = propertyService.getAll();
		System.out.println(properties.size());

		List<ItemListing> itemListings = itemListingService.getAll();
		System.out.println(itemListings.get(0).getItem().getName());
	}
}
