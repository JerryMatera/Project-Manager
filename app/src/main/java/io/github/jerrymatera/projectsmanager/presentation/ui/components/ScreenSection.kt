package io.github.jerrymatera.projectsmanager.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp


@Composable
fun ScreenSection(
    sectionTitle: String,
    modifier: Modifier = Modifier,
    sectionTitleStyle: TextStyle = MaterialTheme.typography.titleMedium,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(text = sectionTitle, style = sectionTitleStyle)
        content()
    }
}