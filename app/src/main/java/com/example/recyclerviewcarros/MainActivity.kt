package com.example.recyclerviewcarros

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var carroAdapter: CarroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        addDataSource()

    }

    private fun addDataSource() {

        val dataSource = DataSource.createDataSet()
        this.carroAdapter.setDataset(dataSource)
    }

    private fun initRecyclerView() {

        this.carroAdapter = CarroAdapter { live ->
            openLink(live.link)
        }

        // Disposição dos items no recycelrView que no caso aqui é Linear
        recyclerview.apply {
            recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerview.adapter = carroAdapter
        }
    }

    private fun openLink(url : String) {

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)

    }
}