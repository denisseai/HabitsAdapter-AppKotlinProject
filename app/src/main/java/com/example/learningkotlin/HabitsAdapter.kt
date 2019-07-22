package com.example.learningkotlin

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.single_card.view.*

class HabitsAdapter(val habits: List<Habit>): RecyclerView.Adapter<HabitsAdapter.HabitViewHolder>() {
    class HabitViewHolder(val iv: View) : RecyclerView.ViewHolder(iv)
    //Specifies the contents for the shown habit
    override fun onBindViewHolder(holder: HabitViewHolder, index: Int) {
        if(holder!= null) {
            val habit = habits[index]
            holder.iv.tv_title.text = habit.title
            holder.iv.tv_description.text = habit.description
            holder.iv.iv_icon.setImageResource(habit.image)
        }

    }
    //Create a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_card, parent, false)
        return HabitViewHolder(view)
    }
    override fun getItemCount() = habits.size
}