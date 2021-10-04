package au.edu.utas.dengm.tutorialsmarkingapp

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import au.edu.utas.dengm.tutorialsmarkingapp.databinding.ActivityReportBinding
import au.edu.utas.dengm.tutorialsmarkingapp.databinding.GradeListBinding

class ReportActivity : AppCompatActivity() {
    private lateinit var ui : ActivityReportBinding
    private var week : String = ""


    private var mySpinnerWeeks = arrayOf("Week 1", "Week 2", "Week 3", "Week 4", "Week 5", "Week 6", "Week 7", "Week 8", "Week 9", "Week 10")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityReportBinding.inflate(layoutInflater)
        setContentView(ui.root)

        ui.myList.adapter = ReportAdapter(students = items)

        //vertical list
        ui.myList.layoutManager = LinearLayoutManager(this)



        ui.weekSpinner.adapter = ArrayAdapter<String>(
            this,
            R.layout.simple_spinner_dropdown_item,
            mySpinnerWeeks
        )


        ui.weekSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long)
            {val selectedWeek = parent.getItemAtPosition(position).toString()
                ui.week.text = selectedWeek
                week = selectedWeek

                var scores = 0.0
                for (student in items){
                    when(week){
                        "Week 1" -> scores += student.week1Score!!
                        "Week 2" -> scores += student.week2Score!!
                        "Week 3" -> scores += student.week3Score!!
                        "Week 4" -> scores += student.week4Score!!
                        "Week 5" -> scores += student.week5Score!!
                        "Week 6" -> scores += student.week6Score!!
                        "Week 7" -> scores += student.week7Score!!
                        "Week 8" -> scores += student.week8Score!!
                        "Week 9" -> scores += student.week9Score!!
                        "Week 10" -> scores += student.week10Score!!
                    }
                    Log.d("hhhhh", scores.toString())
                }

                ui.txtAve.text = (scores / items.size).toString()


                (ui.myList.adapter as ReportAdapter).notifyDataSetChanged()


            } // to close the onItemSelected
            override fun onNothingSelected(parent: AdapterView<*>) { }

        }



        //https://www.youtube.com/watch?v=TcJysB6hp8Q
        val actionBar = supportActionBar
        actionBar!!.title="Report"

        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    inner class ReportHolder(var ui: GradeListBinding) : RecyclerView.ViewHolder(ui.root) {}
    inner class ReportAdapter(private val students: MutableList<Student>) : RecyclerView.Adapter<ReportActivity.ReportHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportHolder {
            val ui = GradeListBinding.inflate(layoutInflater, parent, false)   //inflate a new row from the my_list_item.xml
            return ReportHolder(ui)
        }

        override fun onBindViewHolder(holder: ReportHolder, position: Int) {
            val student = students[position] //get the data at the requested position
            holder.ui.sName.text = student.studentname.toString()

            var score: Double? = 0.0
            when (week) {
                "Week 1" -> score = student.week1Score!!
                "Week 2" -> score = student.week2Score!!
                "Week 3" -> score = student.week3Score!!
                "Week 4" -> score = student.week4Score!!
                "Week 5" -> score = student.week5Score!!
                "Week 6" -> score = student.week6Score!!
                "Week 7" -> score = student.week7Score!!
                "Week 8" -> score = student.week8Score!!
                "Week 9" -> score = student.week9Score!!
                "Week 10" -> score = student.week10Score!!
            }
            holder.ui.sGrade.text = score.toString()

           }


        override fun getItemCount(): Int {
            return students.size
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    //end

    override fun onResume() {
        super.onResume()

        ui.myList.adapter?.notifyDataSetChanged()
    }
}


