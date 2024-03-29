package com.example.learningkotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Adapter -> defines data
        //Recycler -> implement 3 methods
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this)
//        rv.adapter = HabitsAdapter(getSampleHabits())

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.add_habit){
            switch(CreateHabitActivity::class.java)
        }
        return true
    }
    private fun switch(c: Class<*>) {
        val intent = Intent(this, c)
        startActivity(intent)
    }
}
