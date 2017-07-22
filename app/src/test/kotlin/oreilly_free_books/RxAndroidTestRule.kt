package oreilly_free_books

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.runner.Description
import org.junit.runners.model.Statement

open class RxAndroidTestRule : RxJavaTestRule() {
  override fun apply(base: Statement, description: Description): Statement {
    return object : Statement() {
      @Throws(Throwable::class)
      override fun evaluate() {
        hookSchedulers()
        hookAndroidSchedulers()
        base.evaluate()
        restoreAndroidSchedulers()
        restoreSchedulers()
      }
    }
  }

  private fun hookAndroidSchedulers() {
    RxAndroidPlugins.reset()
    RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.from { it.run() } }
    RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
  }

  private fun restoreAndroidSchedulers() {
    RxAndroidPlugins.reset()
  }
}
