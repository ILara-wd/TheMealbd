package mx.aplazo.themeal.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import mx.aplazo.themeal.R

object Tools {

    fun Context.showImage(urlImage: String, imageView: ImageView) {
        Glide.with(this)
            .load(urlImage)
            .placeholder(R.drawable.placeholder)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)
    }

    fun Activity.shareSource(source: String?) {
        if (source.isNullOrEmpty()) {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, source);
            startActivity(Intent.createChooser(shareIntent, "Compartir"))
        }
    }

    fun Activity.openSource(source: String?) {
        if (source.isNullOrEmpty()) {
            val intentSource = Intent(Intent.ACTION_VIEW)
            intentSource.data = Uri.parse(source)
            startActivity(intentSource)
        }
    }

    infix fun <T : Any> Boolean.ternary(value: T): T? = if (this) value else null

}