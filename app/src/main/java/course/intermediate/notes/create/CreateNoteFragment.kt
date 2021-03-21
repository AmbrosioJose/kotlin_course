package course.intermediate.notes.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import course.intermediate.notes.R
import android.content.Context
import course.intermediate.notes.foundations.NullFieldChecker
import kotlinx.android.synthetic.main.fragment_create_note.*
import course.intermediate.notes.models.Note
import javax.inject.Inject
import toothpick.Toothpick
import course.intermediate.notes.foundations.ApplicationScope
import course.intermediate.notes.notes.INoteModel

/**
 * A simple [Fragment] subclass.
 * Use the [CreateNoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateNoteFragment : Fragment(), NullFieldChecker {

    private var listener: OnFragmentInteractionListener? = null

    @Inject
    lateinit var model: INoteModel

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        Toothpick.inject(this, ApplicationScope.scope)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_note, container, false)
    }

    fun saveNote(callback: (Boolean) -> Unit){
        createNote()?.let{
            model.addNote(it){
                callback.invoke(true)
            }
        } ?: callback.invoke(false)
    }

    private fun createNote(): Note? {
        return if(!hasNullField())
             Note(description = noteEditText.editableText.toString())
        else
            return null
    }

    override fun onAttach(context: Context){
        super.onAttach(context)
        if(context is OnFragmentInteractionListener){
            listener = context
        } else {
            throw RuntimeException("$context must Implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun hasNullField(): Boolean = noteEditText.editableText.isNullOrEmpty()


    companion object {
        fun newInstance(): CreateNoteFragment {
            return CreateNoteFragment()
        }
    }

    interface OnFragmentInteractionListener{
        fun onFragmentInteraction()
    }

}