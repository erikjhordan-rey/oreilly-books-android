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

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import butterknife.BindView;
import io.github.erikcaffrey.oreilly_free_books.R;
import io.github.erikcaffrey.oreilly_free_books.di.components.ActivityComponent;
import io.github.erikcaffrey.oreilly_free_books.di.components.DaggerActivityComponent;
import io.github.erikcaffrey.oreilly_free_books.view.navigator.menu.MenuNavigator;
import io.github.erikcaffrey.oreilly_free_books.view.navigator.menu.MenuNavigatorTarget;
import javax.inject.Inject;

public class OreillyActivity extends BaseActivity
    implements NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener {

  @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
  @BindView(R.id.navigation_view) NavigationView navigationView;

  @Inject MenuNavigator menuNavigator;

  private MenuNavigatorTarget menuNavigatorTarget;

  @Override protected int getLayoutResID() {
    return R.layout.oreilly_activity;
  }

  @Override protected void onPrepareActivity() {
    super.onPrepareActivity();
    initializeDagger();
    initializeNavDrawer();
  }

  @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    menuNavigatorTarget = menuNavigator.create(item);
    item.setChecked(true);
    drawerLayout.closeDrawers();
    return true;
  }

  @Override public void onDrawerSlide(View drawerView, float slideOffset) {
    /* Google violating Interface Segregation Principle (http://www.oodesign.com/interface-segregation-principle.html)
    DrawerListener class is forcing to implement all methods about monitoring drawer events */
  }

  @Override public void onDrawerOpened(View drawerView) {
   /* Google violating Interface Segregation Principle (http://www.oodesign.com/interface-segregation-principle.html)
    DrawerListener class is forcing to implement all methods about monitoring drawer events */
  }

  @SuppressWarnings("unchecked") @Override public void onDrawerClosed(View drawerView) {
    navigate();
  }

  @Override public void onDrawerStateChanged(int newState) {
   /* Google violating Interface Segregation Principle (http://www.oodesign.com/interface-segregation-principle.html)
    DrawerListener class is forcing to implement all methods about monitoring drawer events */
  }

  private void initializeDagger() {
    getActivityComponent().inject(this);
  }

  private ActivityComponent getActivityComponent() {
    return DaggerActivityComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
  }

  private void initializeNavDrawer() {
    ActionBarDrawerToggle toggle = getDrawerToggle();
    drawerLayout.addDrawerListener(toggle);
    drawerLayout.addDrawerListener(this);
    toggle.syncState();
    initializeNavigationView();
  }

  private void initializeNavigationView() {
    navigationView.setNavigationItemSelectedListener(this);
    navigationView.getMenu().getItem(MenuNavigator.BOOKS_MENU).setChecked(true);
    navigationView.getMenu().performIdentifierAction(R.id.nav_books, MenuNavigator.BOOKS_MENU);
    navigate();
  }

  private ActionBarDrawerToggle getDrawerToggle() {
    return new ActionBarDrawerToggle(this, drawerLayout, getBaseToolbar(),
        R.string.navigation_drawer_open, R.string.navigation_drawer_close);
  }

  @SuppressWarnings("unchecked") private void navigate() {
    if (menuNavigatorTarget != null) {
      menuNavigatorTarget.onNavigate(menuNavigator);
      menuNavigatorTarget = null;
    }
  }
}
