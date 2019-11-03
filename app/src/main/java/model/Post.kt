package model

import com.squareup.moshi.Json

/**
 * Class which provides a model for post
 * @constructor Sets all properties of the post
 * @property id the unique identifier of the post
 * @property title the title of the post
 * @property body the content of the post
 */
data class Post(
    val id: String,
    val name: String,
    @Json(name = "review_count") val reviewCount: String,
    @Json(name = "image_url") val imageUrl: String,
    val location: Location
)

data class Location(
    val address1: String?,
    val address2: String?,
    val address3: String?,
    val city: String,
    val zip_code: String,
    val country: String,
    val state: String
)
