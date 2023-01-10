package com.ubaya.projectuasnmp.view.home

import Post
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.projectuasnmp.R
import com.ubaya.projectuasnmp.util.fetchImageFromNetwork
import kotlinx.android.synthetic.main.layout_post_card.view.*

class MemeAdapter(
    private val onClickEvent: (Post.Data) -> Unit,
    private val onLikeEvent: (Post.Data, position: Int) -> Unit,
    private val userId: String,
) : RecyclerView.Adapter<MemeAdapter.MemeAdapterViewHolder>() {

    private val dataset = ArrayList<Post.Data>()

    fun setNewDataSet(newData: MutableList<Post.Data>) {
        if (newData.isNotEmpty()) {
            dataset.clear()
            dataset.addAll(newData)
        }
        notifyDataSetChanged()
    }

    fun changeLikeState(position: Int) {
        dataset[position].liked = "true"
        val totalLikeTemp = dataset[position].totalLike!!.toInt()
        dataset[position].totalLike = (totalLikeTemp + 1).toString()
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemeAdapterViewHolder =
        MemeAdapterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_post_card, parent, false
            )
        )

    override fun onBindViewHolder(holder: MemeAdapterViewHolder, position: Int) =
        if (dataset[position].idUser == userId) holder.creationBindItem(position)
        else holder.normalBindItem(position)

    override fun getItemCount(): Int = dataset.size

    inner class MemeAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun normalBindItem(position: Int) {
            fetchImageFromNetwork(dataset[position].url ?: "", itemView.iv_meme_card)
            itemView.tv_like_card.text = "${dataset[position].totalLike} likes"
            itemView.tv_comment_card.text = "${dataset[position].totalComment} Comment"
            itemView.tv_title_top_card.text = dataset[position].topText
            itemView.tv_title_bottom_card.text = dataset[position].bottomText
            itemView.tv_date_card.text = dataset[position].createdAt

            itemView.setOnClickListener { onClickEvent(dataset[position]) }


            if (dataset[position].liked == "false") {
                itemView.tv_like_card.compoundDrawableTintList = ColorStateList.valueOf(Color.BLACK)
                itemView.tv_like_card.setOnClickListener {
                    onLikeEvent(
                        dataset[position],
                        position
                    )
                }
            } else {
                itemView.tv_like_card.compoundDrawableTintList = ColorStateList.valueOf(Color.RED)
                itemView.tv_like_card.isClickable = false
                itemView.tv_like_card.isEnabled = false
            }
        }

        fun creationBindItem(position: Int) {
            fetchImageFromNetwork(dataset[position].url ?: "", itemView.iv_meme_card)
            itemView.tv_like_card.text = "${dataset[position].totalLike} likes"
            itemView.tv_comment_card.text = "${dataset[position].totalComment} Comment"
            itemView.tv_title_top_card.text = dataset[position].topText
            itemView.tv_title_bottom_card.text = dataset[position].bottomText
            itemView.tv_date_card.text = dataset[position].createdAt

            itemView.setOnClickListener { onClickEvent(dataset[position]) }
            itemView.tv_like_card.compoundDrawableTintList = ColorStateList.valueOf(Color.RED)
            itemView.tv_like_card.isClickable = false
            itemView.tv_like_card.isEnabled = false
        }
    }

}
