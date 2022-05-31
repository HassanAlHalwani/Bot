package com.example.bot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Admin_Delete.newInstance] factory method to
 * create an instance of this fragment.
 */
class Admin_Delete : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var id : Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_admin__delete, container, false)
        val databaseSmurf = Database(view.context,"SMURF")
        val databaseVisitor = Database(view.context,"VISITOR")
        var tableName : Int = 0
        val readDataSmurf : ArrayList<DatabaseTrace> = databaseSmurf.readData1()
        val readDataVisitor : ArrayList<DatabaseTrace> = databaseVisitor.readData1()
        val items = arrayOfNulls<Int>(readDataSmurf.size)
        val items1 = arrayOfNulls<Int>(readDataVisitor.size)
        val radBTNSmurf : RadioButton = view.findViewById(R.id.radioButton2)
        val radBTNVisitor : RadioButton = view.findViewById(R.id.radioButton4)
        val spinner = view.findViewById<Spinner>(R.id.spinner4)
        val showQuestion : TextView = view.findViewById(R.id.textView3)
        val showAnswer : TextView = view.findViewById(R.id.textView4)
        val checkbox1 : CheckBox = view.findViewById(R.id.checkBox)
        val checkbox2 : CheckBox = view.findViewById(R.id.checkBox5)
        var counter : Int = 0
        val deleteBTN : Button = view.findViewById(R.id.button3)
        val admin = AdminFea()


        try{
            while (counter < readDataSmurf.size) {
                items[counter] = readDataSmurf[counter].id
                counter += 1

            }} catch (e : Exception){}

        counter = 0

        try{
            while (counter < readDataVisitor.size) {
                items1[counter] = readDataVisitor[counter].id
                counter += 1

            }} catch (e : Exception){}

        radBTNSmurf.setOnClickListener{
            tableName = 1
            spinner?.adapter = activity?.applicationContext?.let { ArrayAdapter(it, R.layout.support_simple_spinner_dropdown_item, items) } as SpinnerAdapter
        }

        radBTNVisitor.setOnClickListener{
            tableName = 2
            spinner?.adapter = activity?.applicationContext?.let { ArrayAdapter(it, R.layout.support_simple_spinner_dropdown_item, items1) } as SpinnerAdapter
        }
            var selectedItem : Int = 0
        spinner.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedItem = parent?.getItemAtPosition(position).toString().toInt()

                if (tableName == 1) {
                    counter = 0;
                    checkbox1.text = "Registration"
                    checkbox2.text = "Finance"
                    while (counter < readDataSmurf.size) {
                        when (selectedItem) {
                            items[counter] -> {
                                showQuestion.text = readDataSmurf[counter].question
                                showAnswer.text = readDataSmurf[counter].answer
                                if(readDataSmurf[counter].category.equals("Registration")) {
                                    checkbox1.isChecked = true
                                    checkbox2.isChecked = false
                                }
                                if(readDataSmurf[counter].category.equals("Finance")) {
                                    checkbox1.isChecked = false
                                    checkbox2.isChecked = true
                                }

                            }
                        }
                        counter += 1
                    }
                }

                if (tableName == 2) {
                    counter = 0;
                    checkbox1.text = "General"
                    checkbox2.text = "Sites"
                    while (counter < readDataVisitor.size) {
                        when (selectedItem) {
                            items1[counter] -> {
                                showQuestion.text = readDataVisitor[counter].question
                                showAnswer.text = readDataVisitor[counter].answer
                                if(readDataVisitor[counter].category.equals("General")) {
                                    checkbox1.isChecked = true
                                    checkbox2.isChecked = false
                                }
                                if(readDataVisitor[counter].category.equals("Sites")) {
                                    checkbox1.isChecked = false
                                    checkbox2.isChecked = true
                                }

                            }
                        }
                        counter += 1
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        deleteBTN.setOnClickListener{
            if(tableName == 1) {
                val isDeleted = databaseSmurf.deleteRow(selectedItem)
                if (isDeleted) {
                    Toast.makeText(
                        view.context,
                        "Data Deleted Successful With Id = ${selectedItem}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                parentFragmentManager.beginTransaction().apply {
                    replace(R.id.adminLayout,admin)
                    commit()
                }

            }
            if(tableName == 2) {
                val isDeleted = databaseVisitor.deleteRow(selectedItem)
                if (isDeleted) {
                    Toast.makeText(
                        view.context,
                        "Data Deleted Successful With Id = ${selectedItem}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                parentFragmentManager.beginTransaction().apply {
                    replace(R.id.adminLayout,admin)
                    commit()
                }

            }
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Admin_Delete.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Admin_Delete().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}