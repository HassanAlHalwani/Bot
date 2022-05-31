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
 * Use the [Admin.newInstance] factory method to
 * create an instance of this fragment.
 */
class Admin : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        val view = inflater.inflate(R.layout.fragment_admin, container, false)
        val empty = arrayOf("")
        var category : String = ""
        var tableName : String = ""
        var question : String = ""
        var answer : String = ""
        val categoryList = arrayOf("Category","Registration","Finance")
        val categoryList1 = arrayOf("Category","General","Sites")
        val spinnerCat = view.findViewById<Spinner>(R.id.spinner2)
        val radBtn1 : RadioButton = view.findViewById(R.id.radioButton3)
        val radBtn2 : RadioButton = view.findViewById(R.id.radioButton)
        val insertBtn : Button = view.findViewById(R.id.button2)
        val questionText : EditText = view.findViewById(R.id.question)
        val answerText : EditText = view.findViewById(R.id.answer)
        val admin = AdminFea()

        spinnerCat?.adapter = activity?.applicationContext?.let { ArrayAdapter(it, R.layout.support_simple_spinner_dropdown_item, empty) } as SpinnerAdapter

        radBtn1.setOnClickListener{
            tableName = "SMURF"
            spinnerCat?.adapter = activity?.applicationContext?.let { ArrayAdapter(it, R.layout.support_simple_spinner_dropdown_item, categoryList) } as SpinnerAdapter
        }
        radBtn2.setOnClickListener{
            tableName = "VISITOR"
            spinnerCat?.adapter = activity?.applicationContext?.let { ArrayAdapter(it, R.layout.support_simple_spinner_dropdown_item, categoryList1) } as SpinnerAdapter
        }
        spinnerCat.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var selectedItem = parent?.getItemAtPosition(position).toString()
                when (selectedItem) {
                    "Registration" -> category = "Registration"
                    "Finance" -> category = "Finance"
                    "General" -> category = "General"
                    "Sites" -> category = "Sites"
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        insertBtn.setOnClickListener{
            val database = Database(view.context,tableName)
            val id = database.maxID
            question = questionText.text.toString()
            answer = answerText.text.toString()
            database.addNewQuestion(id+1,question,answer,category)
            Toast.makeText(view.context,"Data Inserted Successful With Id = ${id+1}",Toast.LENGTH_LONG).show()
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.adminLayout,admin)
                commit()
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
         * @return A new instance of fragment Admin.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Admin().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}