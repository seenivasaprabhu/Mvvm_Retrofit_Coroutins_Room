package com.seenu.thapovan.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter

//Bind the data with view with bindview adapter
@BindingAdapter("app:setText","app:setColor")
    fun formatText(textView: TextView, txt:String, txtColor:Int){
       textView.text = txt
       textView.setTextColor(txtColor)
    }

