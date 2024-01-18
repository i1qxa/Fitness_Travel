package fitness.travel.onxwjvbr.ui.training_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fitness.travel.onxwjvbr.R
import fitness.travel.onxwjvbr.databinding.FragmentTrainingListBinding
import fitness.travel.onxwjvbr.domain.FragmentName
import fitness.travel.onxwjvbr.ui.training_chart.TrainingChartFragment
import fitness.travel.onxwjvbr.ui.training_item.TrainingItemFragment
import fitness.travel.onxwjvbr.ui.training_list.rv.TrainingRVAdapter

class TrainingListFragment : Fragment() {

    private val binding by lazy { FragmentTrainingListBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this)[TrainingListViewModel::class.java] }
    private val rv by lazy { binding.rvTrainingList }
    private val rvAdapter by lazy { TrainingRVAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FragmentName.changeFragmentName(requireContext().getString(R.string.training_list))
        setupHeader()
        setupRV()
        observeVM()
        setupAdapter()
        setupBtnChartCLickListener()
        setupBtnStartTrainingClickListener()
    }


    private fun setupAdapter(){
        rvAdapter.onItemClickListener = {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.mainConteiner, TrainingItemFragment.newInstance(it.id.toInt()))
                addToBackStack(null)
                commit()
            }
        }
    }

    private fun setupHeader(){
        with(binding.trainingHeader){
            tvTrainingDate.text = requireContext().getString(R.string.training_date)
            tvTrainingDuration.text = requireContext().getString(R.string.training_duration)
            tvTrainingDay.text = requireContext().getString(R.string.training_day)
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

    private fun observeVM(){
        viewModel.trainingList.observe(viewLifecycleOwner){
            if (it.isNotEmpty()){
                rvAdapter.submitList(it)
                binding.rvTrainingList.visibility = View.VISIBLE
                binding.tvEmptyTrainingList.visibility = View.GONE
                binding.btnChart.visibility = View.VISIBLE
            }else{
                binding.rvTrainingList.visibility = View.GONE
                binding.tvEmptyTrainingList.visibility = View.VISIBLE
                binding.btnChart.visibility = View.GONE
            }
        }
    }

    private fun setupBtnChartCLickListener(){
        binding.btnChart.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.mainConteiner, TrainingChartFragment())
                addToBackStack(null)
                commit()
            }
        }
    }

    private fun setupBtnStartTrainingClickListener(){
        binding.btnStartTraining.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.mainConteiner, TrainingItemFragment())
                addToBackStack(null)
                commit()
            }
        }
    }

}