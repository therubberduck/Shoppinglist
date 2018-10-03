package dk.redweb.shoppinglist.FrontEnd

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import dk.redweb.shoppinglist.R
import org.jetbrains.anko.find

class NavigationBar(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
    : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var _txtTitle: TextView
    private lateinit var _btnBack: ImageButton

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    init {
        inflate(context, R.layout.view_navigationbar, this)

        _txtTitle = find<TextView>(R.id.txtNavTitle)
        _btnBack = find<ImageButton>(R.id.btnNavBack)

        val activity = context as MainActivity

        _btnBack.setOnClickListener {
            activity.navigator().goBack()
        }
    }

    fun setTitle(title: String) {
        _txtTitle.setText(title)
    }
}