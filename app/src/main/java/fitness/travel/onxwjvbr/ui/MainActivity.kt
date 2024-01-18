package fitness.travel.onxwjvbr.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import fitness.travel.onxwjvbr.R
import fitness.travel.onxwjvbr.databinding.ActivityMainBinding
import fitness.travel.onxwjvbr.domain.FragmentName
import fitness.travel.onxwjvbr.ui.home_screen.HomeFragment

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observeFragmentName()
        setupBtnBackClickLIstener()
        setupBtnHomeClickListener()
    }

    private fun observeFragmentName(){
        FragmentName.fragmentNameLD.observe(this){
            binding.tvFragmentName.text = it
            val btnBackVisibility = if (it == getString(R.string.home)){
                View.INVISIBLE
            }else View.VISIBLE
            binding.btnBack.visibility = btnBackVisibility
        }
    }

    private fun setupBtnBackClickLIstener(){
        binding.btnBack.setOnClickListener {
            supportFragmentManager.popBackStack()
        }
    }

    private fun setupBtnHomeClickListener(){
        binding.btnHome.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.mainConteiner, HomeFragment())
                addToBackStack(null)
                commit()
            }
        }

    }


}

fun String.firstCharToUpperCase():String{
    val firstChar = this[0].uppercaseChar()
    return firstChar.toString() + this.drop(0)
}
