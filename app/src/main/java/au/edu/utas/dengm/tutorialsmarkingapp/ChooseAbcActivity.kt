package au.edu.utas.dengm.tutorialsmarkingapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import au.edu.utas.dengm.tutorialsmarkingapp.databinding.ActivityChooseAbcBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.io.File

class ChooseAbcActivity : AppCompatActivity() {
    private lateinit var ui: ActivityChooseAbcBinding
    private var week : String = ""
    private var studentposition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityChooseAbcBinding.inflate(layoutInflater)
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
        if (score == 100.0) {
            ui.grade.text = "A"
        } else if (score == 80.0) {
            ui.grade.text = "B"
        } else if (score == 70.0) {
            ui.grade.text = "C"
        } else if (score == 60.0) {
            ui.grade.text = "D"
        }else {
            ui.grade.text = "F"
        }

        ui.saveButton.setOnClickListener {
            var grade: Int = ui.radiohd.checkedRadioButtonId
            var score = 0.00

            val db = Firebase.firestore
            val studentMarks = db.collection("students")

            when (grade) {
                R.id.a -> score = 100.00
                R.id.b ->  score = 80.00
                R.id.c ->  score = 70.00
                R.id.d ->  score = 60.00
                R.id.nn ->  score = 0.00
            }

            var updateScore = ""
            if (grade != -1) {
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
                    .update("$updateScore", score)
                    .addOnSuccessListener {
                        when (week) {
                            "Week 1" -> studentObject.week1Score = score
                            "Week 2" -> studentObject.week2Score = score
                            "Week 3" -> studentObject.week3Score = score
                            "Week 4" -> studentObject.week4Score = score
                            "Week 5" -> studentObject.week5Score = score
                            "Week 6" -> studentObject.week6Score = score
                            "Week 7" -> studentObject.week7Score = score
                            "Week 8" -> studentObject.week8Score = score
                            "Week 9" -> studentObject.week9Score = score
                            "Week 10" -> studentObject.week10Score = score
                        }
                        if (score == 100.0) {
                            ui.grade.text = "A"
                        } else if (score == 80.0) {
                            ui.grade.text = "B"
                        } else if (score == 70.0) {
                            ui.grade.text = "C"
                        } else if (score == 60.0) {
                            ui.grade.text = "D"
                        }else {
                            ui.grade.text = "F"
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
                studentMarks.document("${studentObject.id}")
                    .update("$updateScore", 0.00)
                    .addOnSuccessListener {
                        when (week) {
                            "Week 1" -> studentObject.week1Score = 0.00
                            "Week 2" -> studentObject.week2Score = 0.00
                            "Week 3" -> studentObject.week3Score = 0.00
                            "Week 4" -> studentObject.week4Score = 0.00
                            "Week 5" -> studentObject.week5Score = 0.00
                            "Week 6" -> studentObject.week6Score = 0.00
                            "Week 7" -> studentObject.week7Score = 0.00
                            "Week 8" -> studentObject.week8Score = 0.00
                            "Week 9" -> studentObject.week9Score = 0.00
                            "Week 10" -> studentObject.week10Score = 0.00

                        }

                    }
            }
        }


        //https://www.youtube.com/watch?v=TcJysB6hp8Q
        val actionBar = supportActionBar
        actionBar!!.title = "Choose Grade  -  $week"

        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    //end
}