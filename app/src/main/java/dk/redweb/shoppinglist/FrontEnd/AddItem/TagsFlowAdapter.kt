package dk.redweb.shoppinglist.FrontEnd.AddItem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.chrischeng.flowlayout.FlowAdapter
import dk.redweb.shoppinglist.R
import dk.redweb.shoppinglist.ViewModel.AddItemViewModel
import dk.redweb.shoppinglist.ViewModel.Tag
import org.jetbrains.anko.find

class TagsFlowAdapter(private val _tagsVm: AddItemViewModel, private val _screen: AddItemScreen) : FlowAdapter<Tag>() {

    override fun getView(position: Int, parent: ViewGroup): View {
        if(position == count - 1) {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.cell_flowtagbutton, parent, false)
            val ibnAddTag = view.find(R.id.ibnAddTag) as ImageButton
            ibnAddTag.setOnClickListener { _screen.openAddTagMenu(view) }
            return view
        }

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.cell_flowtag, parent, false)

        val tag = _tagsVm.getUsed(position);

        val txtName = view.find(R.id.txtName) as TextView
        txtName.setText(tag.getName())

        view.setOnClickListener { _tagsVm.deselectTag(tag.getId()) }

        return view
    }

    override fun getCount(): Int {
        return _tagsVm.getUsedTagCount() + 1
    }
}