package au.edu.utas.dengm.tutorialsmarkingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import au.edu.utas.dengm.tutorialsmarkingapp.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

val items = mutableListOf<Student>()
class MainActivity : AppCompatActivity() {

    private lateinit var ui : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)

        Toast.makeText(this, "Loading......", Toast.LENGTH_SHORT).show()
        ui.btclasslist.isEnabled=false
        ui.btattendance.isEnabled=false
        ui.btreport.isEnabled=false

        ui.btclasslist.setOnClickListener {
            var intent = Intent(this, ClasslistActivity::class.java)
            startActivity(intent)
        }
        ui.btattendance.setOnClickListener {
            var intent = Intent(this, AttendanceActivity::class.java)
            startActivity(intent)
        }
        ui.btreport.setOnClickListener {
            var intent = Intent(this, ReportActivity::class.java)
            startActivity(intent)
        }

        val db = Firebase.firestore
        val studentsCollection = db.collection("students")

        studentsCollection
            .get()
            .addOnSuccessListener { result ->
                Log.d(FIREBASE_TAG, "--- all students ---")
                items.clear()
                for (document in result)
                {
                    //Log.d(FIREBASE_TAG, document.toString())
                    val student = document.toObject<Student>()
                    student.id = document.id
                    Log.d(FIREBASE_TAG, student.toString())

                    items.add(student)
                    //(ui.myList.adapter as CheckboxActivity.StudentAdapter).notifyDataSetChanged()
                }
                Toast.makeText(this, "Loading successful", Toast.LENGTH_SHORT).show()
                ui.btclasslist.isEnabled=true
                ui.btattendance.isEnabled=true
                ui.btreport.isEnabled = true
            }
    }
}