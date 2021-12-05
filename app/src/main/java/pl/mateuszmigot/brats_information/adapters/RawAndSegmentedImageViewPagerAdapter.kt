package pl.mateuszmigot.brats_information.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
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
import java.util.*

class RawAndSegmentedImageViewPagerAdapter(
    private var context: Context, private var rawImages: List<List<Bitmap>>,
    private var segmentedImages: List<Bitmap>
) : PagerAdapter() {

    private lateinit var modalityButton: Button
    private lateinit var rawImageView: ImageView
    private lateinit var segmentedImageView: ImageView
    private var layoutInflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    private var t1Images = rawImages[0]
    private var t1cImages = rawImages[1]
    private var t2Images = rawImages[2]
    private var flairImages = rawImages[3]

    @NonNull
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = layoutInflater.inflate(R.layout.raw_segmented_image_view, container, false)
        modalityButton = itemView.findViewById(R.id.modalityButton) as Button
        rawImageView = itemView.findViewById(R.id.rawImageView) as ImageView
        segmentedImageView = itemView.findViewById(R.id.segmentedImageView) as ImageView
        setupButtonOnClick(modalityButton, position)
        rawImageView.setImageBitmap(t1Images[position])
        segmentedImageView.setImageBitmap(segmentedImages[position])
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

    private fun setupButtonOnClick(button: Button, position: Int) {
        button.setOnClickListener { v: View ->
            showMenu(v, R.menu.modality_menu, position)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showMenu(view: View, @MenuRes menuRes: Int, position: Int) {
        val popup = PopupMenu(context, view)
        popup.menuInflater.inflate(menuRes, popup.menu)
        popup.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.modality_t1_item -> {
                    rawImageView.setImageBitmap(t1Images[position])
                    modalityButton.text = "t1"
                    true
                }
                R.id.modality_t1c_item -> {
                    rawImageView.setImageBitmap(t1cImages[position])
                    modalityButton.text = "t1ce"
                    true
                }
                R.id.modality_t2_item -> {
                    rawImageView.setImageBitmap(t2Images[position])
                    modalityButton.text = "t2"
                    true
                }
                R.id.modality_flair_item -> {
                    rawImageView.setImageBitmap(flairImages[position])
                    modalityButton.text = "flair"
                    true
                }
                else -> false
            }
        }
        popup.show()
    }
}