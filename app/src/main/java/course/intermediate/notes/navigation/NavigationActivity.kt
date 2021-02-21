package course.intermediate.notes.navigation

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import course.intermediate.notes.R
import course.intermediate.notes.notes.NotesListFragment
import course.intermediate.notes.tasks.TasksListFragment
import kotlinx.android.synthetic.main.activity_navigation.*
import android.content.Intent
import course.intermediate.notes.create.CreateActivity

class NavigationActivity : AppCompatActivity(), TasksListFragment.TouchActionDelegate, NotesListFragment.TouchActionDelegate {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_tasks -> {
                replaceFragment(TasksListFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notes -> {
                replaceFragment(NotesListFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        replaceFragment(TasksListFragment.newInstance())
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun gotToCreateActivity(fragmentValue: String){
        val intent: Intent = Intent(this, CreateActivity::class.java)
        intent.apply {
            putExtra(FRAGMENT_TYPE_KEY, fragmentValue)
        }
        startActivity(intent)
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentHolder, fragment)
            .commit()
    }

    override fun onAddButtonClicked(value: String){
        gotToCreateActivity(value)
    }


    companion object{
        const val FRAGMENT_TYPE_KEY = "f_t_k"
        const val FRAGMENT_VALUE_NOTE = "f_v_n"
        const val FRAGMENT_VALUE_TASK = "f_v_t"
    }
}
