package io.github.erikcaffrey.oreilly_free_books.domain

import io.reactivex.Single
import io.github.erikcaffrey.oreilly_free_books.data.BookEntity
import io.github.erikcaffrey.oreilly_free_books.data.api.ApiBooksDataSource
import io.github.erikcaffrey.oreilly_free_books.data.api.BooksService
import io.github.erikcaffrey.oreilly_free_books.domain.repository.BooksRepository
import io.github.erikcaffrey.oreilly_free_books.RxAndroidRule
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit


class BooksRepositoryTest {

  companion object {

    private val ANY_TITLE = "_title"
    private val ANY_DESCRIPTION = "_description"
    private val ANY_IMAGE = "_image"
    private val ANY_URL = "_url"
    private val ANY_CATEGORY = "_category"
    private val ANY_SUBCATEGORY = "_subCategory"


    @ClassRule @JvmField
    val blockingRxAndroidTestRule = RxAndroidRule()
  }

  @Rule @JvmField
  var mockitoRule = MockitoJUnit.rule()!!

  @Mock lateinit var dataSource: ApiBooksDataSource

  private lateinit var repository: BooksRepository

  @Before
  fun setUp() {

    dataSource = ApiBooksDataSource(MocketRetrofitService())
    repository = BooksRepository(dataSource)
  }

  @Test
  fun shouldReturnABookEntityList() {

    val bookEntityList = givenABookEntityListWithData()

    repository.getBooks()
        .test()
        .assertNoErrors()
        .assertValue {
          it.size == bookEntityList.size && it[0].title == bookEntityList[0].title
        }
  }

  private fun givenABookEntityListWithData(): List<BookEntity> {
    val bookList = ArrayList<BookEntity>()
    (1..10).mapTo(bookList) {
      BookEntity(ANY_TITLE,
          ANY_DESCRIPTION, ANY_IMAGE,
          ANY_URL, ANY_CATEGORY,
          ANY_SUBCATEGORY, "", "", "")
    }
    given(dataSource.getBooks()).willReturn(Single.just(bookList))
    return bookList
  }


  open class MocketRetrofitService : BooksService {
    override fun fetchBooks(): Single<List<BookEntity>> {
      return Single.just(emptyList())
    }


  }

}