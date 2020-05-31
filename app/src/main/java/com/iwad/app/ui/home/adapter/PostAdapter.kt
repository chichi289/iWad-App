package com.iwad.app.ui.home.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iwad.app.R
import com.iwad.app.api.model.Post
import com.iwad.app.extentions.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.raw_post.view.*

class PostAdapter(
    private val postList: ArrayList<Post>,
    private val postCallBack: (Post) -> Unit
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder =
        PostViewHolder(parent.inflate(R.layout.raw_post))

    override fun getItemCount(): Int = postList.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) =
        holder.bindData(postList[position])


    inner class PostViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindData(post: Post) {
            containerView.apply {
                textViewPostTitle.text = post.title
                textViewPostDescription.text = post.body
                setOnClickListener {
                    postCallBack.invoke(post)
                }
            }
        }

    }
}