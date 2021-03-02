package com.example.bottomsheetexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomsheetexample.databinding.BottomsheetFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior

class BottomSheetFragment : Fragment() {

    private var _binding: BottomsheetFragmentBinding? = null
    private val binding get() = _binding!!
    private var myAdapter : BottomSheetAdapter? = null

    private val data: List<Base> = listOf(
        Base(R.drawable.ic_1, "Very nice foto with a beautiful Mountains View!"),
        Base(R.drawable.ic_2, "Amazing blue and violet Aurora Borealis"),
        Base(R.drawable.ic_3, "Sunset in the city"),
        Base(R.drawable.ic_4, "Beautiful and dangerous Waterfall"),
        Base(R.drawable.ic_5, "Lake in Mountains and single Man")
    )

    private var bottomSheetBehavior: BottomSheetBehavior<RecyclerView>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = BottomsheetFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.baseRecycler.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(view.context)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.baseRecycler.layoutManager = layoutManager
        binding.baseRecycler.adapter = BottomSheetAdapter()
        myAdapter = binding.baseRecycler.adapter as BottomSheetAdapter

        myAdapter!!.loadData(data)

        bottomSheetBehavior = BottomSheetBehavior.from(binding.baseRecycler);

        bottomSheetBehavior?.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {

            override fun onStateChanged(bottomSheet: View, newState: Int) {
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                myAdapter!!.offset = slideOffset
                changeViewHolderItemsHeight()
            }
        })

        binding.baseRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                changeViewHolderItemsHeight()
            }
        })
    }

    private fun changeViewHolderItemsHeight(){
        val recyclerChildCount = binding.baseRecycler.childCount
        val recyclerViews = (0 until recyclerChildCount).mapNotNull { id ->
            binding.baseRecycler.getChildViewHolder(binding.baseRecycler.getChildAt(id))
        }.filterIsInstance<BottomSheetAdapter.BaseSheetViewHolder>()

        recyclerViews.forEach { specificViewHolder ->
            specificViewHolder.changeHeight(myAdapter!!.offset)
        }
    }
}
