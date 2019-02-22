package ru.vitalydemidov.og_testapp.base

import ru.vitalydemidov.og_testapp.base.model.BaseItem

class BaseItemMapper {

    fun <MODEL> toBaseItem(type: Int, model: MODEL): BaseItem<MODEL> {
        return BaseItem(type, data = model)
    }

}