package com.pascal.roomapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pascal.roomapp.model.Siswa
import com.pascal.roomapp.R
import kotlinx.android.synthetic.main.item_note.view.*

class SiswaAdapter(
    private val data: List<Siswa>?,
    private val itemClick: OnClickListener
) : RecyclerView.Adapter<SiswaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiswaAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data?.get(position)

        holder.bind(item)
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Siswa?) {
            view.item_nama.text = item?.nama
            view.item_nohp.text = item?.nohp
            view.item_alamat.text = item?.alamat

            view.setOnClickListener{
                itemClick.update(item)
            }

            view.btn_itemDelete.setOnClickListener {
                itemClick.delete(item)
            }
        }
    }

    interface OnClickListener {
        fun update(item: Siswa?)
        fun delete(item: Siswa?)
    }
}


