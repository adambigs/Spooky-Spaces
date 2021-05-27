import { useState, useEffect } from "react";
import { useHistory } from "react-router-dom";

function AddWishlistItem({ username, locationId }) {
	const history = useHistory();

	const handleAdd = (event) => {
		event.preventDefault();
		event.stopPropagation();

		let wishlist = {};
		wishlist["username"] = username;
		wishlist["locationId"] = locationId;
		addWishlist(wishlist);
	};

	const [wishlists, setWishlists] = useState([]);

	useEffect(() => {
		//get the list of all wishlists
		fetch(`http://localhost:8080/api/wishlist/${username}`)
			.then((response) => {
				if (response.status !== 200) {
					console.log(response);
					return Promise.reject("GET didn't work");
				}
				return response.json();
			})
			.then((json) => setWishlists(json))
			.catch(console.log);
	});

	const addFetch = (wishlist) => {
		//add a wishlist
		const init = {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
				Accept: "application/json",
			},
			body: JSON.stringify(wishlist),
		};

		fetch(`http://localhost:8080/api/wishlist/${username}`, init)
			.then((response) => {
				if (response.status !== 201) {
					return Promise.reject("POST doesn't work");
				}
				return response.json();
			})
			.then((json) => {
				setWishlists([...wishlists, json]);
			})
			.then(history.goBack())
			.catch(console.log);
	};

	const addWishlist = (wishlist) => {
		let canSet = true;

		for (let i = 0; i < wishlists.length; i++) {
			//make sure the wishlistId does not already exist
			if (wishlist.wishlistId === wishlists[i].wishlistId) {
				canSet = false;
			}
		}

		if (canSet) {
			addFetch(wishlist); //add wishlist if no errors
		} else {
			console.log("wishlist Id is taken");
		}
	};

	return handleAdd();
}

export default AddWishlistItem;
