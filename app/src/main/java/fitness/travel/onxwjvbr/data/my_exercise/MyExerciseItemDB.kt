package fitness.travel.onxwjvbr.data.my_exercise

import androidx.room.Entity
import androidx.room.PrimaryKey
import fitness.travel.onxwjvbr.domain.ExerciseItem

@Entity(tableName = "MyExerciseItemDB")
data class MyExerciseItemDB(
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
    val dayOfWeek:Int,
){
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


//    fun toExerciseItem():ExerciseItem{
//        val secondaryMusclesString = secondaryMuscles.replace("[", "").replace("]", "")
//        val instructionsSB =StringBuilder()
//        var counter = 1
//        instructions.replace("[", "").replace("]", "").split(".,").map {
//            instructionsSB.append("$counter) ")
//            instructionsSB.append(it)
//            instructionsSB.append("\n")
//            counter++
//        }
//        return ExerciseItem(
//            id = id,
//            bodyPart = bodyPart,
//            equipment = equipment,
//            gifUrl = gifUrl,
//            name = name,
//            target = target,
//            secondaryMuscles = secondaryMusclesString,
//            instructions = instructionsSB.toString(),
//        )
//    }
}
