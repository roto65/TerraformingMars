package com.roto65.terraformingmars

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

enum class ResourceType {
    COIN {
        @Composable
        override fun getName(): String = stringResource(R.string.resource_coin)

        @Composable
        override fun getIcon(): Int = R.drawable.coinicon
    },
    STEEL {
        @Composable
        override fun getName(): String = stringResource(R.string.resource_steel)

        @Composable
        override fun getIcon(): Int = R.drawable.steelicon
    },
    TITANIUM {
        @Composable
        override fun getName(): String = stringResource(R.string.resource_titanium)

        @Composable
        override fun getIcon(): Int = R.drawable.titaniumicon
    },
    PLANT {
        @Composable
        override fun getName(): String = stringResource(R.string.resource_plant)

        @Composable
        override fun getIcon(): Int = R.drawable.planticon
    },
    ENERGY {
        @Composable
        override fun getName(): String = stringResource(R.string.resource_energy)

        @Composable
        override fun getIcon(): Int = R.drawable.energyicon
    },
    HEAT {
        @Composable
        override fun getName(): String = stringResource(R.string.resource_heat)

        @Composable
        override fun getIcon(): Int = R.drawable.heaticon
    };

    @Composable
    abstract fun getName(): String

    @Composable
    abstract fun getIcon(): Int
}