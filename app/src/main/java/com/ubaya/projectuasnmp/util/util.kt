package com.ubaya.projectuasnmp.util

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.datetime.datePicker
import com.afollestad.materialdialogs.datetime.selectedDate
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import com.ubaya.projectuasnmp.R
import java.util.*

fun fetchImageFromNetwork(url:String,iv:ImageView){
    Picasso.get().load(url).placeholder(R.drawable.ic_baseline_broken_image_24).resize(1280,720).centerCrop().into(iv)
}


fun viewVisible(view: View) {
    view.visibility = View.VISIBLE
}

fun viewInvisible(view: View) {
    view.visibility = View.INVISIBLE
}

fun viewGone(view: View) {
    view.visibility = View.GONE
}

fun viewAnimate(context: Context, view: View, animXml: Int) {
    view.startAnimation(
        AnimationUtils.loadAnimation(
            context, animXml
        )
    )
}

fun showSortToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun showLongToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun showSortSnackBar(parentView: View, message: String) {
    Snackbar.make(parentView, message, Snackbar.LENGTH_SHORT).show()
}

fun showLongSnackBar(parentView: View, message: String) {
    Snackbar.make(parentView, message, Snackbar.LENGTH_LONG).show()
}

fun <T> baseSpinnerAdapter(context: Context, listOfData: Array<T>): ArrayAdapter<T> =
    ArrayAdapter(
        context,
        android.R.layout.simple_spinner_dropdown_item,
        android.R.id.text1,
        listOfData
    )

fun showDatePicker(context: Context, action: (date: Calendar) -> Unit) {
    MaterialDialog(context).datePicker().show {
        positiveButton {
            action(it.selectedDate())
        }
    }
}

fun showLoadingUi(context: Context): MaterialDialog {
    return MaterialDialog(context).customView(R.layout.layout_loading).noAutoDismiss()
        .cancelable(false).cornerRadius(16F)
}


