package io.github.jerrymatera.projectsmanager.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.jerrymatera.projectsmanager.presentation.ui.theme.ProjectsManagerTheme

@Composable
fun EmptyItemCard(title: String, onAddClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
        ) {
            Text(text = title)
            Spacer(modifier = Modifier.height(16.dp))
            IconButton(onClick = { onAddClick() }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Create New Item")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun EmptyProjectCardPrev() {
    ProjectsManagerTheme {
        EmptyItemCard(title = "Create new Item",onAddClick = { /*TODO*/ })
    }
}