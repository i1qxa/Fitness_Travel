package fitness.travel.onxwjvbr.ui.exercise_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fitness.travel.onxwjvbr.R
import fitness.travel.onxwjvbr.databinding.FragmentExerciseListBinding
import fitness.travel.onxwjvbr.ui.exercise_list.rv.ExercisesRVAdapter
import kotlinx.coroutines.launch

private const val DAY_OF_WEEK = "day_of_week"

class ExerciseListFragment : Fragment(), AdapterView.OnItemSelectedListener {
    var dyaOfWeek: Int? = null
    private val binding by lazy { FragmentExerciseListBinding.inflate(layoutInflater) }
    private val rvAdapter by lazy { ExercisesRVAdapter() }
    private val viewModel by lazy { ViewModelProvider(this)[ExerciseListViewModel::class.java] }
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
        setupSpinnerBodyPart()
        observeExerciseList()
    }

    private fun observeExerciseList() {
        viewModel.exercisesList.observe(viewLifecycleOwner) {
            rvAdapter.submitList(it)
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        viewModel.setBodyPart(position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    private fun setupRVAdapter() {
        with(rvAdapter) {
            onBtnAddClickListener = {
                viewModel.addExerciseToMyList(it, dyaOfWeek ?: 1)
            }
            onItemClickListener = {
                viewModel.changeExpanded(it)
            }
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvExercises) {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                RecyclerView.VERTICAL,
                false
            )
        }
    }

    private fun setupSpinnerBodyPart() {
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.body_part,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerBodyPart.adapter = adapter
        binding.spinnerBodyPart.onItemSelectedListener = this

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