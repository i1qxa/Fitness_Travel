package fitness.travel.onxwjvbr.ui.exercise_list.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import fitness.travel.onxwjvbr.R
import fitness.travel.onxwjvbr.data.ExerciseItemDB

class ExercisesRVAdapter:ListAdapter<ExerciseItemDB, ViewHolder>(ExercisesDiffCallBack()) {

    var onBtnAddClickListener : ((ExerciseItemDB) -> Unit)? = null
    var onItemClickListener : ((ExerciseItemDB) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when(viewType){
            0 -> ExerciseViewHolder(
                layoutInflater.inflate(
                    R.layout.exercise_item,
                    parent,
                    false
                )
            )
            else -> ExerciseExpandedViewHolder(
                layoutInflater.inflate(
                    R.layout.exercise_item_expanded,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (holder is ExerciseViewHolder){
            with(holder){
                tvName.text = item.name
                tvBodyPart.text = item.bodyPart
                tvEquipment.text = item.equipment
                tvTarget.text = item.target
                tvSecondaryMuscles.text = item.getSecondaryMusclesString()
                btnAdd.setOnClickListener {
                    onBtnAddClickListener?.invoke(item)
                }
                btnExpand.setOnClickListener {
                    onItemClickListener?.invoke(item)
                }
            }
        }else if (holder is ExerciseExpandedViewHolder){
            with(holder){
                tvName.text = item.name
                tvInstruction.text=item.getInstructionAsList()
                ivInstruction.load("https://v2.exercisedb.io/image/ekSNP6QmE-Btlu")
                TODO("Need to realise downloading gif from api")
                btnExpand.setOnClickListener {
                    onItemClickListener?.invoke(item)
                }
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return item.isExpanded
    }
}