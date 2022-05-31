import com.example.bot.R

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.bot.QuestionsList1

class ratefragment : DialogFragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val Questions_List_1 = QuestionsList1()
        var rateView: View = inflater.inflate(R.layout.rate_fragment, container, false)
        rateView.findViewById<Button>(R.id.cancelButton).setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.Activity_Smurfs_Layout,Questions_List_1)
                commit()
            }
        }
        rateView.findViewById<Button>(R.id.submitButton).setOnClickListener {
            val selectedID = rateView.findViewById<RadioGroup>(R.id.rateRadioGroup).checkedRadioButtonId
            val radio = rateView.findViewById<RadioButton>(selectedID)
            var ratingResult = radio.text.toString()
            // use this value to store into database here
            Toast.makeText(context, "You rated $ratingResult. Thank you!", Toast.LENGTH_SHORT).show()
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.Activity_Smurfs_Layout,Questions_List_1)
                commit()
            }
        }
        return rateView
    }

}