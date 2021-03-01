/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.models.Puppy
import com.example.androiddevchallenge.ui.viewmodel.PuppiesListViewModel

@Composable
fun PuppiesListScreen(navController: NavController, puppiesListviewModel: PuppiesListViewModel = viewModel()) {
    val puppies: List<Puppy> by puppiesListviewModel.puppiesList.observeAsState(emptyList())
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.app_name))
                }
            )
        }
    ) {
        PuppiesList(puppies, navController)
    }
}

@Composable
fun PuppiesList(list: List<Puppy>, navController: NavController) {
    LazyColumn(Modifier.fillMaxWidth()) {
        items(list) { item ->
            PuppiesListItem(
                item,
                onClicked = {
                    navController.navigate("details/${item.id}")
                }
            )
            Divider(color = Color.Black)
        }
    }
}

@Composable
fun PuppiesListItem(puppy: Puppy, onClicked: () -> Unit) {
    Row(
        modifier = Modifier
            .clickable(onClick = onClicked)
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Surface(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            // Photo here
        }
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(text = puppy.name)
            Text(text = puppy.breed)
        }
    }
}
