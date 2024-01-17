package fitness.travel.onxwjvbr.ui.training_chart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import fitness.travel.onxwjvbr.databinding.FragmentTrainingChartBinding
import fitness.travel.onxwjvbr.ui.firstCharToUpperCase

class TrainingChartFragment : Fragment() {

    private val binding by lazy { FragmentTrainingChartBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this)[TrainingChartViewModel::class.java] }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel(){
        val chartModel = AAChartModel()
        viewModel.itemList.observe(viewLifecycleOwner){ exerciseAndCountList ->
            val chartData = exerciseAndCountList.map {
                AASeriesElement()
                    .name(it.exerciseName.firstCharToUpperCase())
                    .data(arrayOf(it.amount))
            }
            chartModel.apply {
                chartType(AAChartType.Column)
                title("Amount of Exercises")
                backgroundColor("#FFFFFF")
                dataLabelsEnabled(true)
                series(
                    chartData.toTypedArray()
                )
            }
            binding.chartTrainingList.aa_drawChartWithChartModel(chartModel)
        }

    }

}