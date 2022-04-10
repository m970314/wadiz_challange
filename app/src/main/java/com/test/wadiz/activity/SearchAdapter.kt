package com.test.wadiz.activity

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.wadiz.R
import com.test.wadiz.data.ListData
import com.test.wadiz.databinding.ViewSearchItemBinding
import com.test.wadiz.request.ImageDownloadManager
import okhttp3.internal.notifyAll

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ListDataViewHolder>() {
    private val lectures = mutableListOf<ListData>()
    private val imageloder = ImageDownloadManager
    var onClick: (String) -> Unit  = {}

    fun wholeupdateLectures(lectures: List<ListData>) {
        this.lectures.clear()
        this.lectures.addAll(lectures)
        notifyDataSetChanged()
    }
    fun fundingupdateLectures(lectures: List<ListData>) {
        for(i in lectures.indices) {
            if(lectures.get(i).type.equals("funding")) {
                this.lectures.clear()
                this.lectures.add(lectures.get(i))
            }
            notifyDataSetChanged()
        }
    }
    fun storeupdateLectures(lectures: List<ListData>) {
        for(i in lectures.indices) {
            if(lectures.get(i).type.equals("store")) {
                this.lectures.clear()
                this.lectures.add(lectures.get(i))

            }
            notifyDataSetChanged()
        }
    }
    override fun getItemCount(): Int {
        return lectures.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListDataViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_search_item, parent, false)
        val binding = ViewSearchItemBinding.bind(view)
        return ListDataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListDataViewHolder, position: Int) {
            val lecture = lectures[position]
            imageloder.loadImage(lecture.photoUrl){
                holder.itemView.findViewById<ImageView>(R.id.lectureImage).setImageBitmap(imageloder.imagecache[lecture.photoUrl])
            }
            holder.itemView.findViewById<TextView>(R.id.lectureTitle).text = lecture.title
            holder.itemView.findViewById<TextView>(R.id.lectureType).text = lecture.type
            holder.itemView.findViewById<TextView>(R.id.lecturehash).text = lecture.additionalInfo
            holder.itemView.findViewById<TextView>(R.id.price).text = lecture.price.toString()
            holder.itemView.setOnClickListener { onClick(lecture.landingUrl) }
    }
    class ListDataViewHolder(binding: ViewSearchItemBinding) : RecyclerView.ViewHolder(binding.root) {
    }
}