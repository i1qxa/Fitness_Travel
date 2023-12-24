package fitness.travel.onxwjvbr.ui.my_exercise_list.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import fitness.travel.onxwjvbr.R
import fitness.travel.onxwjvbr.data.my_exercise.MyExerciseItemDB

class MyExerciseRVAdapter : ListAdapter<MyExerciseItemDB, MyExercisesVH>(
    MyExerciseDiffCallBack()
) {

    var onBtnRemoveClickListener: ((MyExerciseItemDB) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyExercisesVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MyExercisesVH(
            layoutInflater.inflate(
                R.layout.my_exercise_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyExercisesVH, position: Int) {
        val item = getItem(position)
        with(holder) {
            tvName.text = item.name
            tvBodyPart.text = item.bodyPart
            tvEquipment.text = item.equipment
            tvTarget.text = item.target
            tvSecondaryMuscles.text = item.getSecondaryMusclesString()
            btnRemove.setOnClickListener {
                onBtnRemoveClickListener?.invoke(item)
            }
        }
    }
}