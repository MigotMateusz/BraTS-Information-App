package pl.mateuszmigot.brats_information.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupMenu
import androidx.annotation.MenuRes
import androidx.annotation.NonNull
import androidx.viewpager.widget.PagerAdapter
import pl.mateuszmigot.brats_information.R
import pl.mateuszmigot.brats_information.activities.GalleryActivity
import java.util.*

class RawAndSegmentedImageViewPagerAdapter(
    private var context: Context,
    rawT1Images: MutableMap<String, Bitmap>,
    rawT1cImages: MutableMap<String, Bitmap>,
    rawT2Images: MutableMap<String, Bitmap>,
    rawFlairImages: MutableMap<String, Bitmap>,
    private var segmentedImages: MutableMap<String, Bitmap>
) : PagerAdapter() {

    private lateinit var modalityButton: Button
    private lateinit var rawImageView: ImageView
    private lateinit var segmentedImageView: ImageView
    private var layoutInflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    private var t1Images = rawT1Images
    private var t1cImages = rawT1cImages
    private var t2Images = rawT2Images
    private var flairImages = rawFlairImages

    @NonNull
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = layoutInflater.inflate(R.layout.raw_segmented_image_view, container, false)
        modalityButton = itemView.findViewById(R.id.modalityButton) as Button
        rawImageView = itemView.findViewById(R.id.rawImageView) as ImageView
        segmentedImageView = itemView.findViewById(R.id.segmentedImageView) as ImageView

        setupButtonOnClick(modalityButton, position, container.context)
        val mapKey= "0${position}.png"
        Log.i("SEGMENTATION LOGS ---->", mapKey)
        rawImageView.setImageBitmap(t1Images[mapKey])
        segmentedImageView.setImageBitmap(segmentedImages[mapKey])
        Objects.requireNonNull(container).addView(itemView)

        return itemView
    }

    override fun getCount(): Int {
        return segmentedImages.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }

    private fun setupButtonOnClick(button: Button, position: Int, context: Context) {
        button.setOnClickListener { v: View ->
            showMenu(v, R.menu.modality_menu, position, context)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showMenu(view: View, @MenuRes menuRes: Int, position: Int, context: Context) {
        val popup = PopupMenu(context, view)
        val mapKey= "0$position.png"
        popup.menuInflater.inflate(menuRes, popup.menu)
        popup.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.modality_t1_item -> {
                    Log.i("ONCLICKMODALITY --->", "T1")
                    rawImageView.setImageBitmap(t1Images[mapKey])
                    modalityButton.text = "t1"
                    true
                }
                R.id.modality_t1c_item -> {
                    Log.i("ONCLICKMODALITY --->", "T1C")
                    rawImageView.setImageBitmap(t1cImages[mapKey])
                    modalityButton.text = "t1ce"
                    true
                }
                R.id.modality_t2_item -> {
                    Log.i("ONCLICKMODALITY --->", "T2")
                    rawImageView.setImageBitmap(t2Images[mapKey])
                    modalityButton.text = "t2"
                    true
                }
                R.id.modality_flair_item -> {
                    Log.i("ONCLICKMODALITY --->", "FLAIR")
                    rawImageView.setImageBitmap(flairImages[mapKey])
                    modalityButton.text = "flair"
                    true
                }
                else -> false
            }
        }
        popup.show()
    }
}