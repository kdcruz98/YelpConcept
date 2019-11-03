package network

import io.reactivex.Observable
import model.Post
import model.PostSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * The interface which provides methods to get result of Yelp business search
 */
interface PostApi {
    /**
     * Get the list of the pots from the API
     * @param term the search term
     * @param location the location for the business search
     * @param limit the limit of responses
     */
    @GET("v3/businesses/search")
    fun getPosts(
        @Query("term") term: String,
        @Query("location") location: String,
        @Query("limit") limit: Int): Observable<PostSearchResponse>
}