package com.example.projectkpbaru

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectkpbaru.databinding.FragmentFoodBinding

class FoodFragment : Fragment(R.layout.fragment_food) {

    private lateinit var binding: FragmentFoodBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFoodBinding.bind(view)
//        val title = resources.getStringArray(R.array.Foodtitle)
//        val desc = resources.getStringArray(R.array.Desc)
//        val imageArry = arrayOf(
//            R.drawable.beuty_adult_cat,
//            R.drawable.bolt2,
//            R.drawable.catchoize_adult,
//            R.drawable.catchoize,
//            R.drawable.excel,
//            R.drawable.felibite,
//            R.drawable.filibite_mother_kitten,
//            R.drawable.lifecat,
//            R.drawable.mantoel,
//            R.drawable.markotop_salmon_ayam,
//            R.drawable.meo_adult_tuna,
//            R.drawable.meo_kitten,
//            R.drawable.mypet,
//            R.drawable.oricat_kitten,
//            R.drawable.rajafit_adult,
//            R.drawable.rajafit_hair_and_skin,
//            R.drawable.royal_canin_first_age,
//            R.drawable.whiskas_junior,
//            R.drawable.whiskhas_adult,
//            R.drawable.toms_catfood_tuna_lamb
//        )
//
//        Log.d(TAG, "onViewCreated: ${imageArry.size}")
//        Log.d(TAG, "onViewCreated: ${title.size}")
//        Log.d(TAG, "onViewCreated: ${desc.size}")
//
//            val data = mutableListOf<RVDataclass>()
//            for (i in imageArry.indices) {
//                data.add(RVDataclass(imageArry[i], title[i], desc[i]))
//            }
//
//            val adapter = rvAdapter(data)
//            binding.RVFragFood.adapter = adapter
//            binding.RVFragFood.layoutManager = LinearLayoutManager(requireContext())
//
//
//
//
//
//
//
//
//        binding.btnLogout.setOnClickListener {
//            Firebase.auth.signOut()
//            startActivity(Intent(requireActivity(), LoginActivity::class.java))
//        }
    }
}