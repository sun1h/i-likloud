package com.ssafy.likloud.ui.photolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssafy.likloud.data.model.PhotoListDto
import com.ssafy.likloud.databinding.ItemPhotoBinding
import com.ssafy.likloud.ui.drawinglist.DrawingListAdapter

class PhotoListAdapter (var list : MutableList<PhotoListDto>): ListAdapter<PhotoListDto, PhotoListAdapter.PhotoListHolder>(
    PhotoListComparator
) {
    companion object PhotoListComparator : DiffUtil.ItemCallback<PhotoListDto>() {
        override fun areItemsTheSame(oldItem: PhotoListDto, newItem: PhotoListDto): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PhotoListDto, newItem: PhotoListDto): Boolean {
            return oldItem.photoId  == newItem.photoId
        }
    }
    inner class PhotoListHolder(binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root){
        val imageDrawing = binding.imageDrawing
        fun bindInfo(photo : PhotoListDto){
            Glide.with(imageDrawing)
                .load(photo.photoUrl)
                .into(imageDrawing)
            itemView.setOnClickListener{
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoListHolder {
        val binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return RecyclerView.ViewHolder(inflater)
        return PhotoListHolder(binding)
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PhotoListHolder, position: Int) {
        holder.apply {
            bindInfo(list.get(position))
        }
    }

    fun updateData(list: ArrayList<PhotoListDto>) {
        this.list = list
    }

    //    //클릭 인터페이스 정의 사용하는 곳에서 만들어준다.
    interface ItemClickListener {
        fun onClick(view: View, position: Int, info:String)
    }
    //클릭리스너 선언
    private lateinit var itemClickListner: ItemClickListener
    //클릭리스너 등록 매소드
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListner = itemClickListener
    }
}