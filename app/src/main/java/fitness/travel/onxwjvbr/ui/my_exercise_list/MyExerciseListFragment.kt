package fitness.travel.onxwjvbr.ui.my_exercise_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fitness.travel.onxwjvbr.R
import fitness.travel.onxwjvbr.databinding.FragmentMyExerciseListBinding

class MyExerciseListFragment : Fragment() {

    private val binding by lazy { FragmentMyExerciseListBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }


}