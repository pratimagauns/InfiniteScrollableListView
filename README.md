# Infinitely Scrollable ListView


## Things achieved:
1) Including recycling capability of [RecyclerView](https://developer.android.com/reference/android/support/v7/widget/RecyclerView) in [ListView](https://developer.android.com/reference/android/widget/ListView).
2) Infinite scrolling capability in [ListView](https://developer.android.com/reference/android/widget/ListView).

### Recycling ListView

In [RecyclerView](https://developer.android.com/reference/android/support/v7/widget/RecyclerView), it is mandatory using the [RecyclerView.ViewHolder](https://developer.android.com/reference/android/support/v7/widget/RecyclerView.ViewHolder) class. Thus reusing the non visible row items to render new items to be shown. 

In order to achieve this in [ListView](https://developer.android.com/reference/android/widget/ListView), a custom ViewHolder is created and tagged with the ConvertView trying to render next. Doing this, the [AbsListView](https://developer.android.com/reference/android/widget/AbsListView) automatically reuses the last non visible item to render new ones.

### Infinite Scrolling
A common application feature is to load automatically more items as the user scrolls through the items. This is achieved by triggering a request for more data once the user crosses a threshold of remaining items before they've hit the end.

Created a custom scroll listener [InfiniteScrollListener](./src/main/java/com/pgauns/infinitescrollablelistview/ui/listeners/InfiniteScrollListener), that trigger loading new records when the remaining items goes below a threshold value.

##### P.S: No service data is used. A sample data is maintained in a local json file. This data is re-added to the data-source infinitely

#### Tried, no success 😞:
RecyclerView prepares view just ahead and behind the visible entries, which is great if you are fetching bitmaps in background.

![Cat](https://github.com/pratimagauns/InfiniteScrollableListView/blob/develop/sccroll_behaviour.png)

[ListView](https://developer.android.com/reference/android/widget/ListView) is based on the premise that there’s no way to precalculate or cache the size of entries in the list. While a RecyclerView prepares view just ahead and behind the visible entries.







