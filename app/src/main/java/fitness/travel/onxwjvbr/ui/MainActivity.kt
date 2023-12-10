package fitness.travel.onxwjvbr.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.FragmentManager
import fitness.travel.onxwjvbr.R
import fitness.travel.onxwjvbr.databinding.ActivityMainBinding
import fitness.travel.onxwjvbr.domain.FragmentName

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observeFragmentName()
        setupBtnBackClickLIstener()
    }

    private fun observeFragmentName(){
        FragmentName.fragmentNameLD.observe(this){
            binding.tvFragmentName.text = it
            val btnBackVisibility = if (it == getString(R.string.my_exercise_list)){
                View.GONE
            }else View.VISIBLE
            binding.btnBack.visibility = btnBackVisibility
        }
    }

    private fun setupBtnBackClickLIstener(){
        binding.btnBack.setOnClickListener {
            supportFragmentManager.popBackStack()
        }
    }


}