package info.guide.xsngthp.fragments

import info.guide.xsngthp.R
import info.guide.xsngthp.base.BaseFragment
import info.guide.xsngthp.binding.viewBinding
import info.guide.xsngthp.data.Data
import info.guide.xsngthp.databinding.FragmentMainBinding

class MainFragment(
    private val listener: Listener
): BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    override val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)

    override fun setupViews() {
        with(binding){
            homeButton.setOnClickListener { listener.onPositionChange(1) }
            aboutButton.setOnClickListener { listener.onPositionChange(2) }
            tipsButton.setOnClickListener { listener.onPositionChange(3) }
            stratButton.setOnClickListener { listener.onPositionChange(4) }

            textContent.text = Data.homeData
        }
    }

    override fun viewModelObservers() {  }

    interface Listener {
        fun onPositionChange(position: Int)
    }
}