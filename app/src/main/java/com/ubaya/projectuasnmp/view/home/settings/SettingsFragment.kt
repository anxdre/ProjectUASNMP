package com.ubaya.projectuasnmp.view.home.settings

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ubaya.projectuasnmp.R
import com.ubaya.projectuasnmp.data.SharedPrefHelper
import com.ubaya.projectuasnmp.data.api.repositories.AuthRepository
import com.ubaya.projectuasnmp.util.showLoadingUi
import com.ubaya.projectuasnmp.util.showSortToast
import com.ubaya.projectuasnmp.view.auth.login.LoginActivity
import com.ubaya.projectuasnmp.view.home.MainActivity
import com.wajahatkarim3.easyvalidation.core.collection_ktx.nonEmptyList
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {
    private val activityParent by lazy { activity as MainActivity }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loadingUi = showLoadingUi(requireContext())

        //TODO::UPLOAD IMG TO SERVER
//        if (activityParent.userData.avatar != null) {
//            iv_photo_settings.setImageURI(activityParent.userData.avatar!!.toUri())
//        }

        iv_photo_settings.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 100)
        }

        cb_hidename_settings.setOnCheckedChangeListener { compoundButton, b ->
            if (compoundButton.isChecked){
                activityParent.userData.isHidden = "1"
            }else{
                activityParent.userData.isHidden = "0"
            }
        }

        btn_save_settings.setOnClickListener {
            loadingUi.show()
            if (validateField()) {
                activityParent.volley.add(
                    AuthRepository().editProfile(activityParent.userData,
                        onSuccess = {
                            SharedPrefHelper(requireContext()).storeUser(
                                activityParent.userData
                            )
                            showSortToast(requireContext(), "Success")
                            loadingUi.dismiss()
                        },
                        onFailed = {
                            showSortToast(requireContext(), "Error contacting server")
                            loadingUi.dismiss()
                        })
                )
            }
        }

        tv_fullname_settings.text =
            "${activityParent.userData.firstname} ${activityParent.userData.lastname}"
        tv_created_settings.text = "Active member since ${activityParent.userData.createdAt}"
        tv_username_settings.text = "${activityParent.userData.username}"
        et_firstname_settings.setText("${activityParent.userData.firstname}")
        et_lastname_settings.setText("${activityParent.userData.lastname}")
        cb_hidename_settings.isChecked = activityParent.userData.isHidden != "0"

        fab_logout_setting.setOnClickListener {
            SharedPrefHelper(requireContext()).deleteUser()
            requireActivity().startActivity(Intent(requireContext(), LoginActivity::class.java))
            requireActivity().finish()
        }
    }

    private fun validateField(): Boolean {
        return nonEmptyList(
            et_firstname_settings,
            et_lastname_settings
        ) { view, _ ->
            view.error = "Field cannot be empty"
            view.requestFocus()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 100) {
            iv_photo_settings.setImageURI(data?.data)
        }
    }

}