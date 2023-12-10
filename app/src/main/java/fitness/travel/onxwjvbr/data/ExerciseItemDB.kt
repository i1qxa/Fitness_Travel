package fitness.travel.onxwjvbr.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExerciseItemDB(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val bodyPart:String,
    val equipment:String,
    val gifUrl:String,
    val remoteId:String,
    val name:String,
    val target:String,
    val secondaryMuscles:String,
    val instructions:String,
){
    fun toMyExerciseDBItem(day:Int) = MyExerciseItemDB(
        id = 0,
        bodyPart = bodyPart,
        equipment = equipment,
        gifUrl = gifUrl,
        remoteId = remoteId,
        name = name,
        target = target,
        secondaryMuscles = secondaryMuscles,
        instructions = instructions,
        dayOfWeek = day,
    )
}
