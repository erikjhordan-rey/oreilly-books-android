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

package io.github.erikcaffrey.oreilly_free_books.view.activity;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public abstract class BaseFragmentActivity extends BaseActivity {

  private Fragment fragment;

  @Override protected void onPrepareActivity() {
    super.onPrepareActivity();
    onPrepareFragment();
  }

  private void onPrepareFragment() {
    final FragmentManager fragmentManager = getSupportFragmentManager();
    final int containerViewId = getContainerViewId();
    fragment = fragmentManager.findFragmentById(containerViewId);
    if (fragment == null) {
      fragment = getFragmentInstance();
      fragmentManager.beginTransaction().add(containerViewId, fragment).commit();
    }
  }

  @IdRes protected abstract int getContainerViewId();

  protected abstract Fragment getFragmentInstance();

  public Fragment getTargetNestedFragment() {
    return fragment;
  }
}