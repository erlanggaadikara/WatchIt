package com.kota.watchit.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kota.watchit.Model.Product
import com.kota.watchit.R
import com.kota.watchit.activity.ProductDetailActivity
import kotlinx.android.synthetic.main.cardview_home.view.*

class GridviewAdapter(private val ProductList: ArrayList<Product>) : RecyclerView.Adapter<GridviewAdapter.Holder>() {


    override fun getItemCount(): Int = ProductList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.cardview_home, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(ProductList[position])
    }



    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(item: Product) {
            with(itemView) {
                nameProduct.text = item.name
                prcProduct.text = item.price
                imgProduct.setImageResource(item.img)
            }
            itemView.setOnClickListener {
                val dataIntent = Intent(itemView.context, ProductDetailActivity::class.java)
                with(dataIntent){
                    putExtra(ProductDetailActivity.NAME, item.name)
                    putExtra(ProductDetailActivity.IMG, item.img)
                    putExtra(ProductDetailActivity.DESC, item.desc)
                    putExtra(ProductDetailActivity.PRICE,item.price)
                    putExtra(ProductDetailActivity.ID,item.idP)
                    itemView.context.startActivity(dataIntent)
                }
            }
        }
    }
}

