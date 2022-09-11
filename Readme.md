# Wanted_PreOnBoarding_Anroid


# Structure

## Clean Architecture + Mvvm
<img src="https://user-images.githubusercontent.com/62296097/189513829-ff836084-b0fc-4c15-a22b-79f8665a828b.png">
<p>
프로젝트의 전체적인 구조는 위와 같습니다
</p>

## Single Activity Multiple Fragment & Navigation Component
Jetpack Navigation과 함께 Single Activity Mutliple Fragment 사용하여 Fragment끼리의 데이터 공유에 이점을 가지는 구조로 구현하였습니다.


### Navigation Graph
<p>Navigation 2.4.0-alpha01부터 지원하는 Multiple Back Stacks 지원을 통해서 
TopNewsScreen, CategoriesScreen, SavedScreen으로 나눠 각 탭에 대한 자체 백 스택을 별도로 유지하고, 다른 탭으로 전환 시 백 스택을 재설정하는 방식으로 구현하였습니다.
</p>
<p>

```xml
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/nav_graph"
    app:startDestination="@+id/top_news">

    <include app:graph = "@navigation/top_news"/>
    <include app:graph = "@navigation/categories"/>
    <include app:graph = "@navigation/saved"/>

</navigation>
```


***
## TopNewsScreen
<!-- <img src="https://avatars.githubusercontent.com/014967" width="100"> -->
<img src= "https://user-images.githubusercontent.com/62296097/189511506-5a893708-1afe-4473-b2a7-49d9f98f6c68.png">

<p>
TopNews Fragment에서는 TopNewsApi를 이용하여 최신 뉴스를 불러와 Recyclerview에 띄워주게 됩니다.
</p>
<p>
TopNews Adapter에서는 ListAdapter를 사용했으며, topNewsClick 람다를 통해서, topNewsItem 클릭시
NewsDetailFragment로 topNews 객체와 함께 이동하게 됩니다.
</p>

***

## CategoriesScreen
<img src="https://user-images.githubusercontent.com/62296097/189511499-fd893e6e-9006-4947-a71f-bd15c78d3d88.png">

<p>
categories navigation은 CategoriesFragment, CategoriesNewsFragment, NewsDetailFragment로 구성됩니다.
</p>
<p>
CategoriesFragment에서는 각 카테고리의 아이템들이 표시됩니다. 각 아이템들을 클릭시 CategiresNewsFragment로 category String type을 navigation argument로 넘겨주면서 이동하게 됩니다. 
</p>
<p>
넘겨준 category 문자열은 appBar의 label로 보여지게 됩니다.
</p>
<p>
CategoriesNewsFragment에서는 카테고리에 맞춰서 topNews들을 불러오게 됩니다. 불러온 topNews들은 TopNewsAdapter를 사용하여 Recyclerview에 보여지게 됩니다.
</p>

***

## SavedScreen
<img src="https://user-images.githubusercontent.com/62296097/189511501-b637a867-b67e-4e64-a518-f7f2d2876f99.png">
<p>
saved navigation은 SavedFragment와 NewsDetailFragment로 이루어집니다.
</p>
<p>
SavedFragment는 Room Database에 저장된 topNews들을 불러옵니다.
</p>


</br>

# TopNews Logic

## TopNewsApi
```kotlin
interface TopNewsApi {
    @GET("/v2/top-headlines")
    suspend fun getTopNews(
        @Query("country") country: String,
        @Query("category") category: String
    ): Response<RemoteTopNews>
}
```
<p>
retrofit 라이브러리를 사용하여 TopNewsApi를 작성하였습니다. 뉴스를 불러올때 country와 category를 parameter로 넘겨줍니다.
</p>

## TopNewsRepository
```kotlin
class TopNewsRepositoryImpl @Inject constructor(
    private val topNewsRemoteDataSource: TopNewsRemoteDataSource
) : TopNewsRepository {
    override fun getTopNews(category: String): Flow<Results<List<TopNews>>> {
        return flow {
            val response = topNewsRemoteDataSource.getTopNews(country = "us", category = category)
            val body = response.body()
            if (response.isSuccessful && body != null) {
                if (body.status == "ok") {
                    val data = mappingTopNewsResponseToDomainModel(body)
                    emit(Results.Success(data))
                }
            }
        }
    }
}

```
<p>
TopNewsRepositoryImpl class는 TopNewsRepository의 구현체입니. topNewApi의 결과를 받아오고, response body에 status가 ok 문자열이라면, response body를 domain Layer의 topNews Model로 변환하며 값을 방출합니다.
</p>

## GetTopNewsUseCase
```kotlin
class GetTopNewsUseCase @Inject constructor(
    private val topNewsRepository: TopNewsRepository
) {
    operator fun invoke(category: String): Flow<Results<List<TopNews>>> {
        return topNewsRepository.getTopNews(category = category)
    }
}

```
GetTopNewsUseCase를 만들어서, topNewsFragment & categoryNewsFragment 에서 뉴스 api를 호출할 때 재사용할 수 있도록 하였습니다.

## Call GetTopNewsUseCase in ViewModel
```kotlin
private val _topNews: MutableStateFlow<TopNewsUiStates> = MutableStateFlow(TopNewsUiStates.Loading)
    val topNews = _topNews.asStateFlow()

    fun getTopNews(category: String) {
        viewModelScope.launch {
            getTopNewsUseCase(category = category).collect { result ->
                when (result) {
                    is Results.Success -> {
                        _topNews.value =
                            TopNewsUiStates.Success(mappingDomainModelToPresentationModel(result.value))
                    }
                    is Results.Loading -> {
                    }
                    is Results.Failure -> {
                    }
                }
            }
        }
    }
```
데이터 홀더클래스로 flow를 사용하였으며 usecase를 호출하여 api의 결과가 success일 경우, PresentationModel로 맵핑해주며 저장하였습니다.

## observe TopNews in View
```kotlin
 private fun observeTopNews() = with(viewLifecycleOwner.lifecycleScope) {
        launch {
            repeatOnLifecycle(state = Lifecycle.State.RESUMED) {
                topNewsViewModel.topNews.collect { state ->
                    when (state) {
                        is TopNewsUiStates.Loading -> {
                        }
                        is TopNewsUiStates.Success -> {
                            topNewsAdapter.submitList(state.news)
                        }
                    }
                }
            }
        }
    }

```
<p> 
View에서는 viewModel의 topNews를 관찰하고 있고, 변경사항이 있다면 ListAdapter로 리스트를 넘겨주어, api의 결과를 화면에 보여주게 됩니다.
</p>

</br>

# TopNewsDatabase Transaction Logic

```kotlin
class ArticleLocalDataSource @Inject constructor(
    private val articleDao: ArticleDao
) {
    suspend fun savedArticle(articleEntity: ArticleEntity): Long {
        return articleDao.insert(
            article = articleEntity
        )
    }

    suspend fun findArticleWithTitle(articleTitle: String): ArticleEntity {
        return articleDao.searchSavedArticle(query = articleTitle)
    }

    suspend fun deleteArticle(articleTitle: String): Int {
        return articleDao.delete(title = articleTitle)
    }

    suspend fun getAllSavedArticles(): List<ArticleEntity> {
        return articleDao.getAllSavedArticles()
    }
}

```
ArticleLocalDataSource클래스에서 topNews의 저장 & 검색 & 삭제 로직을 구현하였습니다.
<p>
서버에서 불러온 뉴스를 star icon 클릭시 savedArticle function을 호출하며 Room데이터 베이스에 저장합니다.
</p>
<p>
Article Entity의 Primary키는 title로 지정하며, NewsDetailFragment 진입시에 해당하는 뉴스가 데이터베이스에 저장이 되어있는지 NewsTitle로 검색을 하여 있다면, star icon을 star_filled icon으로 교체해주었습니다. 
</p>
<p>
SavedFragment에서는 getAllSavedArticles function을 호출하여 Room Database 내부에 저장된 모든 뉴스들을 검색하여 반환합니다.
</p>

</br>

# Version Catalog

Gradle Directory 내부에서 Version Catalog를 생성하여 Library 나 Plugin들을 관리합니다.

### Android Jetpack
|                  Library             |          Description   |
| ----------------------------------- | ------------------------------------------- |
| [Navigation](https://developer.android.com/guide/navigation/navigation-getting-started?hl=ko)   |  Fragment간 화면 전환 및 뷰 스택 관리. Single Activity 구조 적용  |
| [KTX](https://developer.android.com/kotlin/ktx)  | Android 생명주기 관리, 확장함수, Coroutine의 이용을 위한 Kotlin 확장 프로그램   |
| [Hilt, Dagger](https://developer.android.com/training/dependency-injection/hilt-android?hl=ko) | 클래스에 컨테이너를 제공하고 수명 주기를 자동으로 관리하는 Android DI 라이브러리 |
| [Coroutine](https://developer.android.com/kotlin/coroutines?hl=ko) | Android Jetpack과 호환되는 안드로이드 비동기 프로그래밍 솔루션 |
| Coroutine - [Flow](https://developer.android.com/kotlin/flow?hl=ko) | Coroutine 기반의 비동기식 데이터 스트림 |
| Coroutine - [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow?hl=ko) | Flow에서 최적으로 상태 업데이트를 내보내는 관찰 가능한 객체 |
| [Room](https://developer.android.com/training/data-storage/room?hl=ko) | Jetpack에서 권장하는 Android 내부 저장소 관련 솔루션. SQLite를 추상화한 라이브러리 |

### ETC
|                  Library             |          Description   |
| ----------------------------------- | ------------------------------------------- |
| [Retrofit2](https://square.github.io/retrofit/)  | HTTP REST API 통신 라이브러리  |
| [OkHttp3](https://square.github.io/okhttp/)  | HTTP 기반으로  효율적으로 request/response를 할 수 있도록 지원하는 HTTP 클라이언트
| [Glide](https://github.com/bumptech/glide) | 이미지 로드 및 캐싱 라이브러리 |
| [Timber](https://github.com/JakeWharton/timber)     |  로그를 출력하는 라이브러리   |
