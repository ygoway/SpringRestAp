package com.example.hw7.service;

import com.example.hw7.model.Shop;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShopService {

    private Map<Long, Shop> shopData = new HashMap<>();

    public Shop addShop(Shop shop) {
        if (shop == null) {
            throw new IllegalArgumentException("Shop cannot be a null!");
        }
        if (shop.getId() == null) {
            throw new IllegalArgumentException("Shop id cannot be a null!");
        }
        if (shopData.containsKey(shop.getId())) {
            throw new IllegalArgumentException("Shop with id : " + shop.getId() + " already exist!");
        }
        return shopData.put(shop.getId(), shop);
    }

    public void deleteShopById(Long shopId) {
        if (shopId == null) {
            throw new IllegalArgumentException("Shop id cannot be a null!");
        }
        if (shopData.containsKey(shopId)) {
            shopData.remove(shopId);
            System.out.println("Shop with id :" + shopId + " deleted successful");
        } else {
            System.out.println("Shop not exist!");
        }
    }

    public List<Shop> getAllShop() {
        if (shopData.isEmpty()) {
            throw new RuntimeException("Shop list is empty");
        }
        /*StringBuilder shops = new StringBuilder(); --> for readable as case for terminal`s response,
        for (Shop shop : shopData.values()) {            BUT correct using data obj for next user`s requests
            shops.append(shop.toString()).append("\n");
        }
        return shops.toString();*/
        return new ArrayList<>(shopData.values());
    }

    public Shop getById(Long shopID) {
        if (shopID == null) {
            throw new IllegalArgumentException("Shop id cannot be a null!");
        }
        if (!shopData.containsKey(shopID)) {
            throw new IllegalArgumentException("Shop isn`t exist!");
        }
        return shopData.get(shopID);
    }

    public Shop updateShopById(Long id, Shop updatingShop) {
        if (updatingShop == null) {
            throw new IllegalArgumentException("Updating shop cannot be a null");
        }
        ShopService shopService = new ShopService();
        Shop updatedShop = shopService.getById(id);
        updatedShop.setCity(updatingShop.getCity());
        updatedShop.setStreet(updatingShop.getStreet());
        updatedShop.setShopName(updatingShop.getShopName());
        updatedShop.setEmployeesCount(updatingShop.getEmployeesCount());
        updatedShop.setHasWebsite(updatingShop.isHasWebsite());
        return updatedShop;
    }
}
