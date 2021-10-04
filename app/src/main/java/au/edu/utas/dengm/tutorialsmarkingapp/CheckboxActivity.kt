package au.edu.utas.dengm.tutorialsmarkingapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import au.edu.utas.dengm.tutorialsmarkingapp.databinding.ActivityCheckboxBinding
import au.edu.utas.dengm.tutorialsmarkingapp.databinding.CheckboxListBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import java.io.File


class CheckboxActivity : AppCompatActivity() {
    private lateinit var ui : ActivityCheckboxBinding
    private var week : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityCheckboxBinding.inflate(layoutInflater)
        setContentView(ui.root)

        ui.myList.adapter = StudentAdapter(students = items)

        //vertical list
        ui.myList.layoutManager = LinearLayoutManager(this)

        week = intent.getStringExtra(WEEK_KEY).toString()



        //https://www.youtube.com/watch?v=TcJysB6hp8Q
        val actionBar = supportActionBar
        actionBar!!.title= "Checkbox  -  $week"

        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

        Toast.makeText(this, "${items.size} students", Toast.LENGTH_SHORT).show()

    }

    inner class StudentHolder(var ui: CheckboxListBinding) : RecyclerView.ViewHolder(ui.root) {}
    inner class StudentAdapter(private val students: MutableList<Student>) : RecyclerView.Adapter<StudentHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckboxActivity.StudentHolder {
            val ui = CheckboxListBinding.inflate(layoutInflater, parent, false)   //inflate a new row from the my_list_item.xml
            return StudentHolder(ui)
        }

        override fun onBindViewHolder(holder: StudentHolder, position: Int) {


            val student = students[position] //get the data at the requested position
            holder.ui.txtID.text = student.studentid.toString()
            holder.ui.txtName.text = student.studentname.toString()
            holder.ui.imageView.setImageURI(Uri.fromFile(File(student.photo.toString())))


            val db = Firebase.firestore
            val studentMarks = db.collection("students")

            var score = 0.0

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

            holder.ui.myCheckbox.isChecked = score == 100.0



            holder.ui.myCheckbox.setOnClickListener {

                val db = Firebase.firestore
                val studentMarks = db.collection("students")

                var updateScore = ""
                if (holder.ui.myCheckbox.isChecked) {
                    when (week) {
                        "Week 1" -> updateScore = "week1Score"
                        "Week 2" -> updateScore = "week2Score"
                        "Week 3" -> updateScore = "week3Score"
                        "Week 4" -> updateScore = "week4Score"
                        "Week 5" -> updateScore = "week5Score"
                        "Week 6" -> updateScore = "week6Score"
                        "Week 7" -> updateScore = "week7Score"
                        "Week 8" -> updateScore = "week8Score"
                        "Week 9" -> updateScore = "week9Score"
                        "Week 10" -> updateScore = "week10Score"
                    }
                    studentMarks.document("${student.id}")
                        .update("$updateScore", 100.0)
                        .addOnSuccessListener {
                            when (week) {
                                "Week 1" -> student.week1Score = 100.0
                                "Week 2" -> student.week2Score = 100.0
                                "Week 3" -> student.week3Score = 100.0
                                "Week 4" -> student.week4Score = 100.0
                                "Week 5" -> student.week5Score = 100.0
                                "Week 6" -> student.week6Score = 100.0
                                "Week 7" -> student.week7Score = 100.0
                                "Week 8" -> student.week8Score = 100.0
                                "Week 9" -> student.week9Score = 100.0
                                "Week 10" -> student.week10Score = 100.0
                            }
                        }
                } else {
                    when (week) {
                        "Week 1" -> updateScore = "week1Score"
                        "Week 2" -> updateScore = "week2Score"
                        "Week 3" -> updateScore = "week3Score"
                        "Week 4" -> updateScore = "week4Score"
                        "Week 5" -> updateScore = "week5Score"
                        "Week 6" -> updateScore = "week6Score"
                        "Week 7" -> updateScore = "week7Score"
                        "Week 8" -> updateScore = "week8Score"
                        "Week 9" -> updateScore = "week9Score"
                        "Week 10" -> updateScore = "week10Score"

                    }
                    studentMarks.document("${student.id}")
                        .update("$updateScore", 0.0)
                        .addOnSuccessListener {
                            when (week) {
                                "Week 1" -> student.week1Score = 0.0
                                "Week 2" -> student.week2Score = 0.0
                                "Week 3" -> student.week3Score = 0.0
                                "Week 4" -> student.week4Score = 0.0
                                "Week 5" -> student.week5Score = 0.0
                                "Week 6" -> student.week6Score = 0.0
                                "Week 7" -> student.week7Score = 0.0
                                "Week 8" -> student.week8Score = 0.0
                                "Week 9" -> student.week9Score = 0.0
                                "Week 10" -> student.week10Score = 0.0

                            }
                        }
                }
            }
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
}




