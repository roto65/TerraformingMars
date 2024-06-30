package com.roto65.app_legacy

import android.annotation.SuppressLint
import android.icu.number.NumberFormatter
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuBuilder
import androidx.cardview.widget.CardView
import com.roto65.app_legacy.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var boardState: BoardState
    private lateinit var expandedResources: ExpandedResources

    // Coin card

    private lateinit var coinArrow: ImageButton
    private lateinit var coinHiddenView: LinearLayout
    private lateinit var coinCardView: CardView

    private lateinit var coinLblAmount: TextView
    private lateinit var coinLblIncrement: TextView

    private lateinit var coinLblSubAmount: TextView
    private lateinit var coinLblSubIncrement: TextView

    private lateinit var coinBtnIncrementAmount: ImageButton
    private lateinit var coinBtnDecrementAmount: ImageButton
    private lateinit var coinBtnIncrementIncrement: ImageButton
    private lateinit var coinBtnDecrementIncrement: ImageButton

    // Steel card

    private lateinit var steelArrow: ImageButton
    private lateinit var steelHiddenView: LinearLayout
    private lateinit var steelCardView: CardView

    private lateinit var steelLblAmount: TextView
    private lateinit var steelLblIncrement: TextView

    private lateinit var steelLblSubAmount: TextView
    private lateinit var steelLblSubIncrement: TextView

    private lateinit var steelBtnIncrementAmount: ImageButton
    private lateinit var steelBtnDecrementAmount: ImageButton
    private lateinit var steelBtnIncrementIncrement: ImageButton
    private lateinit var steelBtnDecrementIncrement: ImageButton

    // Titanium card

    private lateinit var titaniumArrow: ImageButton
    private lateinit var titaniumHiddenView: LinearLayout
    private lateinit var titaniumCardView: CardView

    private lateinit var titaniumLblAmount: TextView
    private lateinit var titaniumLblIncrement: TextView

    private lateinit var titaniumLblSubAmount: TextView
    private lateinit var titaniumLblSubIncrement: TextView

    private lateinit var titaniumBtnIncrementAmount: ImageButton
    private lateinit var titaniumBtnDecrementAmount: ImageButton
    private lateinit var titaniumBtnIncrementIncrement: ImageButton
    private lateinit var titaniumBtnDecrementIncrement: ImageButton

    // Plant card

    private lateinit var plantArrow: ImageButton
    private lateinit var plantHiddenView: LinearLayout
    private lateinit var plantCardView: CardView

    private lateinit var plantLblAmount: TextView
    private lateinit var plantLblIncrement: TextView

    private lateinit var plantLblSubAmount: TextView
    private lateinit var plantLblSubIncrement: TextView

    private lateinit var plantBtnIncrementAmount: ImageButton
    private lateinit var plantBtnDecrementAmount: ImageButton
    private lateinit var plantBtnIncrementIncrement: ImageButton
    private lateinit var plantBtnDecrementIncrement: ImageButton

    // Energy card

    private lateinit var energyArrow: ImageButton
    private lateinit var energyHiddenView: LinearLayout
    private lateinit var energyCardView: CardView

    private lateinit var energyLblAmount: TextView
    private lateinit var energyLblIncrement: TextView

    private lateinit var energyLblSubAmount: TextView
    private lateinit var energyLblSubIncrement: TextView

    private lateinit var energyBtnIncrementAmount: ImageButton
    private lateinit var energyBtnDecrementAmount: ImageButton
    private lateinit var energyBtnIncrementIncrement: ImageButton
    private lateinit var energyBtnDecrementIncrement: ImageButton

    // Heat card

    private lateinit var heatArrow: ImageButton
    private lateinit var heatHiddenView: LinearLayout
    private lateinit var heatCardView: CardView

    private lateinit var heatLblAmount: TextView
    private lateinit var heatLblIncrement: TextView

    private lateinit var heatLblSubAmount: TextView
    private lateinit var heatLblSubIncrement: TextView

    private lateinit var heatBtnIncrementAmount: ImageButton
    private lateinit var heatBtnDecrementAmount: ImageButton
    private lateinit var heatBtnIncrementIncrement: ImageButton
    private lateinit var heatBtnDecrementIncrement: ImageButton

    // Bottom bar

    private lateinit var btnProduction: LinearLayout
    private lateinit var btnForest: LinearLayout
    private lateinit var btnTempRise: LinearLayout
    private lateinit var lblGeneration: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preferences = getPreferences(MODE_PRIVATE)
        val prevBoardState = BoardState()
        prevBoardState.fromString(preferences.getString(KEY_BOARD_STATE, null))

        boardState = prevBoardState

        val prevExpandedResources = ExpandedResources()
        prevExpandedResources.fromString(preferences.getString(KEY_EXPANDED_RES, null))

        expandedResources = prevExpandedResources

        // Coin card

        coinCardView = findViewById(R.id.cardview_coin)
        coinArrow = findViewById(R.id.coin_arrow_button)
        coinHiddenView = findViewById(R.id.coin_hidden_view)

        if (expandedResources.isExpanded(ResourceType.COIN)) {
            coinHiddenView.visibility = View.VISIBLE
            coinArrow.setImageResource(R.drawable.rounded_keyboard_arrow_up_24)
        } else {
            coinHiddenView.visibility = View.GONE
            coinArrow.setImageResource(R.drawable.rounded_keyboard_arrow_down_24)
        }

        coinArrow.setOnClickListener {
            if (coinHiddenView.visibility == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(coinCardView, AutoTransition())
                coinHiddenView.visibility = View.GONE
                coinArrow.setImageResource(R.drawable.rounded_keyboard_arrow_down_24)
                expandedResources.set(ResourceType.COIN, false)
            } else {
                TransitionManager.beginDelayedTransition(coinCardView, AutoTransition())
                coinHiddenView.visibility = View.VISIBLE
                coinArrow.setImageResource(R.drawable.rounded_keyboard_arrow_up_24)
                expandedResources.set(ResourceType.COIN, true)
            }
        }

        coinLblAmount = findViewById(R.id.coin_amount_label)
        coinLblIncrement = findViewById(R.id.coin_increment_label)

        coinLblSubAmount = findViewById(R.id.coin_amount_sub)
        coinLblSubIncrement = findViewById(R.id.coin_increment_sub)

        updateLblAmountCoin()
        updateLblIncrementCoin()

        coinBtnIncrementAmount = findViewById(R.id.coin_increment_amount)
        coinBtnDecrementAmount = findViewById(R.id.coin_decrement_amount)
        coinBtnIncrementIncrement = findViewById(R.id.coin_increment_increment)
        coinBtnDecrementIncrement = findViewById(R.id.coin_decrement_increment)

        coinBtnIncrementAmount.setOnClickListener {
            boardState.setCountCoin(boardState.coinCount + 1)
            updateLblAmountCoin()
        }

        coinBtnDecrementAmount.setOnClickListener {
            boardState.setCountCoin(boardState.coinCount - 1)
            updateLblAmountCoin()
        }

        coinBtnIncrementIncrement.setOnClickListener {
            boardState.setIncrementCoin(boardState.coinIncrement + 1)
            updateLblIncrementCoin()
        }

        coinBtnDecrementIncrement.setOnClickListener {
            boardState.setIncrementCoin(boardState.coinIncrement - 1)
            updateLblIncrementCoin()
        }

        // Steel card

        steelCardView = findViewById(R.id.cardview_steel)
        steelArrow = findViewById(R.id.steel_arrow_button)
        steelHiddenView = findViewById(R.id.steel_hidden_view)

        if (expandedResources.isExpanded(ResourceType.STEEL)) {
            steelHiddenView.visibility = View.VISIBLE
            steelArrow.setImageResource(R.drawable.rounded_keyboard_arrow_up_24)
        } else {
            steelHiddenView.visibility = View.GONE
            steelArrow.setImageResource(R.drawable.rounded_keyboard_arrow_down_24)
        }

        steelArrow.setOnClickListener {
            if (steelHiddenView.visibility == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(steelCardView, AutoTransition())
                steelHiddenView.visibility = View.GONE
                steelArrow.setImageResource(R.drawable.rounded_keyboard_arrow_down_24)
                expandedResources.set(ResourceType.STEEL, false)
            } else {
                TransitionManager.beginDelayedTransition(steelCardView, AutoTransition())
                steelHiddenView.visibility = View.VISIBLE
                steelArrow.setImageResource(R.drawable.rounded_keyboard_arrow_up_24)
                expandedResources.set(ResourceType.STEEL, true)
            }
        }

        steelLblAmount = findViewById(R.id.steel_amount_label)
        steelLblIncrement = findViewById(R.id.steel_increment_label)

        steelLblSubAmount = findViewById(R.id.steel_amount_sub)
        steelLblSubIncrement = findViewById(R.id.steel_increment_sub)

        updateLblAmountSteel()
        updateLblIncrementSteel()

        steelBtnIncrementAmount = findViewById(R.id.steel_increment_amount)
        steelBtnDecrementAmount = findViewById(R.id.steel_decrement_amount)
        steelBtnIncrementIncrement = findViewById(R.id.steel_increment_increment)
        steelBtnDecrementIncrement = findViewById(R.id.steel_decrement_increment)

        steelBtnIncrementAmount.setOnClickListener {
            boardState.setCountSteel(boardState.steelCount + 1)
            updateLblAmountSteel()
        }

        steelBtnDecrementAmount.setOnClickListener {
            boardState.setCountSteel(boardState.steelCount - 1)
            updateLblAmountSteel()
        }

        steelBtnIncrementIncrement.setOnClickListener {
            boardState.setIncrementSteel(boardState.steelIncrement + 1)
            updateLblIncrementSteel()
        }

        steelBtnDecrementIncrement.setOnClickListener {
            boardState.setIncrementSteel(boardState.steelIncrement - 1)
            updateLblIncrementSteel()
        }

        // Titanium card

        titaniumCardView = findViewById(R.id.cardview_titanium)
        titaniumArrow = findViewById(R.id.titanium_arrow_button)
        titaniumHiddenView = findViewById(R.id.titanium_hidden_view)

        if (expandedResources.isExpanded(ResourceType.TITANIUM)) {
            titaniumHiddenView.visibility = View.VISIBLE
            titaniumArrow.setImageResource(R.drawable.rounded_keyboard_arrow_up_24)

        } else {
            titaniumHiddenView.visibility = View.GONE
            titaniumArrow.setImageResource(R.drawable.rounded_keyboard_arrow_down_24)
        }

        titaniumArrow.setOnClickListener {
            if (titaniumHiddenView.visibility == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(titaniumCardView, AutoTransition())
                titaniumHiddenView.visibility = View.GONE
                titaniumArrow.setImageResource(R.drawable.rounded_keyboard_arrow_down_24)
                expandedResources.set(ResourceType.TITANIUM, false)
            } else {
                TransitionManager.beginDelayedTransition(titaniumCardView, AutoTransition())
                titaniumHiddenView.visibility = View.VISIBLE
                titaniumArrow.setImageResource(R.drawable.rounded_keyboard_arrow_up_24)
                expandedResources.set(ResourceType.TITANIUM, true)
            }
        }

        titaniumLblAmount = findViewById(R.id.titanium_amount_label)
        titaniumLblIncrement = findViewById(R.id.titanium_increment_label)

        titaniumLblSubAmount = findViewById(R.id.titanium_amount_sub)
        titaniumLblSubIncrement = findViewById(R.id.titanium_increment_sub)

        updateLblAmountTitanium()
        updateLblIncrementTitanium()

        titaniumBtnIncrementAmount = findViewById(R.id.titanium_increment_amount)
        titaniumBtnDecrementAmount = findViewById(R.id.titanium_decrement_amount)
        titaniumBtnIncrementIncrement = findViewById(R.id.titanium_increment_increment)
        titaniumBtnDecrementIncrement = findViewById(R.id.titanium_decrement_increment)

        titaniumBtnIncrementAmount.setOnClickListener {
            boardState.setCountTitanium(boardState.titaniumCount + 1)
            updateLblAmountTitanium()
        }

        titaniumBtnDecrementAmount.setOnClickListener {
            boardState.setCountTitanium(boardState.titaniumCount - 1)
            updateLblAmountTitanium()
        }

        titaniumBtnIncrementIncrement.setOnClickListener {
            boardState.setIncrementTitanium(boardState.titaniumIncrement + 1)
            updateLblIncrementTitanium()
        }

        titaniumBtnDecrementIncrement.setOnClickListener {
            boardState.setIncrementTitanium(boardState.titaniumIncrement - 1)
            updateLblIncrementTitanium()
        }

        // Plant card

        plantCardView = findViewById(R.id.cardview_plant)
        plantArrow = findViewById(R.id.plant_arrow_button)
        plantHiddenView = findViewById(R.id.plant_hidden_view)

        if (expandedResources.isExpanded(ResourceType.PLANT)) {
            plantHiddenView.visibility = View.VISIBLE
            plantArrow.setImageResource(R.drawable.rounded_keyboard_arrow_up_24)

        } else {
            plantHiddenView.visibility = View.GONE
            plantArrow.setImageResource(R.drawable.rounded_keyboard_arrow_down_24)
        }

        plantArrow.setOnClickListener {
            if (plantHiddenView.visibility == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(plantCardView, AutoTransition())
                plantHiddenView.visibility = View.GONE
                plantArrow.setImageResource(R.drawable.rounded_keyboard_arrow_down_24)
                expandedResources.set(ResourceType.PLANT, false)
            } else {
                TransitionManager.beginDelayedTransition(plantCardView, AutoTransition())
                plantHiddenView.visibility = View.VISIBLE
                plantArrow.setImageResource(R.drawable.rounded_keyboard_arrow_up_24)
                expandedResources.set(ResourceType.PLANT, true)
            }
        }

        plantLblAmount = findViewById(R.id.plant_amount_label)
        plantLblIncrement = findViewById(R.id.plant_increment_label)

        plantLblSubAmount = findViewById(R.id.plant_amount_sub)
        plantLblSubIncrement = findViewById(R.id.plant_increment_sub)

        updateLblAmountPlant()
        updateLblIncrementPlant()

        plantBtnIncrementAmount = findViewById(R.id.plant_increment_amount)
        plantBtnDecrementAmount = findViewById(R.id.plant_decrement_amount)
        plantBtnIncrementIncrement = findViewById(R.id.plant_increment_increment)
        plantBtnDecrementIncrement = findViewById(R.id.plant_decrement_increment)

        plantBtnIncrementAmount.setOnClickListener {
            boardState.setCountPlant(boardState.plantCount + 1)
            updateLblAmountPlant()
        }

        plantBtnDecrementAmount.setOnClickListener {
            boardState.setCountPlant(boardState.plantCount - 1)
            updateLblAmountPlant()
        }

        plantBtnIncrementIncrement.setOnClickListener {
            boardState.setIncrementPlant(boardState.plantIncrement + 1)
            updateLblIncrementPlant()
        }

        plantBtnDecrementIncrement.setOnClickListener {
            boardState.setIncrementPlant(boardState.plantIncrement - 1)
            updateLblIncrementPlant()
        }

        // Energy card

        energyCardView = findViewById(R.id.cardview_energy)
        energyArrow = findViewById(R.id.energy_arrow_button)
        energyHiddenView = findViewById(R.id.energy_hidden_view)

        if (expandedResources.isExpanded(ResourceType.ENERGY)) {
            energyHiddenView.visibility = View.VISIBLE
            energyArrow.setImageResource(R.drawable.rounded_keyboard_arrow_up_24)

        } else {
            energyHiddenView.visibility = View.GONE
            energyArrow.setImageResource(R.drawable.rounded_keyboard_arrow_down_24)
        }

        energyArrow.setOnClickListener {
            if (energyHiddenView.visibility == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(energyCardView, AutoTransition())
                energyHiddenView.visibility = View.GONE
                energyArrow.setImageResource(R.drawable.rounded_keyboard_arrow_down_24)
                expandedResources.set(ResourceType.ENERGY, false)
            } else {
                TransitionManager.beginDelayedTransition(energyCardView, AutoTransition())
                energyHiddenView.visibility = View.VISIBLE
                energyArrow.setImageResource(R.drawable.rounded_keyboard_arrow_up_24)
                expandedResources.set(ResourceType.ENERGY, true)
            }
        }

        energyLblAmount = findViewById(R.id.energy_amount_label)
        energyLblIncrement = findViewById(R.id.energy_increment_label)

        energyLblSubAmount = findViewById(R.id.energy_amount_sub)
        energyLblSubIncrement = findViewById(R.id.energy_increment_sub)

        updateLblAmountEnergy()
        updateLblIncrementEnergy()

        energyBtnIncrementAmount = findViewById(R.id.energy_increment_amount)
        energyBtnDecrementAmount = findViewById(R.id.energy_decrement_amount)
        energyBtnIncrementIncrement = findViewById(R.id.energy_increment_increment)
        energyBtnDecrementIncrement = findViewById(R.id.energy_decrement_increment)

        energyBtnIncrementAmount.setOnClickListener {
            boardState.setCountEnergy(boardState.energyCount + 1)
            updateLblAmountEnergy()
        }

        energyBtnDecrementAmount.setOnClickListener {
            boardState.setCountEnergy(boardState.energyCount - 1)
            updateLblAmountEnergy()
        }

        energyBtnIncrementIncrement.setOnClickListener {
            boardState.setIncrementEnergy(boardState.energyIncrement + 1)
            updateLblIncrementEnergy()
        }

        energyBtnDecrementIncrement.setOnClickListener {
            boardState.setIncrementEnergy(boardState.energyIncrement - 1)
            updateLblIncrementEnergy()
        }

        // Heat card

        heatCardView = findViewById(R.id.cardview_heat)
        heatArrow = findViewById(R.id.heat_arrow_button)
        heatHiddenView = findViewById(R.id.heat_hidden_view)

        if (expandedResources.isExpanded(ResourceType.HEAT)) {
            heatHiddenView.visibility = View.VISIBLE
            heatArrow.setImageResource(R.drawable.rounded_keyboard_arrow_up_24)

        } else {
            heatHiddenView.visibility = View.GONE
            heatArrow.setImageResource(R.drawable.rounded_keyboard_arrow_down_24)
        }

        heatArrow.setOnClickListener {
            if (heatHiddenView.visibility == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(heatCardView, AutoTransition())
                heatHiddenView.visibility = View.GONE
                heatArrow.setImageResource(R.drawable.rounded_keyboard_arrow_down_24)
                expandedResources.set(ResourceType.HEAT, false)
            } else {
                TransitionManager.beginDelayedTransition(heatCardView, AutoTransition())
                heatHiddenView.visibility = View.VISIBLE
                heatArrow.setImageResource(R.drawable.rounded_keyboard_arrow_up_24)
                expandedResources.set(ResourceType.HEAT, true)
            }
        }

        heatLblAmount = findViewById(R.id.heat_amount_label)
        heatLblIncrement = findViewById(R.id.heat_increment_label)

        heatLblSubAmount = findViewById(R.id.heat_amount_sub)
        heatLblSubIncrement = findViewById(R.id.heat_increment_sub)

        updateLblAmountHeat()
        updateLblIncrementHeat()

        heatBtnIncrementAmount = findViewById(R.id.heat_increment_amount)
        heatBtnDecrementAmount = findViewById(R.id.heat_decrement_amount)
        heatBtnIncrementIncrement = findViewById(R.id.heat_increment_increment)
        heatBtnDecrementIncrement = findViewById(R.id.heat_decrement_increment)

        heatBtnIncrementAmount.setOnClickListener {
            boardState.setCountHeat(boardState.heatCount + 1)
            updateLblAmountHeat()
        }

        heatBtnDecrementAmount.setOnClickListener {
            boardState.setCountHeat(boardState.heatCount - 1)
            updateLblAmountHeat()
        }

        heatBtnIncrementIncrement.setOnClickListener {
            boardState.setIncrementHeat(boardState.heatIncrement + 1)
            updateLblIncrementHeat()
        }

        heatBtnDecrementIncrement.setOnClickListener {
            boardState.setIncrementHeat(boardState.heatIncrement - 1)
            updateLblIncrementHeat()
        }

        // Bottom bar

        btnProduction = findViewById(R.id.production)
        btnForest = findViewById(R.id.forest)
        btnTempRise = findViewById(R.id.temp_rise)
        lblGeneration = findViewById(R.id.generation_label)

        updateLblGeneration()

        btnProduction.setOnClickListener {
            boardState.productionPhase()

            updateLblGeneration()

            updateLblAmountCoin()
            updateLblAmountSteel()
            updateLblAmountTitanium()
            updateLblAmountPlant()
            updateLblAmountEnergy()
            updateLblAmountHeat()
        }

        btnForest.setOnClickListener {
            boardState.buildForest()

            updateLblAmountPlant()
        }

        btnTempRise.setOnClickListener {
            boardState.raiseTemp()

            updateLblAmountHeat()
        }
    }

    @SuppressLint("RestrictedApi")
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.dropdown_menu, menu)

        if (menu is MenuBuilder) {
            menu.setOptionalIconsVisible(true)
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.undo -> {
                boardState.undo()
                updateAllLbl()
                true
            }

            R.id.clear -> {
                boardState.reset()
                updateAllLbl()
                true
            }

            R.id.about -> {
                Toast.makeText(this, "Developed by FA SP AR", Toast.LENGTH_SHORT)
                    .show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPause() {
        super.onPause()

        val preferences = getPreferences(MODE_PRIVATE)
        val editor = preferences.edit()

        editor.putString(KEY_BOARD_STATE, boardState.toString())
        editor.putString(KEY_EXPANDED_RES, expandedResources.toString())

        editor.apply()
    }

    private fun updateLblAmountCoin() {
        coinLblAmount.text = getString(R.string.coin_lbl, boardState.coinCount.toString())
        coinLblSubAmount.text = NumberFormatter.withLocale(java.util.Locale.ITALY)
            .sign(NumberFormatter.SignDisplay.ALWAYS).format(boardState.coinCount).toString()
    }

    private fun updateLblIncrementCoin() {
        coinLblIncrement.text = NumberFormatter.withLocale(java.util.Locale.ITALY)
            .sign(NumberFormatter.SignDisplay.ALWAYS).format(boardState.coinIncrement).toString()
        coinLblSubIncrement.text = NumberFormatter.withLocale(java.util.Locale.ITALY)
            .sign(NumberFormatter.SignDisplay.ALWAYS).format(boardState.coinIncrement).toString()
    }

    private fun updateLblAmountSteel() {
        steelLblAmount.text = getString(R.string.steel_lbl, boardState.steelCount.toString())
        steelLblSubAmount.text = NumberFormatter.withLocale(java.util.Locale.ITALY)
            .sign(NumberFormatter.SignDisplay.ALWAYS).format(boardState.steelCount).toString()
    }

    private fun updateLblIncrementSteel() {
        steelLblIncrement.text = NumberFormatter.withLocale(java.util.Locale.ITALY)
            .sign(NumberFormatter.SignDisplay.ALWAYS).format(boardState.steelIncrement)
            .toString()
        steelLblSubIncrement.text = NumberFormatter.withLocale(java.util.Locale.ITALY)
            .sign(NumberFormatter.SignDisplay.ALWAYS).format(boardState.steelIncrement)
            .toString()
    }

    private fun updateLblAmountTitanium() {
        titaniumLblAmount.text =
            getString(R.string.titanium_lbl, boardState.titaniumCount.toString())
        titaniumLblSubAmount.text = NumberFormatter.withLocale(java.util.Locale.ITALY)
            .sign(NumberFormatter.SignDisplay.ALWAYS).format(boardState.titaniumCount)
            .toString()
    }

    private fun updateLblIncrementTitanium() {
        titaniumLblIncrement.text = NumberFormatter.withLocale(java.util.Locale.ITALY)
            .sign(NumberFormatter.SignDisplay.ALWAYS).format(boardState.titaniumIncrement)
            .toString()
        titaniumLblSubIncrement.text = NumberFormatter.withLocale(java.util.Locale.ITALY)
            .sign(NumberFormatter.SignDisplay.ALWAYS).format(boardState.titaniumIncrement)
            .toString()
    }

    private fun updateLblAmountPlant() {
        plantLblAmount.text = getString(R.string.plant_lbl, boardState.plantCount.toString())
        plantLblSubAmount.text = NumberFormatter.withLocale(java.util.Locale.ITALY)
            .sign(NumberFormatter.SignDisplay.ALWAYS).format(boardState.plantCount).toString()
    }

    private fun updateLblIncrementPlant() {
        plantLblIncrement.text = NumberFormatter.withLocale(java.util.Locale.ITALY)
            .sign(NumberFormatter.SignDisplay.ALWAYS).format(boardState.plantIncrement)
            .toString()
        plantLblSubIncrement.text = NumberFormatter.withLocale(java.util.Locale.ITALY)
            .sign(NumberFormatter.SignDisplay.ALWAYS).format(boardState.plantIncrement)
            .toString()

    }

    private fun updateLblAmountEnergy() {
        energyLblAmount.text = getString(R.string.energy_lbl, boardState.energyCount.toString())
        energyLblSubAmount.text = NumberFormatter.withLocale(java.util.Locale.ITALY)
            .sign(NumberFormatter.SignDisplay.ALWAYS).format(boardState.energyCount).toString()
    }

    private fun updateLblIncrementEnergy() {
        energyLblIncrement.text = NumberFormatter.withLocale(java.util.Locale.ITALY)
            .sign(NumberFormatter.SignDisplay.ALWAYS).format(boardState.energyIncrement)
            .toString()
        energyLblSubIncrement.text = NumberFormatter.withLocale(java.util.Locale.ITALY)
            .sign(NumberFormatter.SignDisplay.ALWAYS).format(boardState.energyIncrement)
            .toString()
    }

    private fun updateLblAmountHeat() {
        heatLblAmount.text = getString(R.string.heat_lbl, boardState.heatCount.toString())
        heatLblSubAmount.text = NumberFormatter.withLocale(java.util.Locale.ITALY)
            .sign(NumberFormatter.SignDisplay.ALWAYS).format(boardState.heatCount).toString()
    }

    private fun updateLblIncrementHeat() {
        heatLblIncrement.text = NumberFormatter.withLocale(java.util.Locale.ITALY)
            .sign(NumberFormatter.SignDisplay.ALWAYS).format(boardState.heatIncrement)
            .toString()
        heatLblSubIncrement.text = NumberFormatter.withLocale(java.util.Locale.ITALY)
            .sign(NumberFormatter.SignDisplay.ALWAYS).format(boardState.heatIncrement)
            .toString()

    }

    private fun updateLblGeneration() {
        lblGeneration.text = getString(R.string.generation_lbl, boardState.generation.toString())
    }

    private fun updateAllLbl() {
        updateLblAmountCoin()
        updateLblIncrementCoin()
        updateLblAmountSteel()
        updateLblIncrementSteel()
        updateLblAmountTitanium()
        updateLblIncrementTitanium()
        updateLblAmountPlant()
        updateLblIncrementPlant()
        updateLblAmountEnergy()
        updateLblIncrementEnergy()
        updateLblAmountHeat()
        updateLblIncrementHeat()
        updateLblGeneration()
    }

    companion object {
        private const val KEY_BOARD_STATE = "boardStateValue"
        private const val KEY_EXPANDED_RES = "expandedResourcesValue"
    }
}