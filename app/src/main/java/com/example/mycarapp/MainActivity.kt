package com.example.mycarapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvCars: RecyclerView
    private val list = ArrayList<Car>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvCars = findViewById(R.id.rv_cars)
        rvCars.setHasFixedSize(true)

        list.addAll(getListCars())
        showRecyclerList()
    }

    private fun getListCars(): ArrayList<Car> {

        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataSpecification = resources.getStringArray(R.array.data_specification)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listCar = ArrayList<Car>()
        for (i in dataName.indices) {
            val car = Car(dataName[i], dataDescription[i], dataSpecification[i], dataPhoto.getResourceId(i, -1))
            listCar.add(car)
        }
        return listCar
    }

    private fun showRecyclerList(){
        rvCars.layoutManager = LinearLayoutManager(this)
        val listCarAdapter = ListCarAdapter(list)
        rvCars.adapter = listCarAdapter

        listCarAdapter.setOnItemClickCallback(object : ListCarAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Car){
                val intentToDetail = Intent(this@MainActivity, DetailActivity::class.java)
                intentToDetail.putExtra("DATA", data)
                startActivity(intentToDetail)
            }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_about -> {
                val intentToAbout = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(intentToAbout)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


// TEMPLATE
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        val intentToAbout = Intent(this@MainActivity, AboutActivity::class.java)
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return super.onCreateOptionsMenu(menu)
//    }

}