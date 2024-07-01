package com.roto65.terraformingmars

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.setValue

data class BoardState(var default: Int = 0) {
    var coinCount by mutableIntStateOf(default)
    var coinIncrement by mutableIntStateOf(default)

    var steelCount by mutableIntStateOf(default)
    var steelIncrement by mutableIntStateOf(default)

    var titaniumCount by mutableIntStateOf(default)
    var titaniumIncrement by mutableIntStateOf(default)

    var plantsCount by mutableIntStateOf(default)
    var plantsIncrement by mutableIntStateOf(default)

    var energyCount by mutableIntStateOf(default)
    var energyIncrement by mutableIntStateOf(default)

    var heatCount by mutableIntStateOf(default)
    var heatIncrement by mutableIntStateOf(default)

    var generation by mutableIntStateOf(1)

    private var prev: String? = null
    var canUndo by mutableStateOf(false)

    fun setCountCoin(value: Int) {
        prev = toString()
        canUndo = true
        coinCount = value
    }

    fun setIncrementCoin(value: Int) {
        prev = toString()
        canUndo = true
        coinIncrement = value
    }

    fun setCountSteel(value: Int) {
        prev = toString()
        canUndo = true
        steelCount = value
    }

    fun setIncrementSteel(value: Int) {
        prev = toString()
        canUndo = true
        steelIncrement = value
    }

    fun setCountTitanium(value: Int) {
        prev = toString()
        canUndo = true
        titaniumCount = value
    }

    fun setIncrementTitanium(value: Int) {
        prev = toString()
        canUndo = true
        titaniumIncrement = value
    }

    fun setCountPlants(value: Int) {
        prev = toString()
        canUndo = true
        plantsCount = value
    }

    fun setIncrementPlants(value: Int) {
        prev = toString()
        canUndo = true
        plantsIncrement = value
    }

    fun setCountEnergy(value: Int) {
        prev = toString()
        canUndo = true
        energyCount = value
    }

    fun setIncrementEnergy(value: Int) {
        prev = toString()
        canUndo = true
        energyIncrement = value
    }

    fun setCountHeat(value: Int) {
        prev = toString()
        canUndo = true
        heatCount = value
    }

    fun setIncrementHeat(value: Int) {
        prev = toString()
        canUndo = true
        heatIncrement = value
    }

    private fun addCoin() {
        coinCount += coinIncrement
    }

    private fun addSteel() {
        steelCount += steelIncrement
    }

    private fun addTitanium() {
        titaniumCount += titaniumIncrement
    }

    private fun addPlants() {
        plantsCount += plantsIncrement
    }

    private fun addEnergy() {
        energyCount += energyIncrement
    }

    private fun addHeat() {
        heatCount += heatIncrement
    }

    private fun convertEnergy() {
        heatCount += energyCount
        energyCount = 0
    }

    fun buildForest(): Boolean {
        if (plantsCount >= 8) {
            prev = toString()
            canUndo = true
            plantsCount -= 8

            return true
        }
        return false
    }

    fun raiseTemp(): Boolean {
        if (heatCount >= 8) {
            prev = toString()
            canUndo = true
            heatCount -= 8

            return true
        }
        return false
    }

    fun productionPhase() {
        prev = toString()
        canUndo = true
        generation++
        convertEnergy()

        addCoin()
        addSteel()
        addTitanium()
        addPlants()
        addEnergy()
        addHeat()
    }

    fun reset() {
        coinCount = default
        coinIncrement = default
        steelCount = default
        steelIncrement = default
        titaniumCount = default
        titaniumIncrement = default
        plantsCount = default
        plantsIncrement = default
        energyCount = default
        energyIncrement = default
        heatCount = default
        heatIncrement = default
        generation = 1

        prev = null
        canUndo = false
    }

    fun undo() {
        if (prev != null) {
            this.fromString(prev)
            prev = null
            canUndo = false
        }
    }

    override fun toString(): String {
        return "$coinCount,$coinIncrement,$steelCount,$steelIncrement,$titaniumCount,$titaniumIncrement,$plantsCount,$plantsIncrement,$energyCount,$energyIncrement,$heatCount,$heatIncrement,$generation"
    }

    fun fromString(string: String?) {

        if (string == null)
            return

        val values = string.split(",")

        this.coinCount = values[0].toInt()
        this.coinIncrement = values[1].toInt()
        this.steelCount = values[2].toInt()
        this.steelIncrement = values[3].toInt()
        this.titaniumCount = values[4].toInt()
        this.titaniumIncrement = values[5].toInt()
        this.plantsCount = values[6].toInt()
        this.plantsIncrement = values[7].toInt()
        this.energyCount = values[8].toInt()
        this.energyIncrement = values[9].toInt()
        this.heatCount = values[10].toInt()
        this.heatIncrement = values[11].toInt()
        this.generation = values[12].toInt()
    }

    companion object {
        val Saver: Saver<BoardState, *> = listSaver(
            save = {
                listOf(
                    it.coinCount,
                    it.coinIncrement,
                    it.steelCount,
                    it.steelIncrement,
                    it.titaniumCount,
                    it.titaniumIncrement,
                    it.plantsCount,
                    it.plantsIncrement,
                    it.energyCount,
                    it.energyIncrement,
                    it.heatCount,
                    it.heatIncrement,
                    it.generation
                )
            },
            restore = {
                BoardState(
                    it[0],
                    it[1],
                    it[2],
                    it[3],
                    it[4],
                    it[5],
                    it[6],
                    it[7],
                    it[8],
                    it[9],
                    it[10],
                    it[11],
                    it[12]
                )
            }
        )
    }

    constructor(
        coinCount: Int,
        coinIncrement: Int,
        steelCount: Int,
        steelIncrement: Int,
        titaniumCount: Int,
        titaniumIncrement: Int,
        plantsCount: Int,
        plantsIncrement: Int,
        energyCount: Int,
        energyIncrement: Int,
        heatCount: Int,
        heatIncrement: Int,
        generation: Int
    ) : this() {
        this.coinCount = coinCount
        this.coinIncrement = coinIncrement
        this.steelCount = steelCount
        this.steelIncrement = steelIncrement
        this.titaniumCount = titaniumCount
        this.titaniumIncrement = titaniumIncrement
        this.plantsCount = plantsCount
        this.plantsIncrement = plantsIncrement
        this.energyCount = energyCount
        this.energyIncrement = energyIncrement
        this.heatCount = heatCount
        this.heatIncrement = heatIncrement
        this.generation = generation
    }
}