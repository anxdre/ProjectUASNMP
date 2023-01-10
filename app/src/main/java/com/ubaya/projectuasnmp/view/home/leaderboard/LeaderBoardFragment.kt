package com.ubaya.projectuasnmp.view.home.leaderboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.ubaya.projectuasnmp.R
import com.ubaya.projectuasnmp.data.api.repositories.LeaderboardRepository
import com.ubaya.projectuasnmp.data.model.Leaderboard
import com.ubaya.projectuasnmp.util.showSortToast
import com.ubaya.projectuasnmp.view.home.MainActivity
import kotlinx.android.synthetic.main.fragment_leader_board.*

class LeaderBoardFragment : Fragment() {
    private val activityParent by lazy { activity as MainActivity }
    private lateinit var rvAdapter: LeaderboardAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leader_board, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvAdapter = LeaderboardAdapter()
        rv_leaderboard.adapter = rvAdapter
        fetchDataFromNetwork()
    }

    private fun fetchDataFromNetwork() {
        sr_lead.startRefreshing()
        activityParent.volley.add(
            LeaderboardRepository().getLeaderboard(
                onSuccess = {
                    try {
                        rvAdapter.setNewDataSet(Gson().fromJson(it, Leaderboard::class.java).data!!)
                        sr_lead.stopRefreshing()
                    } catch (ex: Exception) {

                    }
                },
                onFailed = {
                    showSortToast(requireContext(), "Error contacting server")
                    try {
                        sr_lead.stopRefreshing()
                    } catch (ex: Exception) {

                    }
                }
            )
        )

    }

}