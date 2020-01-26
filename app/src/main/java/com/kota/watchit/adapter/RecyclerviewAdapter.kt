package com.kota.watchit.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.carteasy.v1.lib.Carteasy
import com.kota.watchit.Model.CartProduct
import com.kota.watchit.R
import kotlinx.android.synthetic.main.activity_product_detail.view.*
import kotlinx.android.synthetic.main.cardview_cart.view.*

class RecyclerviewAdapter(private val listData: ArrayList<CartProduct>): RecyclerView.Adapter<RecyclerviewAdapter.Holder>() {

    val cs = Carteasy()
    var ids: String? = ""


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.cardview_cart, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int{
        val count = listData.size
        return count
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) , View.OnClickListener{
        private val alertDialog = AlertDialog.Builder(itemView.context)
        fun bind(item: CartProduct) {
            with(itemView) {
                ids = item.idP
                item_name.text = item.name
                item_price.text = item.price
                product_thumb.setImageResource(item.img)
                item_amount.text = item.qty
            }
        }
        init{
            itemView.add_item.setOnClickListener(this)
            itemView.remove_item.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if(v?.id == R.id.add_item){
                val number = Integer.parseInt(itemView.item_amount.text.toString()) + 1
                val cost = Integer.parseInt(listData[adapterPosition].price) * number
                with(itemView) {
                    item_amount.text = number.toString()
                    item_price.text = cost.toString()
                }
                with(listData[adapterPosition]) {
                    qty = number.toString()
                    price = cost.toString()
                }
                with(cs){
                    update(ids, "Quantity", number, itemView.context)
                    update(ids, "Price", cost, itemView.context)
                  }
                }
            else if(v?.id == R.id.remove_item){
                if(listData[adapterPosition].qty.equals("1")){
                    with(alertDialog) {
                        setTitle("Hapus?")
                        setMessage("Hapus barang dari keranjang?")
                        setPositiveButton("Yes") { dialog, _ ->
                            listData.drop(adapterPosition)
                            cs.RemoveId(ids, itemView.context)
                            dialog.dismiss()
                        }
                        setNegativeButton("No") { dialog, _ ->
                            listData[adapterPosition].qty = "1"
                            dialog.dismiss()
                        }
                        show()
                    }
                }else {
                    var number = Integer.parseInt(itemView.item_amount.text.toString())
                    val cost = Integer.parseInt(listData[adapterPosition].price) / number
                    number -= 1
                    with(itemView) {
                        item_amount.text = number.toString()
                        item_price.text = cost.toString()
                    }
                    with(listData[adapterPosition]) {
                        qty = number.toString()
                        price = cost.toString()
                    }
                    with(cs){
                        update(ids, "Quantity", number, itemView.context)
                        update(ids, "Price", cost, itemView.context)
                    }
                }
            }
        }
    }
}
