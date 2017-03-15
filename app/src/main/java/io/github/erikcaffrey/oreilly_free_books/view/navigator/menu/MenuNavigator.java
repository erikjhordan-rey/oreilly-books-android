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

package io.github.erikcaffrey.oreilly_free_books.view.navigator.menu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import io.github.erikcaffrey.oreilly_free_books.R;
import io.github.erikcaffrey.oreilly_free_books.di.scopes.ActivityContext;
import io.github.erikcaffrey.oreilly_free_books.view.fragment.BooksFragment;
import io.github.erikcaffrey.oreilly_free_books.view.fragment.DownloadsFragment;
import io.github.erikcaffrey.oreilly_free_books.view.navigator.Navigation;
import javax.inject.Inject;

public class MenuNavigator implements Navigation {

  public static final int BOOKS_MENU = 0;
  private final Context activityContext;

  @Inject MenuNavigator(@ActivityContext Context activityContext) {
    this.activityContext = activityContext;
  }

  void toBooks() {
    replaceFragment(BooksFragment.newInstance());
  }

  void toDownloads() {
    replaceFragment(DownloadsFragment.newInstance());
  }

  public MenuNavigatorTarget create(MenuItem item) {

    MenuNavigatorTarget menuNavigator;

    switch (item.getItemId()) {
      case R.id.nav_books:
        menuNavigator = new MenuTarget.BooksMenuNavigator();
        break;

      case R.id.nav_downloads:
        menuNavigator = new MenuTarget.DownloadsMenuNavigator();
        break;

      default:
        menuNavigator = new MenuTarget.BooksMenuNavigator();
        break;
    }

    return menuNavigator;
  }

  private void replaceFragment(@NonNull Fragment fragment) {
    getFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
  }

  private FragmentManager getFragmentManager() {
    return ((AppCompatActivity) activityContext).getSupportFragmentManager();
  }
}
