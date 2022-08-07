package com.example.dbm.popularmovieskt.presentation.view.components.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.dbm.popularmovieskt.R
import com.example.dbm.popularmovieskt.global.Constants

@OptIn(ExperimentalTextApi::class)
@Composable
fun TopBar(
    showMenu: Boolean,
    titleTopBar: String,
    navigationType: Constants.NavType,
    goBackToMain: () -> Unit,
    onDismissMenu: () -> Unit,
    onMenuIconClick: () -> Unit,
    onMenuItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        contentPadding = PaddingValues(start = 18.dp),
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            when (navigationType) {
                Constants.NavType.NAV_MAIN -> {}
                Constants.NavType.NAV_DETAILS -> {
                    Icon(
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .clickable {
                                goBackToMain()
                            },
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.go_back_content_description),
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
            }
            Text(
                modifier = Modifier.wrapContentHeight(),
                text = titleTopBar,
                fontSize = 22.sp,
                color = MaterialTheme.colors.onPrimary,
                style = LocalTextStyle.current.merge(
                    TextStyle(
                        lineHeight = 22.sp,
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        ),
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Center,
                            trim = LineHeightStyle.Trim.None
                        )
                    )
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            when (navigationType) {
                Constants.NavType.NAV_MAIN -> {
                    Row(
                        modifier = Modifier.padding(end = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(top = 16.dp, bottom = 16.dp, end = 12.dp)
                                .clickable
                                {
                                    onMenuIconClick()
                                },
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = stringResource(id = R.string.more_options_content_description),
                            tint = MaterialTheme.colors.onPrimary
                        )

                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { onDismissMenu() })
                        {
                            DropdownMenuItem(
                                onClick = {
                                    onMenuItemClick(Constants.SORT_BY_POPULAR)
                                }
                            ) {
                                Text(text = stringResource(id = R.string.sort_by_popular_option))
                            }
                            DropdownMenuItem(
                                onClick = {
                                    onMenuItemClick(Constants.SORT_BY_HIGHEST_RATED)
                                }
                            ) {
                                Text(text = stringResource(id = R.string.sort_by_highest_rated_option))
                            }
                            DropdownMenuItem(
                                onClick = {
                                    onMenuItemClick(Constants.SORT_BY_FAVORITE_MOVIES)
                                }
                            ) {
                                Text(text = stringResource(id = R.string.sort_by_favorites_option))
                            }
                        }
                    }
                }
                Constants.NavType.NAV_DETAILS -> {}
            }
        }
    }
}