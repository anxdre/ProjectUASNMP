package com.ubaya.projectuasnmp.view.home.mycreation

import Post
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.ubaya.projectuasnmp.R
import com.ubaya.projectuasnmp.data.api.repositories.PostRepository
import com.ubaya.projectuasnmp.util.showSortToast
import com.ubaya.projectuasnmp.view.detailmeme.DetailMeme
import com.ubaya.projectuasnmp.view.home.MainActivity
import com.ubaya.projectuasnmp.view.home.MemeAdapter
import kotlinx.android.synthetic.main.fragment_my_creation.*

class MyCreationFragment : Fragment() {
    private val activityParent by lazy { activity as MainActivity }
    private lateinit var rvAdapter: MemeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_creation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvAdapter = MemeAdapter(
            onClickEvent = {
                val intent = Intent(requireContext(), DetailMeme::class.java)
                intent.putExtra("postData", it)
                startActivity(intent)
            },
            onLikeEvent = { _, _ -> },
            userId = activityParent.userData.idUser!!
        )
        rv_creation.adapter = rvAdapter

        sr_creation.setOnRefreshListener { fetchDataFromNetwork() }
        sr_creation.startRefreshing()
        fetchDataFromNetwork()
    }

    private fun fetchDataFromNetwork() {
        activityParent.volley.add(
            PostRepository().getByUser(
                idUser = activityParent.userData.idUser!!,
                onSuccess = {
                    rvAdapter.setNewDataSet(Gson().fromJson(it, Post::class.java).data)
                    try {
                        sr_creation.stopRefreshing()
                    } catch (ex: Exception) {

                    }
                },
                onFailed = {
                    showSortToast(requireContext(), "Error contacting server")
                    try {
                        sr_creation.stopRefreshing()
                    } catch (ex: Exception) {

                    }
                }
            )
        )

    }
}