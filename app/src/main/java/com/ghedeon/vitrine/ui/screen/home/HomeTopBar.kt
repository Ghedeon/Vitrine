package com.ghedeon.vitrine.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ghedeon.vitrine.R

@Composable
fun HomeTopBar() {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.home_screen_title))
        },
        backgroundColor = MaterialTheme.colors.background,
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.pickle),
                modifier = Modifier.padding(8.dp),
                contentDescription = null
            )
        }
    )
}

@Composable
@Preview
fun HomeTopBarPreview() {
    HomeTopBar()
}