package au.edu.utas.dengm.tutorialsmarkingapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import au.edu.utas.dengm.tutorialsmarkingapp.databinding.ActivityGradeHdBinding
import au.edu.utas.dengm.tutorialsmarkingapp.databinding.MyListItemBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.io.File

class GradeHdActivity : AppCompatActivity() {
        private lateinit var ui : ActivityGradeHdBinding
        private var week : String = ""

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            ui = ActivityGradeHdBinding.inflate(layoutInflater)
            setContentView(ui.root)

            ui.myList.adapter = GradeHdAdapter(students = items)

            //vertical list
            ui.myList.layoutManager = LinearLayoutManager(this)

            week = intent.getStringExtra(WEEK_KEY).toString()

            //https://www.youtube.com/watch?v=TcJysB6hp8Q
            val actionBar = supportActionBar
            actionBar!!.title= "Grade level  -  $week"

            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setDisplayHomeAsUpEnabled(true)

            Toast.makeText(this, "${items.size} students", Toast.LENGTH_SHORT).show()

        }

    inner class GradeHdHolder(var ui: MyListItemBinding) : RecyclerView.ViewHolder(ui.root) {}
    inner class GradeHdAdapter(private val students: MutableList<Student>) : RecyclerView.Adapter<GradeHdActivity.GradeHdHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeHdActivity.GradeHdHolder {
            val ui = MyListItemBinding.inflate(layoutInflater, parent, false)   //inflate a new row from the my_list_item.xml
            return GradeHdHolder(ui)
        }

        override fun onBindViewHolder(holder: GradeHdHolder, position: Int) {
            val student = students[position] //get the data at the requested position
            holder.ui.txtID.text = student.studentid.toString()
            holder.ui.txtName.text = student.studentname.toString()
            holder.ui.imageView.setImageURI(Uri.fromFile(File(student.photo.toString())))

            holder.ui.root.setOnClickListener {
                var i = Intent(holder.ui.root.context, ChooseHdActivity::class.java)
                i.putExtra(Student_INDEX, position)
                i.putExtra(WEEK_KEY, week)
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
        //end
    }