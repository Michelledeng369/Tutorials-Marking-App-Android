package au.edu.utas.dengm.tutorialsmarkingapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import au.edu.utas.dengm.tutorialsmarkingapp.databinding.ActivityCheckboxBinding
import au.edu.utas.dengm.tutorialsmarkingapp.databinding.ActivityChooseMultipleBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.io.File

class ChooseMultipleActivity : AppCompatActivity() {
    private lateinit var ui : ActivityChooseMultipleBinding
    private var week : String = ""
    private var studentposition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityChooseMultipleBinding.inflate(layoutInflater)
        setContentView(ui.root)


        week = intent.getStringExtra(WEEK_KEY).toString()

        studentposition = intent.getIntExtra(Student_INDEX, -1)
        var studentObject = items[studentposition]

        ui.txtID.setText(studentObject.studentid.toString())
        ui.txtName.setText(studentObject.studentname.toString())
        ui.myImageView.setImageURI(Uri.fromFile(File(studentObject.photo.toString())))

        val db = Firebase.firestore
        val studentMarks = db.collection("students")
        var score = 0.00
        when (week) {
            "Week 1" -> score = studentObject.week1Score!!
            "Week 2" -> score = studentObject.week2Score!!
            "Week 3" -> score = studentObject.week3Score!!
            "Week 4" -> score = studentObject.week4Score!!
            "Week 5" -> score = studentObject.week5Score!!
            "Week 6" -> score = studentObject.week6Score!!
            "Week 7" -> score = studentObject.week7Score!!
            "Week 8" -> score = studentObject.week8Score!!
            "Week 9" -> score = studentObject.week9Score!!
            "Week 10" -> score = studentObject.week10Score!!
        }

        ui.grade.text = score.toString()
        Log.d("multiple", score.toString())

        ui.saveButton.setOnClickListener {
            //var grade: Int = ui.radiohd.checkedRadioButtonId

            val db = Firebase.firestore
            val studentMarks = db.collection("students")

            var updateScore = ""
            if (ui.task1.isChecked && ui.task2.isChecked) {
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
                studentMarks.document("${studentObject.id}")
                    .update("$updateScore", 100.0)
                    .addOnSuccessListener {
                        when (week) {
                            "Week 1" -> studentObject.week1Score = 100.0
                            "Week 2" -> studentObject.week2Score = 100.0
                            "Week 3" -> studentObject.week3Score = 100.0
                            "Week 4" -> studentObject.week4Score = 100.0
                            "Week 5" -> studentObject.week5Score = 100.0
                            "Week 6" -> studentObject.week6Score = 100.0
                            "Week 7" -> studentObject.week7Score = 100.0
                            "Week 8" -> studentObject.week8Score = 100.0
                            "Week 9" -> studentObject.week9Score = 100.0
                            "Week 10" -> studentObject.week10Score = 100.0
                        }
                        ui.grade.text = 100.0.toString()
                    }


            } else if (ui.task1.isChecked || ui.task2.isChecked) {
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
                studentMarks.document("${studentObject.id}")
                    .update("$updateScore", 50.0)
                    .addOnSuccessListener {
                        when (week) {
                            "Week 1" -> studentObject.week1Score = 50.0
                            "Week 2" -> studentObject.week2Score = 50.0
                            "Week 3" -> studentObject.week3Score = 50.0
                            "Week 4" -> studentObject.week4Score = 50.0
                            "Week 5" -> studentObject.week5Score = 50.0
                            "Week 6" -> studentObject.week6Score = 50.0
                            "Week 7" -> studentObject.week7Score = 50.0
                            "Week 8" -> studentObject.week8Score = 50.0
                            "Week 9" -> studentObject.week9Score = 50.0
                            "Week 10" -> studentObject.week10Score = 50.0
                        }
                        ui.grade.text = 50.0.toString()
                    }

            }else {
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
                studentMarks.document("${studentObject.id}")
                    .update("$updateScore", 0.0)
                    .addOnSuccessListener {
                        when (week) {
                            "Week 1" -> studentObject.week1Score = 0.0
                            "Week 2" -> studentObject.week2Score = 0.0
                            "Week 3" -> studentObject.week3Score = 0.0
                            "Week 4" -> studentObject.week4Score = 0.0
                            "Week 5" -> studentObject.week5Score = 0.0
                            "Week 6" -> studentObject.week6Score = 0.0
                            "Week 7" -> studentObject.week7Score = 0.0
                            "Week 8" -> studentObject.week8Score = 0.0
                            "Week 9" -> studentObject.week9Score = 0.0
                            "Week 10" -> studentObject.week10Score = 0.0

                        }



                    }
            }
        }


        //https://www.youtube.com/watch?v=TcJysB6hp8Q
        val actionBar = supportActionBar
        actionBar!!.title = "Checkpoints  -  $week"

        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    //end
}