package net.yuuzu.diarynoteapp.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.EditCalendar
import androidx.compose.material.icons.rounded.Image
import androidx.compose.material.icons.rounded.ReadMore
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import net.yuuzu.diarynoteapp.presentation.main.components.CustomGraphic
import net.yuuzu.diarynoteapp.presentation.main.components.GraphicType
import net.yuuzu.diarynoteapp.presentation.main.components.SectionBox
import net.yuuzu.diarynoteapp.presentation.main.components.ShortcutButton
import net.yuuzu.diarynoteapp.presentation.main.components.TopHeader
import net.yuuzu.diarynoteapp.presentation.main.components.VerticalGraphic

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    bottomSheetState: BottomSheetScaffoldState // TODO: 之後要放在 View Model 中以避免重建。
) {
    BottomSheetScaffold(
        scaffoldState = bottomSheetState,
        topBar = {
            TopBarContent()
        },
        sheetContent = {
            SheetContent()
        },
        sheetSwipeEnabled = true,
        sheetPeekHeight = 280.dp,
        sheetContainerColor = MaterialTheme.colorScheme.background,
        sheetContentColor = MaterialTheme.colorScheme.onBackground
    ) {
        MainContent()
    }
}

@Composable
fun TopBarContent(
    // TODO: 之後要改成從 View Model 中取得資料。
) {
    TopHeader(
        name = "Yuuzu",
        weather = "Cloudy, 25°C",
        profileImage = null,
        onProfileClicked = {},
        options = {
            CustomGraphic(
                graphicType = GraphicType.Icon(
                    imageVector = Icons.Rounded.Settings,
                    tint = MaterialTheme.colorScheme.onPrimary
                ),
                size = 24.dp,
                background = MaterialTheme.colorScheme.inversePrimary.copy(0.4f),
                onClicked = {}
            )
        }
    )
}

@Composable
fun MainContent(
    // TODO: 之後要改成從 View Model 中取得資料。
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Current Total Diary: ",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.onPrimary,
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = "0",
                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
        SectionBox {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                ShortcutButton(
                    text = "New Diary",
                    icon = Icons.Rounded.Add,
                    iconTint = MaterialTheme.colorScheme.primary,
                    background = MaterialTheme.colorScheme.primary.copy(0.1f),
                    onShortcutClicked = {},
                    modifier = Modifier.weight(1f)
                )
                ShortcutButton(
                    text = "Date Search",
                    icon = Icons.Rounded.EditCalendar,
                    iconTint = MaterialTheme.colorScheme.secondary,
                    background = MaterialTheme.colorScheme.secondary.copy(0.1f),
                    onShortcutClicked = {},
                    modifier = Modifier.weight(1f)
                )
                ShortcutButton(
                    text = "Show All",
                    icon = Icons.Rounded.ReadMore,
                    iconTint = MaterialTheme.colorScheme.tertiary,
                    background = MaterialTheme.colorScheme.tertiary.copy(0.1f),
                    onShortcutClicked = {},
                    modifier = Modifier.weight(1f)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        RecentlyActivity()
    }
}

@Composable
private fun SheetContent(
    // TODO: 之後要改成從 View Model 中取得資料。
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        item {
            TagFilter()
        }
        items(10) {
            DiaryItem()
        }
    }
}

@Composable
private fun RecentlyActivity(
    // TODO: 之後要改成從 View Model 中取得資料。
) {
    SectionBox(
        title = "Recently Added Diary",
    ) {
        LazyRow(
            contentPadding = PaddingValues(end = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            items(10) {
                VerticalGraphic(
                    text = "Debug",
                    graphicType = GraphicType.Icon(
                        imageVector = Icons.Rounded.Image,
                        tint = Color.Unspecified
                    ),
                    textColor = MaterialTheme.colorScheme.onBackground,
                    textStyle = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Medium),
                    backgroundColor = MaterialTheme.colorScheme.inversePrimary.copy(alpha = 0.1f),
                    contentDescription = "item images",
                    onClicked = {}
                )
            }
        }
    }
}

@Composable
fun TagFilter(
    // TODO: 之後要改成從 View Model 中取得資料。
) {
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        items(10) {
            Box(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        shape = MaterialTheme.shapes.small,
                        color = if (it == 0) Color.Transparent
                                else MaterialTheme.colorScheme.primary
                    )
                    .clip(MaterialTheme.shapes.large)
                    .background(
                        if (it == 0) MaterialTheme.colorScheme.primary.copy(0.1f)
                        else Color.Transparent
                    )
                    .clickable {  }
                    .padding(horizontal = 24.dp, vertical = 12.dp)
            ) {
                Text(
                    text = "Item$it",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Medium),
                    color = if (it == 0) MaterialTheme.colorScheme.onPrimary
                            else MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}

@Composable
fun DiaryItem(
    // TODO: 之後要改成從 View Model 中取得資料。
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {  }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        CustomGraphic(
            graphicType = GraphicType.Icon(
                imageVector = Icons.Rounded.Image,
                tint = MaterialTheme.colorScheme.onPrimary
            ),
            size = 50.dp,
            background = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
            onClicked = {}
        )
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Debug",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "2023/11/11",
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Medium),
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End,
        ) {
            Text(
                text = "Debug",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Medium),
            )
        }
    }
}