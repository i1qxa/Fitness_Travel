package fitness.travel.onxwjvbr.domain

data class TrainingItemWithName(
    val id:Int,
    val trainingId:Int,
    val exerciseId:Int,
    val exerciseName:String,
    val weight:Int,
    val amountRepeat:Int,
    val duration:Long,
)
