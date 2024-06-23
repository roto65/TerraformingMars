package com.roto65.terraformingmars

import android.icu.number.NumberFormatter
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun BodyExpanded(
    boardState: BoardState,
    resExpanded: ExpandedResources,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column(
            modifier = modifier
                .weight(0.49f)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)

        ) {
            Resource(
                type = ResourceType.COIN,
                amount = boardState.coinCount,
                increment = boardState.coinIncrement,
                setAmount = boardState::setCountCoin,
                setIncrement = boardState::setIncrementCoin,
                resExpanded = resExpanded
            )
            Resource(
                type = ResourceType.TITANIUM,
                amount = boardState.titaniumCount,
                increment = boardState.titaniumIncrement,
                setAmount = boardState::setCountTitanium,
                setIncrement = boardState::setIncrementTitanium,
                resExpanded = resExpanded
            )
            Resource(
                type = ResourceType.ENERGY,
                amount = boardState.energyCount,
                increment = boardState.energyIncrement,
                setAmount = boardState::setCountEnergy,
                setIncrement = boardState::setIncrementEnergy,
                resExpanded = resExpanded
            )
        }
        Column(
            modifier = modifier
                .weight(0.49f)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Resource(
                type = ResourceType.STEEL,
                amount = boardState.steelCount,
                increment = boardState.steelIncrement,
                setAmount = boardState::setCountSteel,
                setIncrement = boardState::setIncrementSteel,
                resExpanded = resExpanded
            )
            Resource(
                type = ResourceType.PLANT,
                amount = boardState.plantsCount,
                increment = boardState.plantsIncrement,
                setAmount = boardState::setCountPlants,
                setIncrement = boardState::setIncrementPlants,
                resExpanded = resExpanded
            )
            Resource(
                type = ResourceType.HEAT,
                amount = boardState.heatCount,
                increment = boardState.heatIncrement,
                setAmount = boardState::setCountHeat,
                setIncrement = boardState::setIncrementHeat,
                resExpanded = resExpanded
            )
        }
    }
}

@Composable
fun BodyMedium(
    boardState: BoardState,
    resExpanded: ExpandedResources,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Resource(
            type = ResourceType.COIN,
            amount = boardState.coinCount,
            increment = boardState.coinIncrement,
            setAmount = boardState::setCountCoin,
            setIncrement = boardState::setIncrementCoin,
            resExpanded = resExpanded
        )

        Resource(
            type = ResourceType.STEEL,
            amount = boardState.steelCount,
            increment = boardState.steelIncrement,
            setAmount = boardState::setCountSteel,
            setIncrement = boardState::setIncrementSteel,
            resExpanded = resExpanded
        )
        Resource(
            type = ResourceType.TITANIUM,
            amount = boardState.titaniumCount,
            increment = boardState.titaniumIncrement,
            setAmount = boardState::setCountTitanium,
            setIncrement = boardState::setIncrementTitanium,
            resExpanded = resExpanded
        )
        Resource(
            type = ResourceType.PLANT,
            amount = boardState.plantsCount,
            increment = boardState.plantsIncrement,
            setAmount = boardState::setCountPlants,
            setIncrement = boardState::setIncrementPlants,
            resExpanded = resExpanded
        )
        Resource(
            type = ResourceType.ENERGY,
            amount = boardState.energyCount,
            increment = boardState.energyIncrement,
            setAmount = boardState::setCountEnergy,
            setIncrement = boardState::setIncrementEnergy,
            resExpanded = resExpanded
        )
        Resource(
            type = ResourceType.HEAT,
            amount = boardState.heatCount,
            increment = boardState.heatIncrement,
            setAmount = boardState::setCountHeat,
            setIncrement = boardState::setIncrementHeat,
            resExpanded = resExpanded
        )
    }
}

@Composable
fun Resource(
    type: ResourceType,
    amount: Int,
    increment: Int,
    setAmount: (Int) -> Unit,
    setIncrement: (Int) -> Unit,
    resExpanded: ExpandedResources,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .animateContentSize()
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {
            Row(
                modifier = modifier
                    .padding(
                        top = 16.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = if (resExpanded.isExpanded(type)) 0.dp else 16.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = type.getIcon()),
                    contentDescription = null,
                    modifier = modifier
                        .width(64.dp)
                        .aspectRatio(1f)
                )
                Text(
                    text = type.getName() + ": " + amount.toString(),
                    modifier = modifier
                        .weight(1f)
                        .padding(start = 16.dp),
                )
                Text(
                    text = NumberFormatter.withLocale(java.util.Locale.ITALY)
                        .sign(NumberFormatter.SignDisplay.ALWAYS).format(increment).toString(),
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                    )
                )
                Icon(
                    painter = painterResource(id = if (resExpanded.isExpanded(type)) R.drawable.rounded_keyboard_arrow_up_24 else R.drawable.rounded_keyboard_arrow_down_24),
                    contentDescription = null,
                    modifier = modifier
                        .padding(start = 16.dp)
                        .width(32.dp)
                        .aspectRatio(1f)
                        .clickable(onClick = {
                           resExpanded.reverse(type)
                        })
                )
            }

            if (resExpanded.isExpanded(type)) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(id = R.string.label_amount),
                            modifier = modifier.padding(bottom = 8.dp),
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.ExtraBold
                            )
                        )
                        NumberInput(amount, setAmount)
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.label_increment),
                            modifier = modifier.padding(bottom = 8.dp),
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.ExtraBold
                            )
                        )
                        NumberInput(increment, setIncrement)
                    }
                }
            }
        }
    }
}

@Composable
fun NumberInput(
    value: Int,
    setValue: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ElevatedButton(
            onClick = { setValue(value - 1) },
            modifier = modifier
                .width(48.dp)
                .height(32.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.rounded_remove_24),
                contentDescription = "Decrease value",
                modifier = modifier
                    .width(32.dp)
                    .aspectRatio(1f)
            )
        }
        Text(
            text = NumberFormatter.withLocale(java.util.Locale.ITALY)
                .sign(NumberFormatter.SignDisplay.ALWAYS).format(value).toString(),
            modifier = modifier.padding(horizontal = 16.dp),
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.ExtraBold
            )
        )
        ElevatedButton(
            onClick = { setValue(value + 1) },
            modifier = modifier
                .width(48.dp)
                .height(32.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.rounded_add_24),
                contentDescription = "Increase value",
                modifier = modifier
                    .width(32.dp)
                    .aspectRatio(1f)
            )
        }
    }
}