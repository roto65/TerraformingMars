package com.roto65.terraformingmars

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowWidthSizeClass
import com.roto65.terraformingmars.ui.theme.TerraformingMarsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferences = getPreferences(MODE_PRIVATE)
        val prevBoardState = BoardState()
        prevBoardState.fromString(preferences.getString(KEY_BOARD_STATE, null))

        enableEdgeToEdge()
        setContent {
            TerraformingMarsTheme {
                App(prevState = prevBoardState)
            }
        }
    }

    override fun onPause() {
        super.onPause()

        val preferences = getPreferences(MODE_PRIVATE)
        val editor = preferences.edit()

        editor.putString(KEY_BOARD_STATE, boardState.toString())

        editor.apply()
    }

    companion object {
        private const val KEY_BOARD_STATE = "boardStateValue"
    }
}

lateinit var boardState: BoardState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(modifier: Modifier = Modifier, prevState: BoardState = BoardState()) {

    boardState = rememberSaveable(stateSaver = BoardState.Saver) {
        mutableStateOf(prevState)
    }.value

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            Header(scrollBehavior)
        },
        bottomBar = {
            Box(
                modifier = modifier
                    .shadow(
                        elevation = 10.dp,
                        spotColor = Color.Red,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Footer(boardState)
            }
        },
    ) { padding ->
        Row(
            modifier = modifier
                .padding(padding)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = modifier.padding(8.dp)
            ) {
                Body(boardState)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(scrollBehavior: TopAppBarScrollBehavior) {
    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    val context = LocalContext.current
    val credits = stringResource(id = R.string.credits)

    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(R.string.app_name))
        },
        actions = {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = null
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                DropdownMenuItem(onClick = {
                    boardState.undo()
                },
                    enabled = boardState.canUndo,
                    text = {
                        Text("Undo")
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.rounded_undo_24),
                            contentDescription = null
                        )
                    }
                )
                DropdownMenuItem(onClick = {
                    boardState.reset()
                    expanded = false
                },
                    text = {
                        Text("Clear")
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = null
                        )
                    }
                )
                DropdownMenuItem(onClick = {
                    Toast.makeText(context, credits, Toast.LENGTH_SHORT).show()
                },
                    text = {
                        Text("About")
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = null
                        )
                    }
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@Composable
fun Body(boardState: BoardState) {

    val isWindowSizeLarge =
        currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED

    val resExpanded by rememberSaveable(stateSaver = ExpandedResources.Saver) {
        mutableStateOf(ExpandedResources())
    }

    if (isWindowSizeLarge) {
        BodyExpanded(boardState = boardState, resExpanded = resExpanded)
    } else {
        BodyMedium(boardState = boardState, resExpanded = resExpanded)
    }
}

@Composable
fun Footer(boardState: BoardState, modifier: Modifier = Modifier) {
    val horizontalPadding = 8.dp
    val colWidth = (LocalConfiguration.current.screenWidthDp.dp - (horizontalPadding * 2)) / 4
    val imgWidth = 48.dp

    val context = LocalContext.current
    val forestFail = stringResource(id = R.string.forest_fail)
    val tempRiseFail = stringResource(id = R.string.temp_rise_fail)

    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = horizontalPadding),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = modifier.width(colWidth),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier.height(imgWidth)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.genrationicon),
                        contentDescription = null,
                        modifier = modifier
                            .width(40.dp)
                            .aspectRatio(1f)
                    )
                    Text(
                        text = " : " + boardState.generation.toString(),
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                }
                Text(
                    text = "Generation",
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .width(colWidth)
                    .clickable(onClick = boardState::productionPhase)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.gears),
                    contentDescription = null,
                    modifier = modifier
                        .width(imgWidth)
                        .aspectRatio(1f)
                )
                Text(
                    text = "Production",
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .width(colWidth)
                    .clickable(onClick = {
                        val success = boardState.buildForest()

                        if (!success) {
                            Toast
                                .makeText(context, forestFail, Toast.LENGTH_SHORT)
                                .show()
                        }
                    })
            ) {
                Image(
                    painter = painterResource(id = R.drawable.foresticon),
                    contentDescription = null,
                    modifier = modifier
                        .width(imgWidth)
                        .aspectRatio(1f)
                )
                Text(
                    text = "Forest",
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .width(colWidth)
                    .clickable(onClick = {
                        val success = boardState.raiseTemp()

                        if (!success) {
                            Toast
                                .makeText(context, tempRiseFail, Toast.LENGTH_SHORT)
                                .show()
                        }
                    })
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tempicon),
                    contentDescription = null,
                    modifier = modifier
                        .width(imgWidth)
                        .aspectRatio(1f)
                )
                Text(
                    text = "Temp Rise",
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                )
            }
        }
    }
}


@Preview(showBackground = true, widthDp = 360, heightDp = 780)
@Composable
fun AppPreviewPhonePortrait() {
    TerraformingMarsTheme {
        App()
    }
}

@Preview(showBackground = true, widthDp = 780, heightDp = 360)
@Composable
fun AppPreviewPhoneLandscape() {
    TerraformingMarsTheme {
        App()
    }
}

@Preview(showBackground = true, widthDp = 1205, heightDp = 753)
@Composable
fun AppPreviewTabletLandscape() {
    TerraformingMarsTheme {
        App()
    }
}

@Preview(showBackground = true, widthDp = 753, heightDp = 1205)
@Composable
fun AppPreviewTabletPortrait() {
    TerraformingMarsTheme {
        App()
    }
}
