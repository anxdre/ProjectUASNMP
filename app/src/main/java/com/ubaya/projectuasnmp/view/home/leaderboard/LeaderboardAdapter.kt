package com.ubaya.projectuasnmp.view.home.leaderboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.projectuasnmp.R
import com.ubaya.projectuasnmp.data.model.Leaderboard
import kotlinx.android.synthetic.main.layout_leaderboard_item.view.*

class LeaderboardAdapter : RecyclerView.Adapter<LeaderboardAdapter.LeaderboardHolder>() {
    private val dataSet = mutableListOf<Leaderboard.Data>()

    fun setNewDataSet(newData: MutableList<Leaderboard.Data>) {
        if (newData.isNotEmpty()) {
            dataSet.clear()
            dataSet.addAll(newData)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardHolder =
        LeaderboardHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_leaderboard_item, parent, false
            )
        )

    override fun onBindViewHolder(holder: LeaderboardHolder, position: Int) =
        holder.bindItem(position)

    override fun getItemCount(): Int = dataSet.size

    inner class LeaderboardHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(position: Int) {
            itemView.tv_username_leader.text = dataSet[position].username
            itemView.tv_like_leader.text = dataSet[position].totalLike

            if (dataSet[position].isHidden == "1") {
                var hiddenName = ""
                for (i in 0..dataSet[position].username!!.length / 2) {
                    hiddenName += "*"
                }
                for (i in dataSet[position].username!!.length / 2..dataSet[position].username!!.length) {
                    hiddenName += dataSet[position].username!![i]
                }
                itemView.tv_username_leader.text = hiddenName
            }
        }
    }
}
