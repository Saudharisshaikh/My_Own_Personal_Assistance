package com.example.myownpersonalassistance.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myownpersonalassistance.Fragments.Entities.TitleEntity
import com.example.myownpersonalassistance.R
import com.example.myownpersonalassistance.Utils.Util
import com.example.myownpersonalassistance.databinding.FragmentAddScheduleBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AddScheduleFragment : Fragment() {

   lateinit var list: ArrayList<TitleEntity>

    lateinit var fragmentAddScheduleBinding: FragmentAddScheduleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentAddScheduleBinding = FragmentAddScheduleBinding.inflate(inflater,container,false)

        // Inflate the layout for this fragment
        return fragmentAddScheduleBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list = ArrayList<TitleEntity> ()

        val titleEntityOne:TitleEntity = TitleEntity(1,"Office Meeting")
        val titleEntityTwo:TitleEntity = TitleEntity(2,"Hangout")
        val titleEntityThree:TitleEntity = TitleEntity(3,"Emergency Meeting")
        val titleEntityFour :TitleEntity = TitleEntity(4,"Others")

        list.add(titleEntityOne)
        list.add(titleEntityTwo)
        list.add(titleEntityThree)
        list.add(titleEntityFour)


        fragmentAddScheduleBinding.titleLayout.setOnClickListener {

            Util.showListDialogGeneric(
                context,
                list,
                R.string.list_title,
                listener = object :Util.selectedListener{
                    override fun onListItemSelected(item: Int) {

                        val titles: TitleEntity = list.get(item)
                        fragmentAddScheduleBinding.title.setText(titles.name)
                    }

                }
            )


        }
    }


    public fun onSelectItem(postion:Int){

    }

}