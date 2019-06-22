package com.thernat.starter.binding

import android.view.View
import androidx.databinding.BindingAdapter

/**
 * Created by m.rafalski on 22/06/2019.
 */
@BindingAdapter("visibleGone")
fun View.setVisibleOrGone( isVisible: Boolean){
    visibility = if (isVisible) View.VISIBLE else View.GONE

}