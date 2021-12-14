package mx.aplazo.themeal

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

object Tools {

    fun Context.showImage(urlImage: String, imageView: ImageView) {
        Glide.with(this)
            .load(urlImage)
            .placeholder(R.drawable.placeholder)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)
    }

}