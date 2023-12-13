package fitness.travel.onxwjvbr.ui.exercise_item

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fitness.travel.onxwjvbr.databinding.FragmentExerciseItemBinding

const val EXERCISE_ID = "exercise_id"
class ExerciseItemFragment : Fragment() {
    private var exerciseId: Int? = null
    private val binding by lazy { FragmentExerciseItemBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            exerciseId = it.getInt(EXERCISE_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(exerciseId: Int) =
            ExerciseItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(EXERCISE_ID, exerciseId)
                }
            }
    }
}