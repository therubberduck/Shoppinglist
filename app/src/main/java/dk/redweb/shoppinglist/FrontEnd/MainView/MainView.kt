package dk.redweb.shoppinglist.FrontEnd.MainView

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.wealthfront.magellan.BaseScreenView
import com.wealthfront.magellan.Screen
import dk.redweb.shoppinglist.FrontEnd.TagList.TagListScreen
import dk.redweb.shoppinglist.R
import org.jetbrains.anko.find

class MainView(context: Context?, screens:List<Screen<*>>) : BaseScreenView<MainScreen>(context) {

    private val _pager: ViewPager
    val _btnMenu: ImageButton

    init {
        inflate(context, R.layout.activity_main, this)

        _pager = find(R.id.container)
        _btnMenu = find(R.id.btnMenu)

        initPager(context, screens)
    }

    fun initPager(context: Context?, screens: List<Screen<*>>){
        val swipeAdapter = SwipeAdapter(context, screens)
        _pager.adapter = swipeAdapter
        _pager.offscreenPageLimit = 2
    }

    fun addPagerView(view: View){
        _pager.addView(view)
    }

    class SwipeAdapter(val context: Context?, val screens: List<Screen<*>>) : PagerAdapter() {
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            return screens.get(position).getView()
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        }

        override fun getCount(): Int {
            return screens.count()
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun getPageTitle(position: Int): CharSequence {
            return screens.get(position).getTitle(context)
        }
    }
}