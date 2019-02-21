package ru.vitalydemidov.og_testapp.appcommon

import ru.vitalydemidov.og_testapp.appcommon.model.BaseItem

class BaseItemMapper {

    fun <MODEL> toBaseItem(type: Int, model: MODEL): BaseItem<MODEL> {
        return BaseItem(type, data = model)
    }

}