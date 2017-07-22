package oreilly_free_books

import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

open class RxJavaTestRule: TestRule {

  override fun apply(base: Statement, description: Description): Statement {
    return object : Statement() {
      @Throws(Throwable::class)
      override fun evaluate() {
        hookSchedulers()
        base.evaluate()
        restoreSchedulers()
      }
    }
  }

  protected fun hookSchedulers() {
    RxJavaPlugins.reset()
    RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
    RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
    RxJavaPlugins.setSingleSchedulerHandler { Schedulers.trampoline() }
  }

  protected fun restoreSchedulers() {
    RxJavaPlugins.reset()
  }
}
