package au.edu.utas.dengm.tutorialsmarkingapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import au.edu.utas.dengm.tutorialsmarkingapp.databinding.ActivityClasslistBinding
import au.edu.utas.dengm.tutorialsmarkingapp.databinding.ActivityStudentDetailBinding
import au.edu.utas.dengm.tutorialsmarkingapp.databinding.MyListItemBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

const val Student_INDEX = "Student_Index"


class ClasslistActivity : AppCompatActivity() {
    private lateinit var ui: ActivityClasslistBinding
    var searchStu = mutableListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityClasslistBinding.inflate(layoutInflater)
        setContentView(ui.root)


        ui.myList.adapter = StudentAdapter(students = items)

        //vertical list
        ui.myList.layoutManager = LinearLayoutManager(this)

        ui.searchButton.setOnClickListener {
            var searchText = ui.search.text.toString()
            if (ui.search.text.isNullOrBlank()) {
                ui.myList.adapter = StudentAdapter(students = items)
                ui.myList.adapter?.notifyDataSetChanged()
            } else {
                for (student in items) {
                    if (student.studentid!!.toString()
                            .contains(searchText) || student.studentname!!.toLowerCase()
                            .contains(searchText.toLowerCase())
                    ) {
                        searchStu.add(student)
                    }
                }
                ui.myList.adapter = StudentAdapter(students = searchStu)
                ui.myList.adapter?.notifyDataSetChanged()
            }
        }

        //https://www.youtube.com/watch?v=TcJysB6hp8Q
        val actionBar = supportActionBar
        actionBar!!.title = "Classlist"

        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

        val db = Firebase.firestore
        val studentsCollection = db.collection("students")

        studentsCollection
            .get()
            .addOnSuccessListener { result ->
                Log.d(FIREBASE_TAG, "--- all students ---")
                items.clear()
                for (document in result) {
                    //Log.d(FIREBASE_TAG, document.toString())
                    val student = document.toObject<Student>()
                    student.id = document.id
                    Log.d(FIREBASE_TAG, student.toString())

                    items.add(student)
                    (ui.myList.adapter as StudentAdapter).notifyDataSetChanged()
                }
                Toast.makeText(this, "${items.size} students", Toast.LENGTH_SHORT).show()
            }


    }


    inner class StudentHolder(var ui: MyListItemBinding) : RecyclerView.ViewHolder(ui.root) {}

    inner class StudentAdapter(private val students: MutableList<Student>) : RecyclerView.Adapter<StudentHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ClasslistActivity.StudentHolder {
            val ui = MyListItemBinding.inflate(
                layoutInflater,
                parent,
                false
            )   //inflate a new row from the my_list_item.xml
            return StudentHolder(ui)
        }

        override fun onBindViewHolder(holder: StudentHolder, position: Int) {
            val student = students[position] //get the data at the requested position
            holder.ui.txtID.text = student.studentid.toString()
            holder.ui.txtName.text = student.studentname.toString()
            holder.ui.imageView.setImageURI(Uri.fromFile(File(student.photo.toString())))

            holder.ui.root.setOnClickListener {
                var i = Intent(holder.ui.root.context, StudentDetailActivity::class.java)
                i.putExtra(Student_INDEX, position)
                startActivity(i)
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        val id = item.getItemId()

        if (id == R.id.add_student) {
            var intent = Intent(this, AddStudentActivity::class.java)
            startActivity(intent)
            return true
        }

        return super.onOptionsItemSelected(item)

    }

    override fun onResume() {
        super.onResume()

        ui.myList.adapter?.notifyDataSetChanged()

    }


}