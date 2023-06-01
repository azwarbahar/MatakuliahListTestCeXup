package com.azwar.studentlist.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azwar.studentlist.data.entities.MataKuliah
import com.azwar.studentlist.databinding.ItemMataKuliahBinding
import com.azwar.studentlist.ui.DetailMataKuliahActivity

class MatakuliahAdapter(private var matakuliahs: List<MataKuliah>) :
    RecyclerView.Adapter<MatakuliahAdapter.MyHolderView>() {
    class MyHolderView(private var binding: ItemMataKuliahBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(get: MataKuliah) {
            with(itemView) {

                binding.tvNama.setText(get.nama)

                itemView.setOnClickListener {
                    val intent = Intent(context, DetailMataKuliahActivity::class.java)
                    intent.putExtra("matakuliah", get)
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolderView {
        var binding =
            ItemMataKuliahBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MatakuliahAdapter.MyHolderView(binding)
    }

    override fun getItemCount() = matakuliahs.size

    override fun onBindViewHolder(holder: MyHolderView, position: Int) =
        holder.bind(matakuliahs.get(position))
}