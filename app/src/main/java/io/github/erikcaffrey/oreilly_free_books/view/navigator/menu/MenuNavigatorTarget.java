package io.github.erikcaffrey.oreilly_free_books.view.navigator.menu;

import io.github.erikcaffrey.oreilly_free_books.view.navigator.Navigation;

public interface MenuNavigatorTarget<N extends Navigation> {
  void onNavigate(N navigator);
}
