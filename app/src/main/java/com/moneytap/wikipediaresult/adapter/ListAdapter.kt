package com.moneytap.wikipediaresult.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.moneytap.wikipediaresult.R
import com.moneytap.wikipediaresult.activity.DetailedViewActivity
import com.moneytap.wikipediaresult.model.Page
import com.squareup.picasso.Picasso

class ListAdapter(dataList : List<Page>, val context: Context):
    RecyclerView.Adapter<ListViewHolder>() {

    private val inflater: LayoutInflater
    private val arraylist: List<Page>


    init {

        inflater = LayoutInflater.from(context)
        this.arraylist = dataList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = inflater.inflate(R.layout.list_item, parent, false)

        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arraylist.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        if(arraylist[position].title != null)
            holder.tvHeading.text = arraylist[position].title
        if(arraylist[position].terms!=null && arraylist[position].terms.description[0]!=null)
            holder.tvdesc.text = arraylist[position].terms.description[0]
        if(arraylist[position].thumbnail!=null && arraylist[position].thumbnail.source!=null)
            Picasso.get().load(arraylist[position].thumbnail.source).into(holder.iconImg)
        else
            Picasso.get().load(R.drawable.ic_launcher_background).into(holder.iconImg)

        holder.searcCard.setOnClickListener{
            val url = context.getString(R.string.wikipedia_detail_url) + arraylist[position].title
            val intent = Intent(context, DetailedViewActivity::class.java)
            intent.putExtra("url", url)
            context.startActivity(intent)
        }
    }
}
class ListViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val tvHeading : TextView
    val tvdesc: TextView
    val iconImg: ImageView
    val searcCard: CardView

    init {
        tvHeading = view.findViewById(R.id.heading) as TextView
        tvdesc = view.findViewById(R.id.description) as TextView
        iconImg = view.findViewById(R.id.img_thumbnail) as ImageView
        searcCard = view.findViewById(R.id.search_card) as CardView
    }
}