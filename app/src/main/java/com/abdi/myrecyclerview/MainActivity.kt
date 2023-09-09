package com.abdi.myrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvPahlawan: RecyclerView
    private var list: ArrayList<Pahlawan> = arrayListOf()
    private var title: String = "Mode List"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvPahlawan = findViewById(R.id.rv_pahlawan)
        // FixedSize: RecyclerView dapat melakukan optimasi ukuran lebar dan tinggi secara otomatis
        rvPahlawan.setHasFixedSize(true)
        list.addAll(DataPahlawan.listData)

        setActionBarTitle(title)
        showRecyclerList()
        showRecyclerGrid()
        showRecyclerCardView()
    }

    private fun showSelectedPahlawan(pahlawan: Pahlawan) {
        Toast.makeText(this, "Kamu memilih " + pahlawan.name, Toast.LENGTH_SHORT).show()
    }

    private fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun showRecyclerList() {
        rvPahlawan.layoutManager = LinearLayoutManager(this)
        val listPahlawanAdapter = ListPahlawanAdapter(list)
        rvPahlawan.adapter = listPahlawanAdapter

        listPahlawanAdapter.setOnItemClickCallBack(object : ListPahlawanAdapter.OnItemClickCallBack {
            override fun onItemClicked(data: Pahlawan) {
                showSelectedPahlawan(data)
            }
        })
    }

    private fun showRecyclerGrid() {
        rvPahlawan.layoutManager = GridLayoutManager(this, 2)
        val gridPahlawanAdapter = GridPahlawanAdapter(list)
        rvPahlawan.adapter = gridPahlawanAdapter

        gridPahlawanAdapter.setOnItemClickCallback(object : GridPahlawanAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Pahlawan) {
                showSelectedPahlawan(data)
            }
        })
    }

    private fun showRecyclerCardView() {
        rvPahlawan.layoutManager = LinearLayoutManager(this)
        val cardViewPahlawanAdapter = CardViewPahlawanAdapter(list)
        rvPahlawan.adapter = cardViewPahlawanAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_list -> {
                title = "Mode List"
                showRecyclerList()
            }
            R.id.action_grid -> {
                title = "Mode Grid"
                showRecyclerGrid()
            }
            R.id.action_cardview -> {
                title = "Mode CardView"
                showRecyclerCardView()
            }
        }
        setActionBarTitle(title)
    }
}