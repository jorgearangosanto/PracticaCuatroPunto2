package com.santo.practicacuatro.ui.captura

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.santo.practicacuatro.R
import com.santo.practicacuatro.databinding.CardViewItemLiquorsBinding
import com.santo.practicacuatro.model.Liquor
import com.squareup.picasso.Picasso

class LiquorAdapter (
    private val liquorList:MutableList<Liquor?>,
    private val onItemClicked:(Liquor?)-> Unit,
    //private val onItemLongCliked :(Liquor?)-> Unit,

): RecyclerView.Adapter<LiquorAdapter.liquorViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): liquorViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.card_view_item_liquors,parent,false)
    return liquorViewHolder(view)
    }

    override fun getItemCount(): Int = liquorList.size

    override fun onBindViewHolder(holder: liquorViewHolder, position: Int) {
        val liquor=liquorList[position]
        holder.bind(liquor)
        holder.itemView.setOnClickListener { onItemClicked(liquor) }
       /* holder.itemView.setOnLongClickListener{
            onItemLongCliked(liquor)
            true

        }*/
    }
    fun appendItems(newList: MutableList<Liquor?>){
        liquorList.clear()
        liquorList.addAll(newList)
        notifyDataSetChanged()
    }

    class liquorViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        private val binding = CardViewItemLiquorsBinding.bind(itemView)
        fun bind(liquor: Liquor?){
            with(binding){
                lugarTextview.text=liquor?.lugar
                nombreTextview.text= liquor?.name.toString()
                if (liquor?.url == null){
                    pictureImageView.setImageResource(R.drawable.logo)
                }else{
                    Picasso.get().load(liquor.url).into(pictureImageView);

                }

            }

    }}



}

