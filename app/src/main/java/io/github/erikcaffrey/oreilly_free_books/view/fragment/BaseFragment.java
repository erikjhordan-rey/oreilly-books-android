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

package io.github.erikcaffrey.oreilly_free_books.view.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.github.erikcaffrey.oreilly_free_books.view.presenter.Presenter;
import io.github.erikcaffrey.oreilly_free_books.view.activity.BaseActivity;

public abstract class BaseFragment extends Fragment implements Presenter.View {

  private Unbinder unbinder;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(getLayoutResID(), container, false);
    bindViews(view);
    onPrepareFragment(view);
    return view;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    onPreparePresenter();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    unbinder.unbind();
  }

  protected void onPrepareFragment(@NonNull View view) {

  }

  protected void onPreparePresenter() {

  }

  @LayoutRes protected abstract int getLayoutResID();

  @Nullable protected Toolbar getBaseToolbar() {
    return ((BaseActivity) getActivity()).getBaseToolbar();
  }

  protected void setSupportActionBarOnFragment(@NonNull Toolbar toolbar) {
    ((BaseActivity) getActivity()).setSupportActionBar(toolbar);
  }

  protected BaseActivity getBaseActivity() {
    return ((BaseActivity) getActivity());
  }

  protected ActionBar getSupportActionBar() {
    return ((BaseActivity) getActivity()).getSupportActionBar();
  }

  protected void finishActivity() {
    getActivity().finish();
  }

  protected FragmentManager getActivitySupportFragmentManager() {
    return getActivity().getSupportFragmentManager();
  }

  protected void onReplaceTransaction(@IdRes int containerViewId, @NonNull Fragment fragment) {
    getActivitySupportFragmentManager().beginTransaction()
        .replace(containerViewId, fragment)
        .commit();
  }

  protected void onReplaceTransactionWithBackStack(@IdRes int containerViewId,
      @NonNull Fragment fragment) {
    getActivitySupportFragmentManager().beginTransaction()
        .replace(containerViewId, fragment)
        .addToBackStack(Fragment.class.getCanonicalName())
        .commit();
  }

  private void bindViews(View view) {
    unbinder = ButterKnife.bind(this, view);
  }
}