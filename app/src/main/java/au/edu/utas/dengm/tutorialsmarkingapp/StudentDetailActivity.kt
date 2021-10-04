package au.edu.utas.dengm.tutorialsmarkingapp

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import au.edu.utas.dengm.tutorialsmarkingapp.databinding.ActivityStudentDetailBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.io.File

class StudentDetailActivity : AppCompatActivity() {
    private lateinit var ui: ActivityStudentDetailBinding
    private var ID: String = ""
    private var studentposition: Int=-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityStudentDetailBinding.inflate(layoutInflater)
        setContentView(ui.root)

        studentposition = intent.getIntExtra(Student_INDEX, -1)
        var studentObject = items[studentposition]
        ID = studentObject.id.toString()

        ui.txtID.setText(studentObject.studentid.toString())
        ui.txtName.setText(studentObject.studentname.toString())
        ui.myImageView.setImageURI(Uri.fromFile(File(studentObject.photo.toString())))

        val db = Firebase.firestore
        val studentMarks = db.collection("students")
        ui.week1.text = studentObject.week1Score.toString()
        ui.week2.text = studentObject.week2Score.toString()
        ui.week3.text = studentObject.week3Score.toString()
        ui.week4.text = studentObject.week4Score.toString()
        ui.week5.text = studentObject.week5Score.toString()
        ui.week6.text = studentObject.week6Score.toString()
        ui.week7.text = studentObject.week7Score.toString()
        ui.week8.text = studentObject.week8Score.toString()
        ui.week9.text = studentObject.week9Score.toString()
        ui.week10.text = studentObject.week10Score.toString()


        ui.btSave.setOnClickListener {
            //get the user input
            studentObject.studentid = ui.txtID.text.toString().toInt()
            studentObject.studentname = ui.txtName.text.toString() //good code would check this is really an int


            //update the database

            val db = Firebase.firestore
            val studentsCollection = db.collection("students")
            studentsCollection.document(studentObject.id!!)
                .set(studentObject)
                .addOnSuccessListener {
                    Log.d(FIREBASE_TAG, "Successfully updated student ${studentObject?.id}")
                    //return to the list
                    finish()
                }
        }

        //https://www.youtube.com/watch?v=TcJysB6hp8Q
        val actionBar = supportActionBar
        actionBar!!.title="Student Details"

        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    //end

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.delete_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        val id = item.getItemId()


        if (id == R.id.delete_student) {

            val db = Firebase.firestore
            val studentsCollection = db.collection("students")


                    //https://www.tutorialkart.com/kotlin-android/android-alert-dialog-example/
                    val dialogBuilder = AlertDialog.Builder(this)

                    // set message of alert dialog
                    dialogBuilder.setMessage("Do you want to DELETE this student ?")
                        // if the dialog is cancelable
                        .setCancelable(false)
                        // positive button text and action
                        .setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface: DialogInterface, i: Int ->
                            studentsCollection.document(ID)
                                    .delete()
                                    .addOnSuccessListener {
                                        Log.d(FIREBASE_TAG, "DocumentSnapshot successfully deleted!")
                                        items.removeAt(studentposition)
                                        finish()
                                    }
                                    .addOnFailureListener { e -> Log.w(FIREBASE_TAG, "Error deleting document", e) }

                        })
                        // negative button text and action
                        .setNegativeButton("No", DialogInterface.OnClickListener {
                                dialog, id -> dialog.cancel()
                        })

                    // create dialog box
                    val alert = dialogBuilder.create()
                    // set title for alert dialog box
                    alert.setTitle("Delete Warning")
                    // show alert dialog
                    alert.show()
                    }

        if (id == R.id.share) {
            var sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                studentposition = intent.getIntExtra(Student_INDEX, -1)
                var studentObject = items[studentposition]
                putExtra(Intent.EXTRA_TEXT,
                    "ID: ${studentObject.studentid.toString()}, " +
                            "Name: ${studentObject.studentname.toString()}, " +
                            "Week 1: ${studentObject.week1Score.toString()}, " +
                            "Week 2: ${studentObject.week2Score.toString()}，" +
                            "Week 3: ${studentObject.week3Score.toString()}，" +
                            "Week 4: ${studentObject.week4Score.toString()}，" +
                            "Week 5: ${studentObject.week5Score.toString()}，" +
                            "Week 6: ${studentObject.week6Score.toString()}，" +
                            "Week 7: ${studentObject.week7Score.toString()}，" +
                            "Week 8: ${studentObject.week8Score.toString()}，" +
                            "Week 9: ${studentObject.week9Score.toString()}，" +
                            "Week 10: ${studentObject.week10Score.toString()}")
                type = "text/plain"
            }
            startActivity(Intent.createChooser(sendIntent, "Share via..."))
        }
        if (intent?.action == Intent.ACTION_SEND && intent?.type != null)
        {
            if (intent?.type == "text/plain")
            {
                var sharedText = intent.getStringExtra(Intent.EXTRA_TEXT) ?: ""
                //do something with sharedText
                ui.lblSharedText.text = "Recieved: $sharedText"
            }
        }

        return super.onOptionsItemSelected(item)
    }

}
