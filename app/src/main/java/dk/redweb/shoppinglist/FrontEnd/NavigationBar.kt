package dk.redweb.shoppinglist.FrontEnd

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.widget.ImageButton
import android.widget.TextView
import dk.redweb.shoppinglist.FrontEnd.TagList.TagListScreen
import dk.redweb.shoppinglist.R
import org.jetbrains.anko.find

class NavigationBar(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
    : ConstraintLayout(context, attrs, defStyleAttr) {

    private val _txtTitle: TextView
    private val _btnBack: ImageButton
    private val _btnMenu: ImageButton

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    init {
        inflate(context, R.layout.view_navigationbar, this)

        _txtTitle = find(R.id.txtNavTitle)
        _btnBack = find(R.id.btnNavBack)
        _btnMenu = find(R.id.btnMenu)

        val activity = context as MainActivity

        _btnBack.setOnClickListener {
            activity.navigator().goBack()
        }
        _btnMenu.setOnClickListener {
            activity.navigator().goTo(TagListScreen())
        }
    }

    fun setTitle(title: Int) {
        setTitle(context.getString(title))
    }

    fun setTitle(title: String) {
        _txtTitle.text = title
    }
}