package course.intermediate.notes.create

import course.intermediate.notes.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_create.*
import course.intermediate.notes.navigation.NavigationActivity

class CreateActivity : AppCompatActivity() {
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        intent.getStringExtra(NavigationActivity.FRAGMENT_TYPE_KEY).run{
            textView.text = if(this == NavigationActivity.FRAGMENT_VALUE_TASK){
                "This is a task"
            } else if(this == NavigationActivity.FRAGMENT_VALUE_NOTE){
                "This is a Note"
            } else {
                "This is a blah"
            }
        }
//        textView.text = if(intent.getStringExtra(NavigationActivity.FRAGMENT_TYPE_KEY) == NavigationActivity.FRAGMENT_VALUE_TASK){
//            "This is a task"
//        } else if(intent.getStringExtra(Navigation)){
//            "This is a note"
//        }

    }
}