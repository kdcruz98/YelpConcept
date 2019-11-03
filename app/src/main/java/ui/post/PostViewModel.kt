package ui.post

import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import model.Post

class PostViewModel: BaseViewModel() {
    private val postTitle = MutableLiveData<String>()
    private val postBody = MutableLiveData<String>()
    private val postReview = MutableLiveData<String>()


    fun bind(post: Post){
        postTitle.value = post.name
        postBody.value = post.location.address1
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
