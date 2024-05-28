package com.plcoding.mycashtask.yjahz.presentation.home

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.isLiveLiteralsEnabled
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.NavController
import com.plcoding.mycashtask.yjahz.presentation.home.components.AppTopBar
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import com.plcoding.mycashtask.R
import com.plcoding.mycashtask.yjahz.data.model.catrgories.Categories
import com.plcoding.mycashtask.yjahz.data.model.sellers.PopularSeller
import com.plcoding.mycashtask.yjahz.data.model.sellers.Seller
import com.plcoding.mycashtask.yjahz.presentation.MainViewModel
import com.plcoding.mycashtask.yjahz.presentation.home.components.BasicTextField
import com.plcoding.mycashtask.yjahz.presentation.home.components.shimmerBrush

private const val TITLE_TAG = "title-tag"
private const val ADDRESS_TAG = "address-tag"
private const val SEARCH_TAG = "search-tag"
private const val SEARCH_ICON_TAG = "search-icon-tag"
private const val FILTER_TAG = "filter-tag"
private const val ARROW_TAG = "arrow-tag"
private const val CATEGORIES_TITLE_TAG = "categories-tag"
private const val CATEGORIES_LIST_TAG = "catg-list-tag"
private const val CATEGORIES_VIEWALL_TAG = "catg-viewall-tag"
private const val POPULAR_TITLE_TAG = "popular-tag"
private const val POPULAR_LIST_TAG = "popular-list-tag"
private const val POPULAR_VIEWALL_TAG = "popular-viewall-tag"
private const val TRENDING_TITLE_TAG = "trending-tag"
private const val TRENDING_LIST_TAG = "trending-list-tag"
private const val TRENDING_VIEWALL_TAG = "trending-viewall-tag"


@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(null)
}

@ExperimentalAnimationApi
@Composable
fun HomeScreen(
    navController: NavController?,
    viewModel: MainViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val userState = viewModel.userFlow.collectAsState(initial = null)
    val user = userState.value

    val categoriesState = viewModel.categoriesFlow.collectAsState()
    val categories = categoriesState.value?.data

    val trendiesState = viewModel.trendingSellersFlow.collectAsState()
    val trendies = trendiesState.value?.data

    val popularState = viewModel.popularSellersFlow.collectAsState()
    val popular = popularState.value?.data

    val showShimmer = remember { mutableStateOf(true) }


    val constraintSet = ConstraintSet {
        val title = createRefFor(TITLE_TAG)
        val address = createRefFor(ADDRESS_TAG)
        val search = createRefFor(SEARCH_TAG)
        val searchIcon = createRefFor(SEARCH_ICON_TAG)
        val filter = createRefFor(FILTER_TAG)
        val arrow = createRefFor(ARROW_TAG)
        val categoryTitle = createRefFor(CATEGORIES_TITLE_TAG)
        val categoryList = createRefFor(CATEGORIES_LIST_TAG)
        val categoryViewALl = createRefFor(CATEGORIES_VIEWALL_TAG)
        val populareTitle = createRefFor(POPULAR_TITLE_TAG)
        val popularList = createRefFor(POPULAR_LIST_TAG)
        val popularViewALl = createRefFor(POPULAR_VIEWALL_TAG)
        val trendingTitle = createRefFor(TRENDING_TITLE_TAG)
        val trendingList = createRefFor(TRENDING_LIST_TAG)
        val trendingViewALl = createRefFor(TRENDING_VIEWALL_TAG)

        constrain(title) {
            top.linkTo(parent.top, margin = 10.dp)
            start.linkTo(parent.start, margin = 10.dp)
        }

        constrain(address) {
            top.linkTo(title.bottom, margin = 5.dp)
            start.linkTo(parent.start, margin = 10.dp)
        }

        constrain(arrow) {
            top.linkTo(title.bottom, margin = 5.dp)
            start.linkTo(address.end, margin = 10.dp)
        }

        constrain(filter) {
            top.linkTo(address.bottom, margin = 50.dp)
            end.linkTo(parent.end, margin = 10.dp)
        }

        constrain(searchIcon) {
            top.linkTo(address.bottom, margin = 45.dp)
            end.linkTo(filter.end, margin = 40.dp)
        }

        constrain(search) {
            top.linkTo(address.bottom, margin = 35.dp)
            start.linkTo(parent.start, margin = 10.dp)
            end.linkTo(searchIcon.start, margin = 5.dp)
            width = Dimension.fillToConstraints
        }

        constrain(categoryTitle) {
            top.linkTo(search.bottom, margin = 20.dp)
            start.linkTo(parent.start, margin = 10.dp)
        }

        constrain(categoryViewALl) {
            top.linkTo(search.bottom, margin = 20.dp)
            end.linkTo(parent.end, margin = 10.dp)
        }

        constrain(categoryList) {
            top.linkTo(categoryTitle.bottom, margin = 10.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
        }

        constrain(populareTitle) {
            top.linkTo(categoryList.bottom, margin = 20.dp)
            start.linkTo(parent.start, margin = 10.dp)
        }

        constrain(popularViewALl) {
            top.linkTo(categoryList.bottom, margin = 20.dp)
            end.linkTo(parent.end, margin = 10.dp)
        }

        constrain(popularList) {
            top.linkTo(populareTitle.bottom, margin = 10.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
        }

        constrain(trendingTitle) {
            top.linkTo(popularList.bottom, margin = 20.dp)
            start.linkTo(parent.start, margin = 10.dp)
        }

        constrain(trendingViewALl) {
            top.linkTo(popularList.bottom, margin = 20.dp)
            end.linkTo(parent.end, margin = 10.dp)
        }

        constrain(trendingList) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(trendingTitle.bottom, margin = 10.dp)
            width = Dimension.fillToConstraints
        }

    }

    val userAddress = runCatching { user?.addresses?.firstOrNull()?.address }.getOrElse { "" }

    var search by remember { mutableStateOf("") }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize(),
        backgroundColor = Color(0xFFFFFFFF),
        topBar = {
            AppTopBar()
        }
    ) { _ ->
        ConstraintLayout(
            constraintSet = constraintSet,
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(color = Color(0xFFFBFBFB))//
        ) {
            user?.name?.let {
                Text(
                    text = it,
                    style = TextStyle(
                        fontSize = 22.sp,
                        color = Color(0xff484848),
                        lineHeight = 38.24.sp,
                        letterSpacing = (-0.42).sp,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .layoutId(TITLE_TAG)
                )
            }

            Text(
                text = userAddress ?: "No Address Added",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color(0xff484848),
                    lineHeight = 28.68.sp,
                    letterSpacing = (-0.52).sp,
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier
                    .padding(start = 10.dp)
                    .layoutId(ADDRESS_TAG)
            )

            Icon(
                painterResource(id = R.drawable.ic_back), contentDescription = "Arrow",
                modifier = Modifier
                    .size(18.dp)
                    .rotate(180f)
                    .layoutId(ARROW_TAG),
                tint = Color(0xFFFFD279)
            )

            BasicTextField(
                title = "",
                hint = stringResource(id = R.string.search_hint),
                input = search,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .layoutId(SEARCH_TAG)
            ) { query ->
                search = query
            }
//
            Icon(
                painterResource(id = R.drawable.ic_filter),
                contentDescription = "Filter",
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(19.dp)
                    .layoutId(FILTER_TAG),
                tint = Color(0xFFFFD279)
            )
//
            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search",
                modifier = Modifier
                    .size(30.dp)
                    .layoutId(SEARCH_ICON_TAG),
            )

            Text(
                text = "Categories",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color(0xff484848),
                    lineHeight = 21.68.sp,
                    letterSpacing = (-0.3).sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
                    .padding(start = 20.dp)
                    .layoutId(CATEGORIES_TITLE_TAG)
            )

            UnderlineText(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .layoutId(CATEGORIES_VIEWALL_TAG), text = "ViewAll"
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = PaddingValues(10.dp),
                modifier = Modifier.layoutId(CATEGORIES_LIST_TAG)
            ) {
                items(categories?.size ?: 5) { index ->
                    CategoryItem(categories?.get(index))
                }
            }


            Text(
                text = "Popular Now",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color(0xff484848),
                    lineHeight = 21.68.sp,
                    letterSpacing = (-0.3).sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
                    .padding(start = 20.dp)
                    .layoutId(POPULAR_TITLE_TAG)
            )

            UnderlineText(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .layoutId(POPULAR_VIEWALL_TAG), text = "ViewAll"
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = PaddingValues(10.dp),
                modifier = Modifier.layoutId(POPULAR_LIST_TAG)
            ) {
                items(popular?.size ?: 5) { index ->
                    PopularItem(popular?.get(index))
                }
            }

            Text(
                text = "Trending Now",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color(0xff484848),
                    lineHeight = 21.68.sp,
                    letterSpacing = (-0.3).sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
                    .padding(start = 20.dp)
                    .layoutId(TRENDING_TITLE_TAG)
            )

            UnderlineText(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .layoutId(TRENDING_VIEWALL_TAG), text = "ViewAll"
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = PaddingValues(10.dp),
                modifier = Modifier.layoutId(TRENDING_LIST_TAG)
            ) {
                items(trendies?.size ?: 5) { index ->
                    TrendyItem(trendies?.get(index))
                }
            }


        }
    }
}

@Composable
fun CategoryItem(categories: Categories?, viewModel: MainViewModel = hiltViewModel()) {
    val isLoading = viewModel.isLoading.collectAsState()

    val modifier = if (isLoading.value) Modifier
        .background(
            shimmerBrush(
                targetValue = 1300f,
                showShimmer = isLoading.value
            ),
            shape = RoundedCornerShape(10)
        )
    else {
        Modifier
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(10.dp),
                ambientColor = Color(0x1AFFFFFF)
            )
            .background(
                shape = RoundedCornerShape(10),
                color = Color.White
            )
    }

    Column(
        modifier = modifier
            .width(136.dp)
            .height(114.dp)
    ) {
        AsyncImage(
            model = categories?.image,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 20.dp, end = 20.dp)
                .height(60.dp)
                .width(80.dp)
        )

        categories?.name?.let {
            Text(
                text = it,
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color(0xff484848),
                    lineHeight = 14.34.sp,
                    letterSpacing = (-0.3).sp,
                    fontWeight = FontWeight.Normal,
                ),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 20.dp, top = 5.dp)
            )
        }
    }
}

@Composable
fun TrendyItem(seller: Seller?, viewModel: MainViewModel = hiltViewModel()) {
    val isLoading = viewModel.isLoading.collectAsState()

    val modifier = if (isLoading.value) Modifier
        .background(
            shimmerBrush(
                targetValue = 1300f,
                showShimmer = isLoading.value
            ),
            shape = RoundedCornerShape(10)
        )
    else {
        Modifier
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(10.dp),
                ambientColor = Color(0x1AFFFFFF)
            )
            .background(
                shape = RoundedCornerShape(10),
                color = Color.White
            )
    }

    Column(
        modifier = modifier
            .width(136.dp)
            .height(109.dp),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = seller?.image,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.padding(20.dp)
        )
    }
}

@Composable
fun PopularItem(seller: Seller?, viewModel: MainViewModel = hiltViewModel()) {
    val isLoading = viewModel.isLoading.collectAsState()

    val modifier = if (isLoading.value) Modifier
        .background(
            shimmerBrush(
                targetValue = 1300f,
                showShimmer = isLoading.value
            ),
            shape = RoundedCornerShape(10)
        )
    else {
        Modifier
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(10.dp),
                ambientColor = Color(0x1AFFFFFF)
            )
            .background(
                shape = RoundedCornerShape(10),
                color = Color.White
            )
    }


    Box(
        modifier = modifier
            .width(188.dp)
            .height(162.dp),
        contentAlignment = BottomCenter
    ) {
        AsyncImage(
            model = seller?.image,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    color = Color(0x80484848),
                    shape = RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp)
                )
                .padding(all = 10.dp),
            verticalArrangement = Arrangement.Center
        ) {

            seller?.name?.let {
                Text(
                    text = it,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color(0xffFCEFCE),
                        lineHeight = 19.2.sp,
                        letterSpacing = (-0.3).sp,
                        fontWeight = FontWeight.Medium,
                    ),
                    modifier = Modifier
                        .align(Alignment.Start)
                )
            }

            seller?.distance?.let {
                val distanceInKilo =
                    runCatching { seller.distance.toInt().div(1000) }.getOrElse { 0 }

                Row(modifier = Modifier.padding(top = 3.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_location),
                        contentDescription = "Loc",
                        modifier = Modifier.size(8.dp)
                    )

                    Text(
                        text = " $distanceInKilo Km",
                        style = TextStyle(
                            fontSize = 7.sp,
                            color = Color(0xffFCEFCE),
                            lineHeight = 9.56.sp,
                            letterSpacing = (-0.3).sp,
                            fontWeight = FontWeight.Normal,
                        )
                    )
                }
            }

            seller?.rate?.let { rate ->
                Row(
                    modifier = Modifier.padding(top = 3.dp)
                ) {
                    RatingBar(
                        value = rate.toFloat(),
                        painterEmpty = painterResource(id = R.drawable.ic_star),
                        painterFilled = painterResource(id = R.drawable.ic_star_full),
                        onValueChange = {},
                        onRatingChanged = {},
                        spaceBetween = 2.dp,
                        modifier = Modifier.width(55.dp),
                        size = 7.dp,
                    )


                    Text(
                        text = " $rate",
                        style = TextStyle(
                            fontSize = 8.sp,
                            color = Color(0xffFCEFCE),
                            lineHeight = 9.56.sp,
                            letterSpacing = (-0.3).sp,
                            fontWeight = FontWeight.Bold,
                        )
                    )
                }
            }
        }
    }
}


@Composable
fun UnderlineText(modifier: Modifier, text: String) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = 11.sp,
            color = Color(0xffFFD279),
            lineHeight = 11.95.sp,
            letterSpacing = (-0.3).sp,
            fontWeight = FontWeight.Normal,
        ),
        modifier = modifier.drawBehind {
            val strokeWidthPx = 1.dp.toPx()
            val verticalOffset = size.height - 2.sp.toPx()
            drawLine(
                color = Color(0xffFFD279),
                strokeWidth = strokeWidthPx,
                start = Offset(0f, verticalOffset),
                end = Offset(size.width, verticalOffset)
            )
        }
    )
}

