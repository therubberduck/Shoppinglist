package dk.redweb.shoppinglist.FrontEnd.MainView

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.EditText
import android.widget.ImageButton
import dk.redweb.shoppinglist.FrontEnd.MainView.OnListFragment.OnListFragment
import dk.redweb.shoppinglist.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var _sectionPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()
        setupPager()
    }

    private fun setupToolbar() {
        val toolbar = Toolbar(this)

        val textField = EditText(this)
        textField.setHint("Test")

        val button = ImageButton(this)
        button.setImageResource(R.drawable.ic_add)

        toolbar.addView(textField)
        toolbar.addView(button)

        setSupportActionBar(toolbar)
    }

    private fun setupPager() {
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        _sectionPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        container.adapter = _sectionPagerAdapter
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            if(position == 0) {
                return OnListFragment()
            }
            else {
                return OnListFragment()
            }
        }

        override fun getCount(): Int {
            return 2
        }
    }
}
