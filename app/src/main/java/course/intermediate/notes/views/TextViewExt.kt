package course.intermediate.notes.views

import android.widget.TextView
import android.graphics.Paint

fun TextView.setStrikeThrough(){
    paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
}

fun TextView.removeStrikeThrough(){
    paintFlags = paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
}