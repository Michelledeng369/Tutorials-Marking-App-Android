# Tutorials-Marking-App-Android

This is a Tutorial Marks App developed in Android studio. 

The application has three sections: 
1. Classlist - mainly used for student management (student addition, editing, deletion and student details view and share); 
2. Attendance - this section is to mark the student’s performance on the tutorial with 5 marking schemes: a. checkbox attendance; b. multiple checkpoints; c. enter score; d. Grade level (HD+/HD/DN/CR/PP/NN), and f. Grade Level (A/B/C/D/F), only one scheme can be used each week; 
3. Report - shows the grades of all students according to the week with the average score. The development of the application follows usability goals and design principles, which can basically meet tutor's marking requirements on the Tutorial.
4. Use Firebase as the database.

Note: the application is tested on Pixel 4 API 30 (1080 * 2280: 440dpi)

The search function is achieved but it cannot be associated with the recycle view because it uses a temporary variable "searchStu" instead of “students” to store student details. Therefore, for search results, no matter who the user clicks, position=0 is always returned, which is the first student.
