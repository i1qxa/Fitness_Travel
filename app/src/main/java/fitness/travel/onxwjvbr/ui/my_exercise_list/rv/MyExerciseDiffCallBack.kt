package fitness.travel.onxwjvbr.ui.my_exercise_list.rv

import androidx.recyclerview.widget.DiffUtil
import fitness.travel.onxwjvbr.data.my_exercise.MyExerciseItemDB

class MyExerciseDiffCallBack: DiffUtil.ItemCallback<MyExerciseItemDB>() {

    override fun areItemsTheSame(oldItem: MyExerciseItemDB, newItem: MyExerciseItemDB): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MyExerciseItemDB, newItem: MyExerciseItemDB): Boolean {
        return oldItem == newItem
    }
}