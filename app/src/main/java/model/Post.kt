package model

import com.squareup.moshi.Json

/**
 * Class which provides a model for the business post
 * @constructor Sets all properties of the business post
 * @property id the unique identifier of the business
 * @property name the name of the business
 * @property reviewCount the number of reviews/ratings
 * @property location the location of the business
 */
data class Post(
    val id: String,
    val name: String,
    @Json(name = "review_count") val reviewCount: String,
    val location: Location
)

/**
 * Class which defines the location/address of a business
 * @constructor Sets all properties of the business location
 * @property address1 the first address field
 * @property address2 the second address field
 * @property address3 the third address field
 * @property city the city in which the business is located
 * @property zip_code the zip code in which the business is located
 * @property country the country in which the business is located
 * @property state the state in which the business is located
 */
data class Location(
    val address1: String?,
    val address2: String?,
    val address3: String?,
    val city: String,
    val zip_code: String,
    val country: String,
    val state: String
)
