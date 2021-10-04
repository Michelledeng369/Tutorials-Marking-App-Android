package au.edu.utas.dengm.tutorialsmarkingapp

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import au.edu.utas.dengm.tutorialsmarkingapp.databinding.ActivityAttendanceBinding

const val WEEK_KEY : String = "WEEK"

class AttendanceActivity : AppCompatActivity() {
    private lateinit var ui : ActivityAttendanceBinding
    private var mySpinnerWeeks = arrayOf("Week 1", "Week 2", "Week 3", "Week 4", "Week 5", "Week 6", "Week 7", "Week 8", "Week 9", "Week 10")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityAttendanceBinding.inflate(layoutInflater)
        setContentView(ui.root)


            ui.weekSpinner.adapter = ArrayAdapter<String>(
            this,
            R.layout.simple_spinner_dropdown_item,
            mySpinnerWeeks
        )

        ui.weekSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long)
            {val selectedWeek = parent.getItemAtPosition(position).toString()



            } // to close the onItemSelected
        override fun onNothingSelected(parent: AdapterView<*>) { }

        }
        ui.btCheckbox.setOnClickListener {
            val week: String = ui.weekSpinner.selectedItem.toString()



            var intent = Intent(this, CheckboxActivity::class.java)
            intent.putExtra(WEEK_KEY , week)
            startActivity(intent)
        }

        ui.btGradeHd.setOnClickListener {
            val week: String = ui.weekSpinner.selectedItem.toString()


            var intent = Intent(this, GradeHdActivity::class.java)
            intent.putExtra(WEEK_KEY , week)
            startActivity(intent)
        }

        ui.btGradeA.setOnClickListener {
            val week: String = ui.weekSpinner.selectedItem.toString()


            var intent = Intent(this, GradeAbcActivity::class.java)
            intent.putExtra(WEEK_KEY , week)
            startActivity(intent)
        }

        ui.btScore.setOnClickListener {
            val week: String = ui.weekSpinner.selectedItem.toString()


            var intent = Intent(this, EnterScoreActivity::class.java)
            intent.putExtra(WEEK_KEY , week)
            startActivity(intent)
        }

        ui.btMultiple.setOnClickListener {
            val week: String = ui.weekSpinner.selectedItem.toString()


            var intent = Intent(this, MultipleActivity::class.java)
            intent.putExtra(WEEK_KEY , week)
            startActivity(intent)
        }

//https://www.youtube.com/watch?v=TcJysB6hp8Q
        val actionBar = supportActionBar
        actionBar!!.title="Attendance"

        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    //end
}