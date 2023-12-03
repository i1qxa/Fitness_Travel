package fitness.travel.onxwjvbr.domain

import androidx.lifecycle.MutableLiveData

object FragmentName {

    val fragmentNameLD = MutableLiveData<String>()

    fun changeFragmentName(fragmentName:String){
        fragmentNameLD.value = fragmentName
    }

}