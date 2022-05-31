package com.example.bot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LogIn.newInstance] factory method to
 * create an instance of this fragment.
 */
class LogIn : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var errorFlag : Boolean = true

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
        val view = inflater.inflate(R.layout.fragment_log_in, container, false)
        val user : EditText = view.findViewById(R.id.username)
        val pass : EditText = view.findViewById(R.id.password)
        val loginBtn : Button = view.findViewById(R.id.loginBTN)
        val text1 : TextView = view.findViewById(R.id.textView)
        val text2 : TextView = view.findViewById(R.id.textView2)
        val db = StudentDB(view.context);
        var usernameText : Int = 0
        var password : String = ""
        var passwordChecker : String = ""
        val Questions_List_1 = QuestionsList1()

        loginBtn.setOnClickListener{
            try{
                usernameText = user.text!!.toString().toInt()
                password = pass.text!!.toString()}
            catch (e : Exception){}
            errorFlag = db.checkPassword(usernameText,password)


            if (!errorFlag) {
                text1.visibility = View.VISIBLE
                text2.visibility = View.VISIBLE
            } else {
                text1.visibility = View.INVISIBLE
                text2.visibility = View.INVISIBLE

                parentFragmentManager.beginTransaction().apply {
                    replace(R.id.Activity_Smurfs_Layout,Questions_List_1)
                    commit()
                }


            }

        }

        text2.setOnClickListener{
            Toast.makeText(view.context,"Call (Anas) 07-97-22-17-59",Toast.LENGTH_LONG).show()
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
         * @return A new instance of fragment LogIn.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LogIn().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}