package com.roto65.app_legacy

enum class ResourceType {
    COIN,
    STEEL,
    TITANIUM,
    PLANT,
    ENERGY,
    HEAT
}


data class ExpandedResources(val default: Boolean = false) {
    private var coinExpanded: Boolean = default
    private var steelExpanded: Boolean = default
    private var titaniumExpanded: Boolean = default
    private var plantsExpanded: Boolean = default
    private var energyExpanded: Boolean = default
    private var heatExpanded: Boolean = default

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

    fun set(type: ResourceType, value:Boolean) {
        when (type) {
            ResourceType.COIN -> coinExpanded = value
            ResourceType.STEEL -> steelExpanded = value
            ResourceType.TITANIUM -> titaniumExpanded = value
            ResourceType.PLANT -> plantsExpanded = value
            ResourceType.ENERGY -> energyExpanded = value
            ResourceType.HEAT -> heatExpanded = value
        }
    }

    override fun toString(): String {
        var s = ""

        s += if (coinExpanded) {
            "1,"
        } else {
            "0,"
        }

        s += if (steelExpanded) {
            "1,"
        } else {
            "0,"
        }

        s += if (titaniumExpanded) {
            "1,"
        } else {
            "0,"
        }

        s += if (plantsExpanded) {
            "1,"
        } else {
            "0,"
        }

        s += if (energyExpanded) {
            "1,"
        } else {
            "0,"
        }

        s += if (heatExpanded) {
            "1"
        } else {
            "0"
        }
        return s
    }

    fun fromString(string: String?) {
        if (string == null)
            return

        val values = string.split(",")

        this.coinExpanded = values[0].toInt() == 1
        this.steelExpanded = values[1].toInt() == 1
        this.titaniumExpanded = values[2].toInt() == 1
        this.plantsExpanded = values[3].toInt() == 1
        this.energyExpanded = values[4].toInt() == 1
        this.heatExpanded = values[5].toInt() == 1
    }
}
