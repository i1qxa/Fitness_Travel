package fitness.travel.onxwjvbr.domain

import kotlinx.serialization.Serializable

@Serializable
data class ExerciseItemRemote(
    val bodyPart:String,
    val equipment:String,
    val gifUrl:String,
    val id:String,
    val name:String,
    val target:String,
    val secondaryMuscles:List<String>,
    val instructions:List<String>,
)
