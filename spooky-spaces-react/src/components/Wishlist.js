import { useEffect, useState } from "react";
import WishlistItem from "./WishlistItem";
import { useParams } from "react-router-dom";

function Wishlist() {
	const [wishlists, setWishlists] = useState([]); //List of all wishlist
	const { username } = useParams();

	useEffect(() => {
		//get the list of all wishlist
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
	}, []); //NEED BRACKETS - EMPTY!!!

	const deleteItem = (locationId) => {
		let newWishLists = [];

		if (newWishLists.length !== wishlists.length) {
			console.log("Deleted Successfully");
			setWishlists(newWishLists);
		} else {
			console.log("Could not find ID");
		}
	};

	return (
		//map all values to an wishlist
		<div className="container mt-5 text-center">
			<h1 className="pt-3 ml-3">Wishlist</h1>
			{wishlists.map((w) => (
				<WishlistItem
					key={w.wishlistId}
					wishlistId={w.wishlistId}
					username={w.username}
					locationId={w.locationId}
					deleteItem={deleteItem}
				/>
			))}
		</div>
	);
}

export default Wishlist;
