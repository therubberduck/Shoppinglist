package dk.redweb.shoppinglist.ViewModel

import dk.redweb.shoppinglist.Database.AppDatabase

class AddItemViewModel(private val _db: AppDatabase, private val _itemId: Long?) : BaseViewModel() {
    private val _tags: MutableList<Tag> = mutableListOf()
    private val _selectedTags: MutableList<Tag> = mutableListOf()
    private val _unselectedTags: MutableList<Tag> = mutableListOf()

    private val _selectedTagsSubscription: HashMap<Any, (MutableList<Tag>) -> Unit> = hashMapOf()

    fun setup() {
        if(_itemId != null) {
            _db.Tags.getTags(_itemId) {
                for (dbtag in it) {
                    _selectedTags.add(Tag(dbtag))
                }

                _selectedTags.sortBy { sort -> sort.getName() }
            }
        }

        _db.Tags.getTags {
            it.forEach { dbtag ->
                var nonexist = true
                _selectedTags.forEach {
                    if(dbtag.id == it.getId()) {
                        nonexist = false
                    }
                }
                if(nonexist) {
                    _unselectedTags.add(Tag(dbtag))
                }
            }

            _unselectedTags.sortBy { sort -> sort.getName() }
        }

        doCallback(_selectedTagsSubscription.values, _selectedTags)
    }

    fun selectTag(tagId: Long) {
        val tag = _unselectedTags.first { it.getId() == tagId }
        _unselectedTags.remove(tag)
        _selectedTags.add(tag)

        _selectedTags.sortBy { sort -> sort.getName() }

        doCallback(_selectedTagsSubscription.values, _selectedTags)
    }

    fun deselectTag(tagId: Long) {
        val tag = _selectedTags.first { it.getId() == tagId }
        _selectedTags.remove(tag)
        _unselectedTags.add(tag)

        _unselectedTags.sortBy { sort -> sort.getName() }

        doCallback(_selectedTagsSubscription.values, _selectedTags)
    }

    fun getUsed() : List<Tag> {
        return _selectedTags.toList()
    }

    fun getUsed(position: Int) : Tag {
        return _selectedTags.get(position)
    }

    fun getUnused(): List<Tag> {
        return _unselectedTags.toList()
    }

    fun getUsedTagCount(): Int {
        return _selectedTags.size
    }

    fun observeTagsOnItem(key: Any, callback: (item: MutableList<Tag>) -> Unit) {
        _selectedTagsSubscription.put(key, callback)
    }
}