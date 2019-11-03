package model

/**
 * Class which defines the model for the search response
 * @constructor Sets all search response
 * @property total the number of businesses returned in the response
 * @property businesses the list of businesses, defined in model.Post
*/
data class PostSearchResponse(val total: Int, val businesses: List<Post>)