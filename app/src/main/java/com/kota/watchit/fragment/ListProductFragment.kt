package com.kota.watchit.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.ListFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.kota.watchit.Model.Product
import com.kota.watchit.Model.ProductCollection

import com.kota.watchit.R
import com.kota.watchit.adapter.GridviewAdapter
import com.kota.watchit.databinding.FragmentListProductBinding
import kotlinx.android.synthetic.main.fragment_list_product.*

class ListProductFragment : Fragment() {

    private var list: ArrayList<Product> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentListProductBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_product, container, false)
        val view: View =binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.addAll(ProductCollection.listData)
        gridview.apply{
            gridview.layoutManager = GridLayoutManager(context, 2)
            val adapter = GridviewAdapter(list)
            gridview.adapter = adapter
        }
    }

    companion object {
        fun newInstance(): ListProductFragment = ListProductFragment()
    }
}

