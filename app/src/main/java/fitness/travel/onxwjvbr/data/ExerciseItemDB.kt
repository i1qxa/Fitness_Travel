package fitness.travel.onxwjvbr.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ExerciseItemDB")
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
    val isExpanded:Int,
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
    fun getSecondaryMusclesString() = secondaryMuscles.replace("[", "").replace("]", "")

    fun getInstructionAsList():String{
        val instructionsSB =StringBuilder()
        var counter = 1
        instructions.replace("[", "").replace("]", "").split(".,").map {
            instructionsSB.append("$counter) ")
            instructionsSB.append(it)
            instructionsSB.append("\n")
            counter++
        }
        return instructionsSB.toString()
    }
}
