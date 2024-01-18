package fitness.travel.onxwjvbr.ui.my_exercise_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fitness.travel.onxwjvbr.R
import fitness.travel.onxwjvbr.databinding.FragmentMyExerciseListBinding
import fitness.travel.onxwjvbr.domain.FragmentName
import fitness.travel.onxwjvbr.ui.exercise_list.ExerciseListFragment
import fitness.travel.onxwjvbr.ui.my_exercise_list.rv.MyExerciseRVAdapter
import fitness.travel.onxwjvbr.ui.training_item.TrainingItemFragment
import fitness.travel.onxwjvbr.ui.training_list.TrainingListFragment

class MyExerciseListFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private val binding by lazy { FragmentMyExerciseListBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this)[MyExerciseListViewModel::class.java] }
    private val rvAdapter by lazy { MyExerciseRVAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFragmentName()
        setupSpinner()
        setupFabClickListeners()
        setupRecyclerView()
        setupRVAdapter()
        observeViewModel()
    }

    private fun setupFragmentName() {
        FragmentName.changeFragmentName(requireContext().getString(R.string.my_exercise_list))
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        viewModel.changeDayOfWeek(position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    private fun setupRecyclerView() {
        with(binding.myExercisesRV) {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                RecyclerView.VERTICAL,
                false
            )
        }
    }

    private fun setupRVAdapter() {
        rvAdapter.onBtnRemoveClickListener = {
            viewModel.removeMyExerciseItem(it)
        }
    }

    private fun observeViewModel() {
        viewModel.myExercisesListLD.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.myExercisesRV.visibility = View.VISIBLE
                binding.tvEmptyExerciseList.visibility = View.GONE
                rvAdapter.submitList(it)
                setupBtnPlayTrainingClickListener(true)
            } else {
                binding.tvEmptyExerciseList.visibility = View.VISIBLE
                binding.myExercisesRV.visibility = View.GONE
                setupBtnPlayTrainingClickListener(false)
            }

        }
        observeNewTraining()
    }

    private fun setupSpinner() {
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.days_of_week,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerDayOfWeek.adapter = adapter
        binding.spinnerDayOfWeek.onItemSelectedListener = this
    }

    private fun setupFabClickListeners() {
        setupBtnAddExerciseClickListener()
        setupBtnTrainingListClickListener()
    }

    private fun setupBtnAddExerciseClickListener() {
        binding.fabAddExercise.setOnClickListener {
            val dayOfWeek = binding.spinnerDayOfWeek.selectedItemPosition
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.mainConteiner, ExerciseListFragment.newInstance(dayOfWeek, 0))
                addToBackStack(null)
                commit()
            }
        }
    }

    private fun setupBtnTrainingListClickListener() {
        binding.fabTrainingList.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.mainConteiner, TrainingListFragment())
                addToBackStack(null)
                commit()
            }
        }
    }

    private fun setupBtnPlayTrainingClickListener(isReady: Boolean) {
        binding.fabStartTreining.setOnClickListener {
            if (isReady) {
                viewModel.startNewTraining()
            } else {
                Toast.makeText(
                    requireContext(),
                    requireContext().getString(R.string.empty_exercise_list),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

    private fun observeNewTraining() {
        viewModel.newTrainingId.observe(viewLifecycleOwner) { trainingId ->
            if (trainingId != null) {
                viewModel.clearNewTrainingId()
                parentFragmentManager.beginTransaction().apply {
                    replace(R.id.mainConteiner, TrainingItemFragment.newInstance(trainingId))
                    addToBackStack(null)
                    commit()
                }
            }

        }
    }

}