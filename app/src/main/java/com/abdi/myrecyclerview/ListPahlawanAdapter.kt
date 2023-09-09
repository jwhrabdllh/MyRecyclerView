package com.abdi.myrecyclerview

import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListPahlawanAdapter(private val listPahlawan: ArrayList<Pahlawan>) : RecyclerView.Adapter<ListPahlawanAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallBack: OnItemClickCallBack

    fun setOnItemClickCallBack(onItemClickCallback: OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_hero, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val pahlawan = listPahlawan[position]

        // Glide sebuah library untuk menampilkan foto secara asynchronous dari URL/Drawable yang diberikan.
        Glide.with(holder.itemView.context) // with: untuk memasukkan context.
            .load(pahlawan.photo)  // load: memasukkan sumber gambar, dari URL atau drawable.
            .apply(RequestOptions().override(55, 55))  // apply: memanipulasi gambar, RequestOptions utk meng-override ukuran gambar.
            .into(holder.imgPhoto)  // into: memasukkan imageView sebagai objek penampil gambar.

        holder.tvName.text = pahlawan.name
        holder.tvDetail.text = pahlawan.detail

        holder.itemView.setOnClickListener {
            onItemClickCallBack.onItemClicked(listPahlawan[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return listPahlawan.size
    }

    /** class ListViewHolder harus inherit ke RecyclerView dan ViewHolder */
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /** meng-inisialisasi view yang ada di dalam layout item_row_hero */
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvDetail: TextView = itemView.findViewById(R.id.tv_item_detail)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
    }

    interface OnItemClickCallBack {
        fun onItemClicked(data: Pahlawan)
    }
}