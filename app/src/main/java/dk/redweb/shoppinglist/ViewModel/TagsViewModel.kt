package dk.redweb.shoppinglist.ViewModel

import dk.redweb.shoppinglist.Database.AppDatabase
import dk.redweb.shoppinglist.Database.Model.DbItem
import dk.redweb.shoppinglist.Database.Model.DbTag

class TagsViewModel(private val _db: AppDatabase) : BaseViewModel() {
    private val _tags: MutableList<Tag> = mutableListOf()
    private val _tagsSubscription: HashMap<Any, (MutableList<Tag>) -> Unit> = hashMapOf()

    init {
        _db.Tags.getTags {
            setupViewModel(it)
        }
    }

    fun setupViewModel(items: List<DbTag>) {
        for (dbitem in items) {
            val item = Tag(dbitem)
            handleAddItem(item)
        }
    }

    fun createTag(name: String, type: Int) {
        _db.Tags.createTag(name, type) {
            _db.Tags.getTag(it) {
                val tag = Tag(it)
                handleAddItem(tag)
            }
        }
    }

    fun updateTag(tag: Tag) {
        _db.Tags.updateTag(tag)
    }

    fun deleteTag(tag: Tag) {
        _db.Tags.removeTag(tag.getId())
        handleRemoveTag(tag)
    }

    private fun handleAddItem(tag: Tag) {
        //Handle Full List
        _tags.add(tag)
        _tags.sortBy { sort -> sort.getName() }
        doCallback(_tagsSubscription.values, _tags)
    }

    private fun handleRemoveTag(tag: Tag) {
        _tags.remove(tag)
        doCallback(_tagsSubscription.values, _tags)
    }

    fun get(index: Int) : Tag {
        return _tags.get(index)
    }

    fun getCount() : Int {
        return _tags.size
    }

    fun observeTags(key: Any, callback: (item: MutableList<Tag>) -> Unit) {
        _tagsSubscription.put(key, callback)
    }
}