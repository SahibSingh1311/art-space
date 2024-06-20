package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ArtSpaceLayout()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceLayout( modifier: Modifier = Modifier) {
    val firstArtWork = R.drawable.greek_guy
    val secondArtWork = R.drawable.forest_dawn_deer
    val fifthArtWork = R.drawable.urban_double_exposure_collage_concept

    var title by remember {
        mutableStateOf(R.string.greek_guy)
    }

    var year by remember {
        mutableStateOf(R.string.greek_guy_year)
    }

    var currentArtwork by remember {
        mutableStateOf(firstArtWork)
    }

    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {
        ArtWall(currentArtwork, modifier.padding(16.dp))
        ArtWallDescriptor(title, year, modifier.padding(bottom = 8.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp,Alignment.CenterHorizontally))
        {
            //Previous Button
            Button(onClick = {
                when(currentArtwork) {
                firstArtWork -> {
                    currentArtwork = fifthArtWork
                    title = R.string.urban_double_exposure_concept
                    year = R.string.urban_double_exposure_concept_year
                }
                secondArtWork -> {
                    currentArtwork = firstArtWork
                    title = R.string.greek_guy
                    year = R.string.greek_guy_year
                }
                else -> {
                    currentArtwork = secondArtWork
                    title = R.string.psychedelic_hand
                    year = R.string.psychedelic_hand_year
                }
            } },
                modifier = modifier.size(120.dp,50.dp)) {
                Text(text = "Previous")
            }
            //Next Button
            Button(onClick = {
                when(currentArtwork) {
                firstArtWork -> {
                    currentArtwork = secondArtWork
                    title = R.string.forest_dawn_deer
                    year = R.string.forest_dawn_deer_year
                }
                secondArtWork -> {
                    currentArtwork = fifthArtWork
                    title = R.string.picture_with_drawing_man_face
                    year = R.string.picture_with_drawing_man_face_year
                }
                else -> {
                    currentArtwork = firstArtWork
                    title = R.string.greek_guy
                    year = R.string.greek_guy_year
                }
            } },
                modifier = modifier.size(120.dp,50.dp)) {
                Text(text = "Next")
            }
        }
    }
}

@Composable
fun ArtWall(
    @DrawableRes artWork : Int, modifier: Modifier = Modifier) {
    Surface(modifier.wrapContentSize(),
        shadowElevation = 20.dp) {
        Image(
            painter = painterResource(id = artWork),
            contentDescription = stringResource(id = R.string.greek_guy_year),
            modifier = modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
fun ArtWallDescriptor(
    @StringRes title: Int,
    @StringRes year: Int, modifier: Modifier) {
    Column (modifier,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stringResource(id = title))
        Text(text = stringResource(id = year))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}