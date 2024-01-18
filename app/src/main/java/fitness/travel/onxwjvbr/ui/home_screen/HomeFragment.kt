package fitness.travel.onxwjvbr.ui.home_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fitness.travel.onxwjvbr.R
import fitness.travel.onxwjvbr.databinding.FragmentHomeBinding
import fitness.travel.onxwjvbr.domain.FragmentName
import fitness.travel.onxwjvbr.ui.my_exercise_list.MyExerciseListFragment
import fitness.travel.onxwjvbr.ui.training_chart.TrainingChartFragment
import fitness.travel.onxwjvbr.ui.training_list.TrainingListFragment

class HomeFragment : Fragment() {

    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFragmentName()
        setupBtnClickListeners()
    }

    private fun setupFragmentName(){
        FragmentName.changeFragmentName(requireContext().getString(R.string.home))
    }

    private fun setupBtnClickListeners(){
        with(binding){
            ivChart.setOnClickListener {
                launchFragment(TrainingChartFragment())
            }
            ivWorkout.setOnClickListener {
                launchFragment(TrainingListFragment())
            }
            ivTrainingPlan.setOnClickListener {
                launchFragment(MyExerciseListFragment())
            }
        }
    }

    private fun launchFragment(fragment:Fragment){
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.mainConteiner, fragment)
            addToBackStack(null)
            commit()
        }
    }

}