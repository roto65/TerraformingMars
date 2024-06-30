package com.roto65.app_legacy;

import kotlin.random.Random

data class BoardState(var default: Int = 0) {
    var coinCount: Int = default
    var coinIncrement: Int = Random.nextInt(-2, 10)

    var steelCount: Int = default
    var steelIncrement: Int = Random.nextInt(-2, 10)

    var titaniumCount: Int = default
    var titaniumIncrement: Int = Random.nextInt(-2, 10)

    var plantCount: Int = default
    var plantIncrement: Int = Random.nextInt(-2, 10)

    var energyCount: Int = default
    var energyIncrement: Int = Random.nextInt(-2, 10)

    var heatCount: Int = default
    var heatIncrement: Int = Random.nextInt(-2, 10)

    var generation: Int = 1

    private var prev: String? = null
    var canUndo: Boolean = false

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

    fun setCountPlant(value: Int) {
        prev = toString()
        canUndo = true
        plantCount = value
    }

    fun setIncrementPlant(value: Int) {
        prev = toString()
        canUndo = true
        plantIncrement = value
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

    private fun addplant() {
        plantCount += plantIncrement
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

    fun buildForest() {
        if (plantCount >= 8) {
            prev = toString()
            canUndo = true
            plantCount -= 8
        }
    }

    fun raiseTemp() {
        if (heatCount >= 8) {
            prev = toString()
            canUndo = true
            heatCount -= 8
        }
    }

    fun productionPhase() {
        prev = toString()
        canUndo = true
        generation++
        convertEnergy()

        addCoin()
        addSteel()
        addTitanium()
        addplant()
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
        plantCount = default
        plantIncrement = default
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
        return "$coinCount,$coinIncrement,$steelCount,$steelIncrement,$titaniumCount,$titaniumIncrement,$plantCount,$plantIncrement,$energyCount,$energyIncrement,$heatCount,$heatIncrement,$generation"
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
        this.plantCount = values[6].toInt()
        this.plantIncrement = values[7].toInt()
        this.energyCount = values[8].toInt()
        this.energyIncrement = values[9].toInt()
        this.heatCount = values[10].toInt()
        this.heatIncrement = values[11].toInt()
        this.generation = values[12].toInt()
    }
}