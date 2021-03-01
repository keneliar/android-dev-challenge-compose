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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardBackspace
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.models.Puppy
import com.example.androiddevchallenge.ui.viewmodel.PuppyDetailViewModel

@Composable
fun PuppyDetailScreen(navController: NavController, puppyId: Int) {
    val viewModel: PuppyDetailViewModel = viewModel(factory = PuppyDetailViewModel.Factory(puppyId))
    val puppy by viewModel.puppy.observeAsState()
    PuppyDetail(puppy, navController)
}

@Composable
fun PuppyDetail(puppy: Puppy?, navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Rounded.KeyboardBackspace, contentDescription = stringResource(R.string.backstack_button_description))
                    }
                },
                title = {
                    Text(puppy?.name ?: "")
                }
            )
        }
    ) {
        PuppyDetailContent(puppy)
    }
}

@Composable
fun PuppyDetailContent(puppy: Puppy?) {
    val typography = MaterialTheme.typography
    puppy?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Surface(
                    modifier = Modifier
                        .height(180.dp)
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(6.dp)),
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(stringResource(R.string.photo_not_load, puppy.name)) // Full Photo here, change to Image()
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(stringResource(R.string.breed, it.breed), style = typography.h6)
                Text(stringResource(R.string.sex, it.sex))
                Text(stringResource(R.string.age, it.age))
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                Button(
                    onClick = { /* click */ },
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(stringResource(R.string.button_pick))
                }
            }
        }
    }
}
