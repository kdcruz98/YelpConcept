package ui.post

import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import model.Post

/**
 * Handles the live string data for business details
 */
class PostViewModel: BaseViewModel() {
    private val postTitle = MutableLiveData<String>()
    private val postBody = MutableLiveData<String>()
    private val postReview = MutableLiveData<String>()

    /**
     * Sets the text value for the business details
     */
    fun bind(post: Post){
        postTitle.value = post.name
        postBody.value = post.location.address1 + ", " + post.location.city
        postReview.value = post.reviewCount + " Reviews"
    }

    fun getPostTitle():MutableLiveData<String>{
        return postTitle
    }

    fun getPostBody():MutableLiveData<String>{
        return postBody
    }

    fun getPostReview():MutableLiveData<String>{
        return postReview
    }
}
