package pl.mateuszmigot.brats_information.adapters

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.NonNull
import androidx.viewpager.widget.PagerAdapter
import pl.mateuszmigot.brats_information.R
import java.util.*

class RawAndSegmentedImageViewPagerAdapter(context: Context, private var rawImages: List<Bitmap>,
                                           private var segmentedImages: List<Bitmap>
) : PagerAdapter() {

    private var context: Context = context
    private var layoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    @NonNull
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = layoutInflater.inflate(R.layout.raw_segmented_image_view, container, false)
        val rawImageView = itemView.findViewById(R.id.rawImageView) as ImageView
        val segmentedImageView = itemView.findViewById(R.id.segmentedImageView) as ImageView
        rawImageView.setImageBitmap(rawImages[position])
        segmentedImageView.setImageBitmap(segmentedImages[position])
        Objects.requireNonNull(container).addView(itemView)

        return itemView
    }

    override fun getCount(): Int {
        return rawImages.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}