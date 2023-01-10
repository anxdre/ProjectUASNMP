package com.ubaya.projectuasnmp.view.home.home

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
import com.ubaya.projectuasnmp.view.creatememe.CreateMemeActivity
import com.ubaya.projectuasnmp.view.detailmeme.DetailMeme
import com.ubaya.projectuasnmp.view.home.MainActivity
import com.ubaya.projectuasnmp.view.home.MemeAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private val activityParent by lazy { activity as MainActivity }
    private lateinit var rvAdapter: MemeAdapter
    private val postRepo by lazy { PostRepository() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fab_add_main.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    CreateMemeActivity::class.java
                )
            )
        }

        rvAdapter = MemeAdapter(
            userId = activityParent.userData.idUser!!,
            onClickEvent = {
                val intent = Intent(requireContext(), DetailMeme::class.java)
                intent.putExtra("postData", it)
                startActivity(intent)
            },
            onLikeEvent = { data, position ->
                addLikeToPost(data.idPost!!, position)
            })
        rv_home.adapter = rvAdapter

        sr_home.startRefreshing()
        fetchDataFromNetwork()
        sr_home.setOnRefreshListener { fetchDataFromNetwork() }
    }

    private fun fetchDataFromNetwork() {
        activityParent.volley.add(
            postRepo.getAllPost(
                idUser = activityParent.userData.idUser!!,
                onSuccess = {
                    rvAdapter.setNewDataSet(Gson().fromJson(it, Post::class.java).data)
                    try {
                        sr_home.stopRefreshing()
                    } catch (ex: Exception) {

                    }
                },
                onFailed = {
                    showSortToast(requireContext(), "Error contacting server")
                    try {
                        sr_home.stopRefreshing()
                    } catch (ex: Exception) {

                    }
                }
            )
        )

    }

    private fun addLikeToPost(idPost: String, position: Int) {
        activityParent.volley.add(
            postRepo.addLike(
                idUser = activityParent.userData.idUser!!,
                idPost = idPost,
                onSuccess = {
                    val result = Gson().fromJson(it, Post::class.java)
                    rvAdapter.changeLikeState(position)
                    showSortToast(requireContext(), result.msg.toString())
                }
            ) {
                showSortToast(requireContext(), "Error contacting server")
            }
        )

    }


    override fun onResume() {
        super.onResume()
        sr_home.startRefreshing()
        fetchDataFromNetwork()
    }

}