package info.guide.xsngthp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import info.guide.xsngthp.binding.viewBinding
import info.guide.xsngthp.data.Data
import info.guide.xsngthp.databinding.ActivityMainBinding
import info.guide.xsngthp.fragments.DetailsFragment
import info.guide.xsngthp.fragments.MainFragment

class MainActivity : AppCompatActivity(), MainFragment.Listener {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    private lateinit var fragment: List<Fragment>

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {
        fragment = listOf(
            MainFragment(this),
            DetailsFragment(Data.data[0]),
            DetailsFragment(Data.data[1]),
            DetailsFragment(Data.data[2]),
            DetailsFragment(Data.data[3])
        )

        viewPagerAdapter = ViewPagerAdapter(this, fragment)

        with(binding){

            with(viewPager){
                adapter = viewPagerAdapter
                isUserInputEnabled = false
                offscreenPageLimit = 4

                registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        when(position){
                            0 -> bottomNavigationView.selectedItemId = R.id.main
                            1 -> bottomNavigationView.selectedItemId = R.id.home
                            2 -> bottomNavigationView.selectedItemId = R.id.about
                            3 -> bottomNavigationView.selectedItemId = R.id.tips
                            4 -> bottomNavigationView.selectedItemId = R.id.history
                        }
                    }
                })
            }

            bottomNavigationView.setOnItemSelectedListener {
                when(it.itemId){
                    R.id.main -> setCurrentItem(0)
                    R.id.home -> setCurrentItem(1)
                    R.id.about -> setCurrentItem(2)
                    R.id.tips -> setCurrentItem(3)
                    R.id.history -> setCurrentItem(4)
                }
                true
            }
        }

    }

    private fun setCurrentItem(i: Int) {
        binding.viewPager.currentItem = i
    }

    companion object {
        fun getStartIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override fun onBackPressed() {
        if(binding.viewPager.currentItem > 0){
            binding.viewPager.currentItem = 0
        }else AlertDialog.Builder(this)
            .setTitle("Exit Application?")
            .setMessage("Do you want to exit?")
            .setPositiveButton("Ok"){ _,_ -> super.onBackPressed() }
            .setNegativeButton("Cancel"){ d, _ -> d.dismiss()}
            .show()
    }

    override fun onPositionChange(position: Int) {
        binding.viewPager.currentItem = position
    }
}