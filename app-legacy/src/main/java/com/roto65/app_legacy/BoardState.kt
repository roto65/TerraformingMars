package com.roto65.app_legacy

data class BoardState(var default: Int = 0) {
    var coinCount: Int = default
    var coinIncrement: Int = default

    var steelCount: Int = default
    var steelIncrement: Int = default

    var titaniumCount: Int = default
    var titaniumIncrement: Int = default

    var plantCount: Int = default
    var plantIncrement: Int = default

    var energyCount: Int = default
    var energyIncrement: Int = default

    var heatCount: Int = default
    var heatIncrement: Int = default

    var generation: Int = 1

    private var prev: String? = null

    fun setCountCoin(value: Int) {
        prev = toString()
        coinCount = value
    }

    fun setIncrementCoin(value: Int) {
        prev = toString()
        coinIncrement = value
    }

    fun setCountSteel(value: Int) {
        prev = toString()
        steelCount = value
    }

    fun setIncrementSteel(value: Int) {
        prev = toString()
        steelIncrement = value
    }

    fun setCountTitanium(value: Int) {
        prev = toString()
        titaniumCount = value
    }

    fun setIncrementTitanium(value: Int) {
        prev = toString()
        titaniumIncrement = value
    }

    fun setCountPlant(value: Int) {
        prev = toString()
        plantCount = value
    }

    fun setIncrementPlant(value: Int) {
        prev = toString()
        plantIncrement = value
    }

    fun setCountEnergy(value: Int) {
        prev = toString()
        energyCount = value
    }

    fun setIncrementEnergy(value: Int) {
        prev = toString()
        energyIncrement = value
    }

    fun setCountHeat(value: Int) {
        prev = toString()
        heatCount = value
    }

    fun setIncrementHeat(value: Int) {
        prev = toString()
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

    private fun addPlant() {
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

    fun buildForest(): Boolean {
        if (plantCount >= 8) {
            prev = toString()
            plantCount -= 8

            return true
        }
        return false
    }

    fun raiseTemp(): Boolean {
        if (heatCount >= 8) {
            prev = toString()
            heatCount -= 8

            return true
        }
        return false
    }

    fun productionPhase() {
        prev = toString()
        generation++
        convertEnergy()

        addCoin()
        addSteel()
        addTitanium()
        addPlant()
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
    }

    fun undo() {
        if (prev != null) {
            this.fromString(prev)
            prev = null
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