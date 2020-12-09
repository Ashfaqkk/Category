package com.example.ashfaq_iroid.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.ashfaq_iroid.R
import com.example.ashfaq_iroid.model.CategoryListResponseModel
import kotlinx.android.synthetic.main.item_all_category.view.*

class AdapterCategoryList(
    var context: Context,
    private var callback: CategoryItemClick,
    var mList: ArrayList<CategoryListResponseModel.Category?>
) :
    RecyclerView.Adapter<AdapterCategoryList.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(context, callback, mList[position])
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_all_category, parent, false)
        return ViewHolder(view)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun onBind(
            context: Context,
            callback: CategoryItemClick,
            data: CategoryListResponseModel.Category?
        ) {

            var requestOptions = RequestOptions()
            requestOptions =
                requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.ic_image_placeholder)
                    .placeholder(R.drawable.ic_image_placeholder)

            itemView.tvCategory.text = data?.name
            Glide.with(context)
                .load(data?.image)
                .apply(requestOptions)
                .into(itemView.ivCategory)

            itemView.setOnClickListener {

                callback.onCategoryItemClick(position = adapterPosition)

            }

        }
    }

}
interface CategoryItemClick {
    fun onCategoryItemClick(position: Int)
}