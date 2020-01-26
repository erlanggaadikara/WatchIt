package com.kota.watchit.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.carteasy.v1.lib.Carteasy
import com.kota.watchit.Model.CartProduct
import com.kota.watchit.Model.ProductCollection

import com.kota.watchit.R
import com.kota.watchit.adapter.RecyclerviewAdapter
import com.kota.watchit.databinding.FragmentCartBinding
import kotlinx.android.synthetic.main.fragment_cart.*

class CartFragment : Fragment() {


    private val cs = Carteasy()
    private val listData =  arrayListOf<CartProduct>()
    private val listData2 = arrayListOf<CartProduct>()
    private val cp = CartProduct()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentCartBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)
        binding.apply {
            cartStatus.visibility = View.VISIBLE
            shoppingCart.visibility = View.INVISIBLE
            buttonChkout.visibility = View.INVISIBLE
        }
        val myView: View = binding.root
        return myView
    }

    override fun onResume() {
        super.onResume()
        listData.clear()
        listData2.clear()
        listptoduk()
    }

    private fun listptoduk(){
        val list = cs.ViewAll(context)
        if(list == null){
            cartStatus.visibility = View.VISIBLE
            shoppingCart.visibility = View.INVISIBLE
            buttonChkout.visibility = View.INVISIBLE
        }else{
            cartStatus.visibility = View.INVISIBLE
            shoppingCart.visibility = View.VISIBLE
            buttonChkout.visibility = View.VISIBLE
            for (entry in list.entries) {
                val values= entry.value

                val innerData: Map<Any?,Any?> = values
                for (innerEntry in innerData.entries) {
                    val values1 = innerEntry.key
                    Log.d("TAG",values1.toString())
                    when (values1.toString()) {
                        "Product Name" ->  cp.name = innerEntry.value.toString()
                        "Price" -> cp.price = innerEntry.value.toString()
                        "Image" -> cp.img = resources.getIdentifier(innerEntry.value.toString(), "drawable", context?.packageName)
                        "Quantity" ->  cp.qty = innerEntry.value.toString()
                        "ID" ->  cp.idP = innerEntry.value.toString()
                    }
                    listData.add(cp)
                }
                listData2.add(listData.last())
            }
            shoppingCart.apply {
                shoppingCart.layoutManager = LinearLayoutManager(activity)
                val recyclerViewAdapter = RecyclerviewAdapter(listData2)
                shoppingCart.adapter = recyclerViewAdapter
            }
            buttonChkout.setOnClickListener{
                var priceTotal = 0
                for(x in 0 until listData2.size){
                    priceTotal += Integer.parseInt(listData2[x].price)
                }
                val builder = AlertDialog.Builder(activity!!)
                with(builder){
                    setTitle("Checkout")
                    setMessage("Total \n $priceTotal")
                    setPositiveButton("OK"){dialog, _ ->
                        dialog.dismiss()
                    }
                    show()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listptoduk()
    }

    companion object {

        fun newInstance (): CartFragment = CartFragment()
            }
    }

