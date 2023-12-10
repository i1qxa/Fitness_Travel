package fitness.travel.onxwjvbr.ui.my_exercise_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.ViewModelProvider
import fitness.travel.onxwjvbr.R
import fitness.travel.onxwjvbr.databinding.FragmentMyExerciseListBinding

class MyExerciseListFragment : Fragment() {

    private val binding by lazy { FragmentMyExerciseListBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this)[MyExerciseListViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSpinner(binding.spinnerDayOfWeek, R.array.days_of_week)
    }


    private fun setupSpinner(spinner: Spinner, arrayId: Int) {
        val adapter = ArrayAdapter.createFromResource(
            requireContext(), arrayId,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }



}