package info.guide.xsngthp.fragments

import info.guide.xsngthp.R
import info.guide.xsngthp.base.BaseFragment
import info.guide.xsngthp.binding.viewBinding
import info.guide.xsngthp.databinding.FragmentDetailsBinding
import winwin.alwn.xsbnhnh.data.DataModel

class DetailsFragment(
    private val data: DataModel
): BaseFragment<FragmentDetailsBinding>(R.layout.fragment_details) {

    override val binding: FragmentDetailsBinding by viewBinding(FragmentDetailsBinding::bind)

    override fun setupViews() {
        with(binding){
            banner.setBackgroundResource(data.image)
            textTitle.text = data.title
            textContent.text = data.desc
        }
    }

    override fun viewModelObservers() {  }
}