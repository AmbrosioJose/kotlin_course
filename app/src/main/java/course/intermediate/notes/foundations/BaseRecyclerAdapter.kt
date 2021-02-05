package course.intermediate.notes.foundations

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T>(
    protected val list: MutableList<T> = mutableListOf()
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BaseViewHolder<T>).onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    abstract class  BaseViewHolder<E>(val  view: View) : RecyclerView.ViewHolder(view){
        abstract  fun onBind(data: E)
    }
}