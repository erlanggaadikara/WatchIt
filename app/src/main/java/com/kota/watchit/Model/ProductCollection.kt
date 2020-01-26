package com.kota.watchit.Model

import com.kota.watchit.R

object ProductCollection {

    private val idProduct = arrayOf(
        "a","b","c","d","e","f"
    )
    private val nameProduct =  arrayOf(
        "GC Jam Tangan Pria X900012G7S",
        "GC Chronograph Watch X72026G1s",
        "GC Jam Tangan Pria X51002G5S",
        "GC 1 Class X90005G2S",
        "CASIO Edifice EFR-524D-7AVDF",
        "GC Stainless Steel I41008G1"
    )

    private val priceProduct = arrayOf(
        "9790000","8999000","8999000","7790000","1979000","6499000"
    )

    private val descProduct = arrayOf(
        "Analog, Stainless Steel, Blue Strap, Chronograph, 100m Water Resistant, 45mm Case Diameter, Garansi 2 tahun Local Official Distributor Warranty",
        "Analog, Stainless Steel, Leather, Quartz, 100m Water Resistant, Garansi 2 tahun Local Official Distributor Warranty",
        "Analog, Stainless Steel, Chronograph, 100m Water Resistant, 47mm Case Diameter, Garansi 2 tahun Local Official Distributor Warranty",
        "Analog, Sapphire Crystal, Brown Leather Strap, Chronograph, 100m Water Resistant, Quartz Movement, 44mm Case Diameter, Garansi 2 tahun Local Official Distributor Warranty",
        "Analog, Stainless Steel, Quartz Movement, 100 meters water resistant, Chronograph, Date display, Garansi 2 tahun Local Official Distributor Warranty",
        "Analog, Stainless Steel, Quartz, 30m water resistant, Analogue, Chronograph, Date Display"
    )

    private val imgProduct = intArrayOf(
        R.drawable.gcx90012g7s,
        R.drawable.gcx720261gs,
        R.drawable.x51002g5s,
        R.drawable.gc1classx90005g2s,
        R.drawable.casioedifice,
        R.drawable.gci41008g1
    )

    val listData: Collection<Product>
        get(){
            val list = arrayListOf<Product>()
            for(position in idProduct.indices){
                val produk = Product()
                produk.idP = idProduct[position]
                produk.name = nameProduct[position]
                produk.desc = descProduct[position]
                produk.img = imgProduct[position]
                produk.price = priceProduct[position]
                list.add(produk)
            }
            return list
        }
}