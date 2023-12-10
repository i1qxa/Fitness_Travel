package fitness.travel.onxwjvbr.ui.exercise_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import fitness.travel.onxwjvbr.databinding.FragmentExerciseListBinding
import fitness.travel.onxwjvbr.ui.exercise_list.rv.ExercisesRVAdapter
import kotlinx.coroutines.launch

private const val DAY_OF_WEEK = "day_of_week"

class ExerciseListFragment : Fragment() {
    var dyaOfWeek: Int? = null
    private val binding by lazy { FragmentExerciseListBinding.inflate(layoutInflater) }
    private val rvAdapter by lazy { ExercisesRVAdapter() }
    private val viewModel by lazy { ViewModelProvider(requireContext())[ExerciseListViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dyaOfWeek = it.getInt(DAY_OF_WEEK)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupRVAdapter()
    }

    private fun setupRVAdapter() {
        with(rvAdapter) {
            onBtnAddClickListener = {
                viewModel.addExerciseToMyList(it,dyaOfWeek)
            }
            onItemClickListener = {
                TODO("Realise opening ExerciseItem")
            }
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvExercises) {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(dayOfWeek: Int) =
            ExerciseListFragment().apply {
                arguments = Bundle().apply {
                    putInt(DAY_OF_WEEK, dayOfWeek)
                }
            }
    }
}