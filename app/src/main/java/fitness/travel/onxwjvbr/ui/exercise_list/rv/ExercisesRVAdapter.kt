package fitness.travel.onxwjvbr.ui.exercise_list.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import coil.transform.CircleCropTransformation
import fitness.travel.onxwjvbr.R
import fitness.travel.onxwjvbr.data.exercise.ExerciseItemDB
import fitness.travel.onxwjvbr.domain.ExerciseItemRemote
import fitness.travel.onxwjvbr.ui.exercise_list.API_HOST
import fitness.travel.onxwjvbr.ui.exercise_list.API_KEY
import fitness.travel.onxwjvbr.ui.firstCharToUpperCase
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONException
import java.io.IOException

class ExercisesRVAdapter : ListAdapter<ExerciseItemDB, ViewHolder>(ExercisesDiffCallBack()) {

    var onBtnAddClickListener: ((ExerciseItemDB) -> Unit)? = null
    var onItemClickListener: ((ExerciseItemDB) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
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
        if (holder is ExerciseViewHolder) {
            with(holder) {
                tvName.text = item.name.firstCharToUpperCase()
                tvBodyPart.text = item.bodyPart
                tvEquipment.text = item.equipment
                tvTarget.text = item.target
                tvSecondaryMuscles.text = item.getSecondaryMusclesString()
                btnAdd.setOnClickListener {
                    onBtnAddClickListener?.invoke(item)
                }
                holder.itemView.setOnClickListener {
                    onItemClickListener?.invoke(item)
                }
            }
        } else if (holder is ExerciseExpandedViewHolder) {
            with(holder) {
                tvName.text = item.name.firstCharToUpperCase()
                tvInstruction.text = item.getInstructionAsList()
                getImgSrc(item.remoteId, holder)
                holder.itemView.setOnClickListener {
                    onItemClickListener?.invoke(item)
                }
                holder.btnAdd.setOnClickListener {
                    onBtnAddClickListener?.invoke(item)
                }
            }
        }

    }



    private fun getImgSrc(exerciseId: String, holder: ExerciseExpandedViewHolder) {
        val url = "https://exercisedb.p.rapidapi.com/exercises/exercise/$exerciseId"
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .get()
            .addHeader("X-RapidAPI-Key", API_KEY)
            .addHeader("X-RapidAPI-Host", API_HOST)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val exercise = response.body?.string()?.let { convertJsonToExercise(it) }
                    if (exercise != null) {
                        holder.ivInstruction.load(exercise.gifUrl){
                            placeholder(R.mipmap.ic_launcher)
                            transformations(CircleCropTransformation())
                        }
                    }
                }
            }
        })

    }

    private fun convertJsonToExercise(inputString: String): ExerciseItemRemote? {
        return try {
            Json.decodeFromString<ExerciseItemRemote>(inputString)
        } catch (e: JSONException) {
            null
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return item.isExpanded
    }
}