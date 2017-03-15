package io.github.erikcaffrey.oreilly_free_books.view.navigator.menu;

class MenuTarget {

  static class BooksMenuNavigator implements MenuNavigatorTarget<MenuNavigator> {
    @Override public void onNavigate(MenuNavigator navigator) {
      navigator.toBooks();
    }
  }

  static class DownloadsMenuNavigator implements MenuNavigatorTarget<MenuNavigator> {
    @Override public void onNavigate(MenuNavigator navigator) {
      navigator.toDownloads();
    }
  }
}
