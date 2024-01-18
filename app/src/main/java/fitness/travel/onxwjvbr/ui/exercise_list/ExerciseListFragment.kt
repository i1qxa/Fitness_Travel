package fitness.travel.onxwjvbr.ui.exercise_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fitness.travel.onxwjvbr.R
import fitness.travel.onxwjvbr.databinding.FragmentExerciseListBinding
import fitness.travel.onxwjvbr.domain.FragmentName
import fitness.travel.onxwjvbr.ui.exercise_list.rv.ExercisesRVAdapter

private const val DAY_OF_WEEK = "day_of_week"
private const val TRAINING_ID = "training_id"
const val API_KEY = "1a29ccc9c9mshed9e4da7e217435p1efa8ajsned0632a69413"
const val API_HOST = "exercisedb.p.rapidapi.com"

class ExerciseListFragment : Fragment(), AdapterView.OnItemSelectedListener {
    var dyaOfWeek: Int? = null
    var trainingId:Long? = null
    private val binding by lazy { FragmentExerciseListBinding.inflate(layoutInflater) }
    private val rvAdapter by lazy { ExercisesRVAdapter() }
    private val viewModel by lazy { ViewModelProvider(this)[ExerciseListViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dyaOfWeek = it.getInt(DAY_OF_WEEK)
            trainingId = it.getLong(TRAINING_ID)
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
        setupFragmentName()
        setupRecyclerView()
        setupRVAdapter()
        setupSpinnerBodyPart()
        observeExerciseList()
        observeToast()
    }

    private fun observeToast() {
        viewModel.toastLD.observe(viewLifecycleOwner) {
            if (it){
                Toast.makeText(
                    requireContext(),
                    requireContext().getString(R.string.exercise_added_toast),
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.resetToast()
            }

        }
    }

    private fun setupFragmentName() {
        FragmentName.changeFragmentName(requireContext().getString(R.string.exercise_list))
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
                viewModel.addExerciseToMyList(it, dyaOfWeek?:1, trainingId?.toInt())
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
        fun newInstance(dayOfWeek: Int, trainingId:Long?) =
            ExerciseListFragment().apply {
                arguments = Bundle().apply {
                    putInt(DAY_OF_WEEK, dayOfWeek)
                    putLong(TRAINING_ID, trainingId?:0)
                }
            }
    }
}