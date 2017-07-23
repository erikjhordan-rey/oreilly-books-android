/*
 * Copyright (C) 2017 Erik Jhordan Rey.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.erikcaffrey.oreilly_free_books

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.runner.Description
import org.junit.runners.model.Statement

open class RxAndroidRule : RxJavaRule() {
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
