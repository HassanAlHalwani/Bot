package com.example.bot


import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView
import ratefragment
import java.util.*
import kotlin.collections.ArrayList






// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuestionsList1.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuestionsList1 : Fragment(),TextToSpeech.OnInitListener{
    // TODO: Rename and change types of parameters
    private val Table_Name: String = "SMURF"
    private var param1: String? = null
    private var param2: String? = null
    private var textToSpeech: TextToSpeech? = null
    private var buttonSpeak: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_questions_list1, container, false)
        val spinner = view.findViewById<Spinner>(R.id.spinner)
        val spinnerCat = view.findViewById<Spinner>(R.id.spinner2)
        val answer : TextView = view.findViewById(R.id.answer)
        val database = Database(view.context,Table_Name)
        var categorySelected : String = "Category"
        val readData : ArrayList<DatabaseTrace> = database.readData()
        val readData1 : ArrayList<DatabaseTrace> = database.selectQuestions("Registration")
        val readData2 : ArrayList<DatabaseTrace> = database.selectQuestions("Finance")
        val size : Int = readData.size
        val size1 : Int = readData1.size
        val size2 : Int = readData2.size
        val category = arrayOf("Category","Registration","Finance")
        val items = arrayOfNulls<String>(size + 1)
        val items1 = arrayOfNulls<String>(size1 + 1)
        val items2 = arrayOfNulls<String>(size2 + 1)
        buttonSpeak = view.findViewById(R.id.speechbtn)
        val rateButton : Button = view.findViewById(R.id.rateBTN)
        buttonSpeak!!.isEnabled = true
        textToSpeech = TextToSpeech(view.context, TextToSpeech.OnInitListener{})
        var voiceSpeech: String = ""
        var counter : Int = 0;

        items [0] = "QUESTIONS"
        items1 [0] = "QUESTIONS"
        items2 [0] = "QUESTIONS"

        val rate = ratefragment()
        rateButton.setOnClickListener{
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.Activity_Smurfs_Layout,rate)
                commit()
            }

        }

        try{
            while (counter < size) {
                items[counter + 1] = readData[counter].question
                counter += 1

            }} catch (e : Exception){}

        counter = 0

        try{
            while (counter < size1) {
                items1[counter + 1] = readData1[counter].question
                counter += 1

            }} catch (e : Exception){}

        counter = 0

        try{
            while (counter < size2) {
                items2[counter + 1] = readData2[counter].question
                counter += 1

            }} catch (e : Exception){}

        spinner?.adapter = activity?.applicationContext?.let { ArrayAdapter(it, R.layout.support_simple_spinner_dropdown_item, items) } as SpinnerAdapter
        spinnerCat?.adapter = activity?.applicationContext?.let { ArrayAdapter(it, R.layout.support_simple_spinner_dropdown_item, category) } as SpinnerAdapter

        spinnerCat.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var selectedItem = parent?.getItemAtPosition(position).toString()

                counter = 0;
                while (counter < size) {
                    when (selectedItem) {
                        category[0] -> {
                            answer.text = "No Question Selected"
                            categorySelected = "Category"
                            spinner?.adapter = activity?.applicationContext?.let {
                                ArrayAdapter(
                                    it,
                                    R.layout.support_simple_spinner_dropdown_item,
                                    items
                                )
                            } as SpinnerAdapter
                        }
                        category[1] -> {
                            answer.text = "No Question Selected"
                            categorySelected = "Registration"
                            spinner?.adapter = activity?.applicationContext?.let {
                                ArrayAdapter(
                                    it,
                                    R.layout.support_simple_spinner_dropdown_item,
                                    items1
                                )
                            } as SpinnerAdapter
                        }
                        category[2] -> {
                            answer.text = "No Question Selected"
                            categorySelected = "Finance"
                            spinner?.adapter = activity?.applicationContext?.let {
                                ArrayAdapter(
                                    it,
                                    R.layout.support_simple_spinner_dropdown_item,
                                    items2
                                )
                            } as SpinnerAdapter
                        }
                    }
                    counter += 1
                    }

                }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {

                var selectedItem = parent.getItemAtPosition(position).toString()
                if (categorySelected.equals(category[0])) {
                    counter = 0;
                    while (counter < size) {
                        when (selectedItem) {
                            items[0] -> answer.text = "No Question Selected"
                            items[counter + 1] -> {
                                answer.text = readData[counter].answer
                                voiceSpeech = readData[counter].answer
                            }
                        }
                        counter += 1
                    }
                }

                if (categorySelected.equals(category[1])) {
                    counter = 0;
                    while (counter < size1) {
                        when (selectedItem) {
                            items1[0] -> answer.text = "No Question Selected"
                            items1[counter + 1] -> {
                                answer.text = readData1[counter].answer
                                voiceSpeech = readData1[counter].answer
                            }
                        }
                        counter += 1
                    }
                }

                if (categorySelected.equals(category[2])) {
                    counter = 0;
                    while (counter < size2) {
                        when (selectedItem) {
                            items2[0] -> answer.text = "No Question Selected"
                            items2[counter + 1] -> {
                                answer.text = readData2[counter].answer
                                voiceSpeech = readData2[counter].answer
                            }
                        }
                        counter += 1
                    }
                }


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        textToSpeech!!.language = Locale.UK
        textToSpeech!!.setPitch(0.7F)
        textToSpeech!!.setSpeechRate(0.8F)
        buttonSpeak!!.setOnClickListener { speakOut(voiceSpeech) }

        return view
    }

    private fun speakOut(text : String) {
        textToSpeech!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QuestionsList1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic fun newInstance(param1: String, param2: String) =
                QuestionsList1().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = textToSpeech!!.setLanguage(Locale.UK)


            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS","The Language specified is not supported!")

            } else {
                buttonSpeak!!.isEnabled = true

            }

        } else {
            Log.e("TTS", "Initilization Failed!")

        }
    }

    override fun onDestroy() {
        if (textToSpeech != null) {
            textToSpeech!!.stop()
            textToSpeech!!.shutdown()
        }
        super.onDestroy()
    }
}
