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

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import butterknife.ButterKnife;
import io.github.erikcaffrey.oreilly_free_books.OreillyBooksApplication;
import io.github.erikcaffrey.oreilly_free_books.R;
import io.github.erikcaffrey.oreilly_free_books.di.components.ApplicationComponent;
import io.github.erikcaffrey.oreilly_free_books.di.modules.ActivityModule;
import io.github.erikcaffrey.oreilly_free_books.view.presenter.Presenter;

public abstract class BaseActivity extends AppCompatActivity implements Presenter.View {

  private Toolbar toolbar;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutResID());
    getApplicationComponent().inject(this);
    onPrepareSupportActionBar();
    bindViews();
    onPreparePresenter();
    onPrepareActivity();
  }

  private void onPrepareSupportActionBar() {
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    if (toolbar != null) {
      setSupportActionBar(toolbar);
      ActionBar actionBar = getSupportActionBar();
      if (actionBar != null) {
        onSetupSupportActionBar(actionBar);
      }
    }
  }

  protected void onSetupSupportActionBar(@NonNull ActionBar actionBar) {

  }

  protected void onPrepareActivity() {

  }

  protected void onPreparePresenter() {

  }

  private void bindViews() {
    ButterKnife.bind(this);
  }

  @Nullable public Toolbar getBaseToolbar() {
    return toolbar;
  }

  @LayoutRes protected abstract int getLayoutResID();

  protected ApplicationComponent getApplicationComponent() {
    return ((OreillyBooksApplication) getApplication()).getApplicationComponent();
  }

  protected ActivityModule getActivityModule() {
    return new ActivityModule(this);
  }
}