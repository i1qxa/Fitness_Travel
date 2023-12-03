package fitness.travel.onxwjvbr.domain

data class ExerciseItem(
    val id:Int,
    val bodyPart:String,
    val equipment:String,
    val gifUrl:String,
    val name:String,
    val target:String,
    val secondaryMuscles:String,
    val instructions:String,
){
    var isCollapsed = true
    fun changeColapsed(){
        isCollapsed = !isCollapsed
    }
}
