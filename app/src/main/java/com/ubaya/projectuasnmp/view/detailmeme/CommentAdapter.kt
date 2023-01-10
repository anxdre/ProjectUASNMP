package com.ubaya.projectuasnmp.view.detailmeme

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.projectuasnmp.R
import com.ubaya.projectuasnmp.data.model.Comment
import kotlinx.android.synthetic.main.layout_comment_card.view.*

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    private val dataset = mutableListOf<Comment.Data>()

    fun setNewDataSet(newData: MutableList<Comment.Data>) {
        if (newData.isNotEmpty()) {
            dataset.clear()
            dataset.addAll(newData)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder =
        CommentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_comment_card, parent, false)
        )

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) =
        holder.bindItem(position)

    override fun getItemCount(): Int = dataset.size

    inner class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(position: Int) {
            itemView.tv_value_comment_card.text = dataset[position].comment
            itemView.tv_date_comment_card.text = dataset[position].createdAt
            itemView.tv_username_comment_card.text = dataset[position].username
        }
    }
}