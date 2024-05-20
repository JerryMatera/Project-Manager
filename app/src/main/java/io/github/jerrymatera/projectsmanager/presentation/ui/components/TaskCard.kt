package io.github.jerrymatera.projectsmanager.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.jerrymatera.projectsmanager.data.network.model.ArchivedStatus
import io.github.jerrymatera.projectsmanager.data.network.model.Task
import io.github.jerrymatera.projectsmanager.presentation.ui.theme.ProjectsManagerTheme

@Composable
fun TaskCard(
    task: Task,
    modifier: Modifier = Modifier,
    hideProjectNames: Boolean = false
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = task.name, style = MaterialTheme.typography.titleLarge)
            if (hideProjectNames) {
                Text(text = task.projectName, style = MaterialTheme.typography.titleSmall)
            }
            ScreenSection(
                sectionTitle = "Project Timelines",
                content = {
                    Column {
                        Text(text = "Created at:${task.createdAt}")
                        Text(text = "Deadline:${task.deadline}")
                    }
                })
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun TaskCardPrev() {
    ProjectsManagerTheme {
        TaskCard(
            task = Task(
                uuid = "193dd254-95c5-4e24-ac3f-95975f1a03bc",
                name = "Design",
                deadline = "2024-06-01T02:00:00Z",
                createdAt = "2024-05-15T15:50:35Z",
                projectUuid = "2bc9fbde-8634-4806-9b20-622be40bc718",
                archivedStatus = ArchivedStatus(
                    time = "0001-01-01T00:00:00Z",
                    valid = false
                ),
                projectName = "ProjectAI"
            )
        )
    }
}