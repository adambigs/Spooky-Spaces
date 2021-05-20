package learn.spooky.controller;

import learn.spooky.domain.Result;
import learn.spooky.domain.WishListService;
import learn.spooky.models.WishList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/wishlist")
public class WishListController {

    private final WishListService service;

    public WishListController(WishListService service) {
        this.service = service;
    }

    @GetMapping
    public List<WishList> findAll() {
        return service.findAll();
    }

    @GetMapping("/find")
    public ResponseEntity<WishList> findByUsername(@RequestBody String username){
        WishList wishList = service.findByUsername(username);
        if (wishList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(wishList);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody WishList wishList) {
        Result<WishList> result = service.add(wishList);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorHandler.build(result);
    }

    @PutMapping("/{wishlistId}")
    public ResponseEntity<Object> update(@PathVariable int wishListId, @RequestBody WishList wishList) {
        if (wishListId != wishList.getWishListId()) { //make sure wishlist id exists
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<WishList> result = service.update(wishList);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorHandler.build(result);
    }

    @DeleteMapping("/{locationId}")
    public ResponseEntity<Object> deleteById(@PathVariable int locationId,@RequestBody String username) {
        Result<WishList> result = service.deleteByUsername(username, locationId);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorHandler.build(result);
    }
}
