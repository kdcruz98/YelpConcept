package ui.post

import android.view.View
import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.yelpconcept.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import model.PostSearchResponse
import network.PostApi
import javax.inject.Inject

/**
 * This class calls the Yelp API, initialises the data subscription and handles list data
 */
class PostListViewModel: BaseViewModel() {
    @Inject
    lateinit var postApi: PostApi
    val postListAdapter: PostListAdapter = PostListAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }

    private lateinit var subscription: Disposable

    init{
        loadPosts()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    /**
     * Create a subscription for the Yelp API
     */
    private fun loadPosts(){
        subscription = postApi.getPosts("restaurant", "Brisbane", 10)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                // Add result
                { result -> onRetrievePostListSuccess(result) },
                { onRetrievePostListError() }
            )
    }

    private fun onRetrievePostListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE
    }

    /**
     * Send the search response to the adapter for data binding
     */
    private fun onRetrievePostListSuccess(postList: PostSearchResponse){
        postListAdapter.updatePostList(postList.businesses)
    }

    private fun onRetrievePostListError(){
        errorMessage.value = R.string.post_error
    }
}