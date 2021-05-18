package learn.spooky.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class WishList {

    //Basic validations, the variables cannot be null/empty
    @Min(value = 0, message = "Wish list id must be positive")
    private int wishListId;

    @NotBlank(message = "username is required for wish list")
    private String username;

    public WishList() {
    }

    public WishList(@Min(value = 0, message = "Wish list id must be positive") int wishListId, @NotBlank(message = "username is required for wish list") String username) {
        this.wishListId = wishListId;
        this.username = username;
    }

    public int getWishListId() {
        return wishListId;
    }

    public void setWishListId(int wishListId) {
        this.wishListId = wishListId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
