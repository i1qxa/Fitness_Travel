package fitness.travel.onxwjvbr.ui.training_item.full

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import fitness.travel.onxwjvbr.R
import fitness.travel.onxwjvbr.databinding.FragmentTrainingItemFullBinding
import fitness.travel.onxwjvbr.domain.ExerciseItemRemote
import fitness.travel.onxwjvbr.ui.exercise_list.API_HOST
import fitness.travel.onxwjvbr.ui.exercise_list.API_KEY
import fitness.travel.onxwjvbr.ui.exercise_list.rv.ExerciseExpandedViewHolder
import fitness.travel.onxwjvbr.ui.firstCharToUpperCase
import fitness.travel.onxwjvbr.ui.training_item.TrainingItemFragment
import fitness.travel.onxwjvbr.ui.training_item.full.rv.ItemRvAdapter
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONException
import java.io.IOException
import java.util.Calendar

private const val TRAINING_ID = "training_id"
private const val EXERCISE_ID = "exercise_id"
private const val EXERCISE_NAME = "exercise_name"

class ItemFullFragment : Fragment() {


    private val binding by lazy { FragmentTrainingItemFullBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this)[TrainingItemFullViewModel::class.java] }
    private val btnAdd by lazy { binding.btnAddRepeat }
    private val rvAdapter = ItemRvAdapter()
    private val rv by lazy { binding.rvTrainingItemList }
    private var trainingId: Int? = null
    private var exerciseId: Int? = null
    private var exerciseName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            trainingId = it.getInt(TRAINING_ID)
            exerciseId = it.getInt(EXERCISE_ID)
            exerciseName = it.getString(EXERCISE_NAME).toString()
            viewModel.setupTrainingInfo(trainingId ?: 0, exerciseId ?: 0, exerciseName)
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
        binding.tvExerciseName.text = exerciseName.firstCharToUpperCase()
        setupRV()
        observeVM()
        setupBtnStartClickListener()
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

    private fun setupBtnStartClickListener() {
        binding.btnAddRepeat.setOnClickListener {
            if (btnAdd.text == getString(R.string.start)){
                btnAdd.text = getString(R.string.finish)
                viewModel.startTime = Calendar.getInstance().timeInMillis
            }else{
                btnAdd.text = getString(R.string.start)
                viewModel.endTime = Calendar.getInstance().timeInMillis
                viewModel.addNewExercise(
                    binding.etWeight.text.toString().toInt(),
                    binding.etRepeat.text.toString().toInt()
                )
            }
        }
    }

    private fun observeVM() {
        viewModel.exerciseList.observe(viewLifecycleOwner) {
            rvAdapter.submitList(it)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(trainingId: Int, exerciseId: Int, exerciseName: String) =
            ItemFullFragment().apply {
                arguments = Bundle().apply {
                    putInt(TRAINING_ID, trainingId)
                    putInt(EXERCISE_ID, exerciseId)
                    putString(EXERCISE_NAME, exerciseName)
                }
            }
    }

}