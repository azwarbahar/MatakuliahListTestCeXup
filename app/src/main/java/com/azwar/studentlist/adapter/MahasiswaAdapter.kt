package com.azwar.studentlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azwar.studentlist.data.entities.Mahasiswa
import com.azwar.studentlist.databinding.ItemMahasiswaBinding
import com.azwar.studentlist.databinding.ItemMataKuliahBinding

class MahasiswaAdapter(private var mahasiswas: List<Mahasiswa>) :
    RecyclerView.Adapter<MahasiswaAdapter.MyHolderView>() {
    class MyHolderView(private var binding: ItemMahasiswaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(get: Mahasiswa) {
            with(itemView) {
                binding.tvNama.setText("Nama : ${get.nama ?: "Nama : -"}")
                binding.tvNim.setText("NIM : ${get.nim ?: "NIM : -"}")
            }
        }

    }

    fun filterList(list: List<Mahasiswa>) {
        mahasiswas = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolderView {
        var binding =
            ItemMahasiswaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MahasiswaAdapter.MyHolderView(binding)
    }

    override fun getItemCount() = mahasiswas.size

    override fun onBindViewHolder(holder: MyHolderView, position: Int) =
        holder.bind(mahasiswas.get(position))
}