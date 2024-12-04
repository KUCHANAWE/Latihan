package com.example.latihan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class adapterRecView (private val listTask:ArrayList<tasklist>) : RecyclerView.Adapter<adapterRecView.ListViewHolder>(){
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun delData(pos:Int)
        fun editData(pos: Int)
        fun completeTask(pos: Int)

    }
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback=onItemClickCallback
    }


    inner class ListViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var _deskripsi = itemView.findViewById<TextView>(R.id.deskripsi)
        var _judul = itemView.findViewById<TextView>(R.id.namaTask)
        var _tanggal = itemView.findViewById<TextView>(R.id.tanggal)
        var _kategori = itemView.findViewById<TextView>(R.id.kategori)
        var _btnHapus = itemView.findViewById<Button>(R.id.btnHapus)
        var _btnUbah = itemView.findViewById<Button>(R.id.btnEdit)
        var _btnStart = itemView.findViewById<Button>(R.id.btnKerjakan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_recycle,parent,false)
        return  ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listTask.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var task = listTask[position]

        holder._judul.setText(task.judul)
        holder._tanggal.setText(task.tanggal)
        holder._deskripsi.setText(task.deskripsi)
        holder._kategori.setText(task.kategori)
        holder._btnHapus.setOnClickListener{
            onItemClickCallback.delData(position)
        }
        holder._btnUbah.setOnClickListener{
            onItemClickCallback.editData(position)
        }
        // Tombol Kerjakan/Selesai
        holder._btnStart.text = "START"
        holder._btnStart.setOnClickListener {
            if (holder._btnStart.text == "START") {
                holder._btnStart.text = "SELESAI"
                holder._btnUbah.isEnabled = false // Nonaktifkan tombol edit
            } else {
                onItemClickCallback.completeTask(position) // Hapus task
            }
        }
    }
}