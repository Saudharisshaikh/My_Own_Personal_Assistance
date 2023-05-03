package com.example.myownpersonalassistance.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myownpersonalassistance.R
import com.example.myownpersonalassistance.databinding.FragmentAddScheduleBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddScheduleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddScheduleFragment : Fragment() {

    lateinit var fragmentAddScheduleBinding: FragmentAddScheduleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentAddScheduleBinding = FragmentAddScheduleBinding.inflate(inflater,container,false)

        // Inflate the layout for this fragment
        return fragmentAddScheduleBinding.root
    }




}