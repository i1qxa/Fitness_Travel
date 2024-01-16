package fitness.travel.onxwjvbr.ui.training_item

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fitness.travel.onxwjvbr.R
import fitness.travel.onxwjvbr.databinding.FragmentTrainingItemBinding
import fitness.travel.onxwjvbr.domain.FragmentName
import fitness.travel.onxwjvbr.ui.training_item.full.ItemFullFragment
import fitness.travel.onxwjvbr.ui.training_item.rv.TrainingItemRVAdapter

private const val TRAINING_DAY = "day"

class TrainingItemFragment : Fragment() {
    private var trainingId: Int? = null
    private val binding by lazy { FragmentTrainingItemBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this)[TrainingItemViewModel::class.java] }
    private val rv by lazy { binding.rvTrainingItems }
    private val rvAdapter by lazy { TrainingItemRVAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            trainingId = it.getInt(TRAINING_DAY)
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
        viewModel.trainingIdLD.value = trainingId
        FragmentName.changeFragmentName(requireContext().getString(R.string.training))
        observeTimer()
        setupAdapter()
        setupRV()
        observeVM()
        setupBtnFinishClickListener()
        observeFinishTraining()
    }

    private fun observeFinishTraining() {
        viewModel.shouldFinishTraining.observe(viewLifecycleOwner) {
            if (it) {
                parentFragmentManager.popBackStack()
            }
        }
    }

    private fun observeTimer() {
        viewModel.timerLD.observe(viewLifecycleOwner) {
            binding.tvTimer.text = it
        }
    }

    private fun setupRV() {
        with(rv) {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                RecyclerView.VERTICAL,
                false
            )
        }
    }

    private fun setupAdapter() {
        rvAdapter.onBtnAddAmountClickListener = {
            val newFragment = ItemFullFragment.newInstance(
                it.trainingId,
                it.exerciseId,
                it.exerciseName
            )
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.mainConteiner, newFragment)
                addToBackStack(null)
                commit()
            }
        }
    }

    private fun observeVM() {
        viewModel.exerciseListLD.observe(viewLifecycleOwner) {
            rvAdapter.submitList(it)
        }
    }

    private fun setupBtnFinishClickListener() {
        binding.btnFinish.setOnClickListener {
            viewModel.finishTraining()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(trainingId: Int) =
            TrainingItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(TRAINING_DAY, trainingId)
                }
            }
    }
}