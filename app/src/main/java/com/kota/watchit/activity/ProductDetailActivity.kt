package com.kota.watchit.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.carteasy.v1.lib.Carteasy
import com.kota.watchit.R
import com.kota.watchit.databinding.ActivityProductDetailBinding

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail)

        binding.apply {
            val ids: String = intent.getStringExtra(ID)
            ProductName.text = intent.getStringExtra(NAME)
            price.text = intent.getStringExtra(PRICE)
            desc.text = intent.getStringExtra(DESC)
            val res = intent.getIntExtra(IMG,0)
            imageViews.setImageResource(res)
            buttonAdd.setOnClickListener{
                val cs = Carteasy()
                with(cs) {
                    add(ids, "ID", ID)
                    add(ids, "Product Name", ProductName.text)
                    add(ids, "Price", price.text)
                    add(ids, "Quantity", 1)
                    add(ids, "Image", res)
                    add(ids, "Description Product", desc.text)
                    commit(applicationContext)
                    persistData(applicationContext, true)
                }
                val toast1 = Toast.makeText(applicationContext,"Barang berhasil ditambahkan",Toast.LENGTH_LONG)
                toast1.show()
            }
        }
    }

    companion object {
        const val ID = "id"
        const val NAME = "name"
        const val PRICE = "12"
        const val DESC = "info"
        const val IMG = ""
    }
}
