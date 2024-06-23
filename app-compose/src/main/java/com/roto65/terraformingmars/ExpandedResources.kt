package com.roto65.terraformingmars

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.setValue

data class ExpandedResources(val default: Boolean = false) {
    private var coinExpanded by mutableStateOf(default)
    private var steelExpanded by mutableStateOf(default)
    private var titaniumExpanded by mutableStateOf(default)
    private var plantsExpanded by mutableStateOf(default)
    private var energyExpanded by mutableStateOf(default)
    private var heatExpanded by mutableStateOf(default)

    fun isExpanded(type: ResourceType): Boolean {
        return when (type) {
            ResourceType.COIN -> coinExpanded
            ResourceType.STEEL -> steelExpanded
            ResourceType.TITANIUM -> titaniumExpanded
            ResourceType.PLANT -> plantsExpanded
            ResourceType.ENERGY -> energyExpanded
            ResourceType.HEAT -> heatExpanded
        }
    }

    fun reverse(type: ResourceType) {
        when (type) {
            ResourceType.COIN -> coinExpanded = !coinExpanded
            ResourceType.STEEL -> steelExpanded = !steelExpanded
            ResourceType.TITANIUM -> titaniumExpanded = !titaniumExpanded
            ResourceType.PLANT -> plantsExpanded = !plantsExpanded
            ResourceType.ENERGY -> energyExpanded = !energyExpanded
            ResourceType.HEAT -> heatExpanded = !heatExpanded
        }
    }

    companion object {
        val Saver: Saver<ExpandedResources, *> = listSaver(
            save = {
                listOf(
                    it.coinExpanded,
                    it.steelExpanded,
                    it.titaniumExpanded,
                    it.plantsExpanded,
                    it.energyExpanded,
                    it.heatExpanded
                )
            },
            restore = {
                ExpandedResources(
                    it[0],
                    it[1],
                    it[2],
                    it[3],
                    it[4],
                    it[5]
                )
            }
        )
    }

    constructor(
        coinExpanded: Boolean,
        steelExpanded: Boolean,
        titaniumExpanded: Boolean,
        plantsExpanded: Boolean,
        energyExpanded: Boolean,
        heatExpanded: Boolean
    ) : this() {
        this.coinExpanded = coinExpanded
        this.steelExpanded = steelExpanded
        this.titaniumExpanded = titaniumExpanded
        this.plantsExpanded = plantsExpanded
        this.energyExpanded = energyExpanded
        this.heatExpanded = heatExpanded
    }
}
