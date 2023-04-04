package com.example.hw7.controller;

import com.example.hw7.model.apiResponse.JsonRestResponse;
import com.example.hw7.model.Shop;
import com.example.hw7.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shops")
public class ShopRestController {

    public ShopRestController(ShopService shopService) {
        this.shopService = shopService;
    }

    @Autowired
    public ShopService shopService;

    /*we just add new obj. If a user need to use added obj in this controller
      should return created obj. Depends on architecture*/
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonRestResponse> createShop(@RequestBody Shop shop) {
        try {
            shopService.addShop(shop);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new JsonRestResponse(true, "Shop has been created successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new JsonRestResponse(false, "Invalid request", null));
        }
    }

    @DeleteMapping(value = "/delete/{shopID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonRestResponse> deleteShop(@PathVariable("shopID") Long shopID) {
        try {
            shopService.deleteShopById(shopID);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new JsonRestResponse(true, "Shop has been deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new JsonRestResponse(false, "Shop not found", null));
        }
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonRestResponse> getAllShops () {
        try {
            List<Shop> shopList = shopService.getAllShop();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new JsonRestResponse(true, "Response shop list", shopList));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new JsonRestResponse(false, "Sorry, shop list not exist", null));
        }
    }

    @GetMapping(value = "/{shopID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonRestResponse> getShopById (@PathVariable("shopID") Long shopID) {
        try {
            Shop shop = shopService.getById(shopID);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new JsonRestResponse(true, "Shop find", shop));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new JsonRestResponse(false, "Invalid request", null));
        }
    }

    @PatchMapping(value = "/{shopID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonRestResponse> patchShopById (@PathVariable("shopId") Long shopID,
                                                           @RequestBody Shop shop) {
        try {
            shopService.updateShopById(shopID, shop);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(new JsonRestResponse(true, "Successful changes to shop", shop));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new JsonRestResponse(false, "Invalid request", null));
        }
    }

}
