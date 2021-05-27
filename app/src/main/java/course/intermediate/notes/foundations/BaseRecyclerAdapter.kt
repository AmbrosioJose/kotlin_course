package course.intermediate.notes.foundations

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import course.intermediate.notes.tasks.TaskAdapter
import androidx.recyclerview.widget.DiffUtil

abstract class BaseRecyclerAdapter<T>(
    protected val masterList: MutableList<T> = mutableListOf()
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun onItemDeleted(indexInList: Int, indexInView: Int) {
        masterList.removeAt(indexInList)
        notifyItemRemoved(indexInView)
    }

    fun onItemUpdated(newItem: T, indexInList: Int, indexInView: Int){
        masterList[indexInList] = newItem
        notifyItemChanged(indexInView)
    }

    fun updateList(list: List<T>){
        // cleared master list then added updated one and lastly called notifyDataSetChanged
        // DiffUtil wasn't always working for new values.
//        val result = DiffUtil.calculateDiff(DiffUtilCallbackImpl(masterList, list))
        masterList.clear()
        masterList.addAll(list)
        notifyDataSetChanged()
//        result.dispatchUpdatesTo(this)
    }

    override fun getItemViewType(position: Int): Int = if(position == 0){
        TYPE_ADD_BUTTON
    } else {
        TYPE_INFO
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is AddButtonViewHolder){
            holder.onBind(Unit, position - 1)
        } else
            (holder as BaseViewHolder<T>).onBind(masterList[position - 1], position - 1)
    }

    override fun getItemCount(): Int = masterList.size + 1

    abstract class  BaseViewHolder<E>(val  view: View) : RecyclerView.ViewHolder(view){
        abstract  fun onBind(data: E, listIndex: Int)
    }

    abstract class AddButtonViewHolder(view: View): BaseViewHolder<Unit>(view)

    companion object{
        const val TYPE_ADD_BUTTON = 0
        const val TYPE_INFO = 1
    }

    class DiffUtilCallbackImpl<T>(private val oldList: List<T>, private val newList: List<T>): DiffUtil.Callback(){

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

    }
}