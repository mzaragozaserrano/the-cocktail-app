package com.thecocktailapp.datasources

import com.mzs.core.data.datasources.local.ResourcesDataSource
import javax.inject.Inject

class FakeResourcesDataSourceImpl @Inject constructor() : ResourcesDataSource {

    override fun getStringFromResource(resId: Int): String =
        "Pour Hot Damn 100 in bottom of a jar or regular glass.Fill the rest of the glass with sweet tea.Stir with spoon, straw, or better yet a cinnamon stick and leave it in ."

    override fun getStringOrResource(resId: Int, str: String?, ): String = str ?: "Coffee / Tea"

}