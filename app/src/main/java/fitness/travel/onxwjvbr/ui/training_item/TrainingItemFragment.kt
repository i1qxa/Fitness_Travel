package fitness.travel.onxwjvbr.ui.training_item

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fitness.travel.onxwjvbr.R
import fitness.travel.onxwjvbr.databinding.FragmentTrainingItemBinding

private const val TRAINING_DAY = "day"
class TrainingItemFragment : Fragment() {
    private var trainingDay: String? = null
     private val binding by lazy { FragmentTrainingItemBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            trainingDay = it.getString(TRAINING_DAY)
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
        fun newInstance(day: String) =
            TrainingItemFragment().apply {
                arguments = Bundle().apply {
                    putString(TRAINING_DAY, day)
                }
            }
    }
}