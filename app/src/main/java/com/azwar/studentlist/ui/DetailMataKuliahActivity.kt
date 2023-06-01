package com.azwar.studentlist.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.azwar.studentlist.adapter.MahasiswaAdapter
import com.azwar.studentlist.data.entities.Mahasiswa
import com.azwar.studentlist.data.entities.MataKuliah
import com.azwar.studentlist.databinding.ActivityDetailMataKuliahBinding
import com.azwar.studentlist.viewmodel.DetailViewModel

class DetailMataKuliahActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMataKuliahBinding

    private lateinit var mahasiswaAdapter: MahasiswaAdapter

    private lateinit var detailViewModel: DetailViewModel

    private lateinit var mataKuliah: MataKuliah

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMataKuliahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        mataKuliah = intent.getParcelableExtra("matakuliah")!!
        setUpView(mataKuliah)

    }

    private fun setUpView(mataKuliah: MataKuliah) {

        binding.tvNamaMataKuliah.setText("Nama : ${mataKuliah.nama ?: "Nama : -"}")

        //dosen
        binding.tvNamaDosen.setText("Nama : ${mataKuliah.dosen?.nama ?: "Nama : -"}")
        binding.tvNidDosen.setText("NID : ${mataKuliah.dosen?.nid ?: "NID : -"}")

        binding.rvMahasiswa.layoutManager = LinearLayoutManager(this)
        mahasiswaAdapter = MahasiswaAdapter(mataKuliah.mahasiswa!!)
        binding.rvMahasiswa.adapter = mahasiswaAdapter

        // data to viewmodel
        detailViewModel.setDataList(mataKuliah.mahasiswa)

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val query = s.toString().trim()
                detailViewModel.filterMahasiswa(query)
                updateMahasiswaList(detailViewModel.getFilteredMahasiswaList())
            }

            override fun afterTextChanged(s: Editable?) {
                val searchQuery = s.toString().trim()
//
//                detailViewModel.mahasiswa = if (searchQuery.isNotEmpty()) ({
//                    val filteredByNama = detailViewModel.getMahasiswaList().filter {
//                        it.nama?.contains(searchQuery, ignoreCase = true) ?:
//                    }
//
//                    val filteredByNim = detailViewModel.getMahasiswaList().filter {
//                        it.nim?.contains(searchQuery, ignoreCase = true) ?:
//                    }
//
//                    filteredByNama.union(filteredByNim)
//                }).toList() else {
//                    viewModel.getMahasiswaList()
//                }
//
//                mahasiswaAdapter.setMahasiswaList(filteredList)
//                if (searchQuery.isNotEmpty()) {
//                    detailViewModel.searchMahasiswa(searchQuery)
//                } else {
//                    detailViewModel.clearSearch()
//                }
            }
        })


    }

    private fun updateMahasiswaList(mahasiswaList: List<Mahasiswa>) {
        mahasiswaAdapter.filterList(mahasiswaList)
    }

}