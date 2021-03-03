# **Example Horizontal list with expandable image view like Google Maps**

![imgTransition](https://media.giphy.com/media/5jatadcjYQYZ0Zk8UC/giphy.gif)

**Step 1 : configure RecyclerView to Bottom Sheet in layout** 

app/src/main/res/layout/bottomsheet_fragment.xml (line 2 and 27)

In Your layout Fragment You need to close RecyclerView in CoordinatorLayout and add one line to behave like Bottom Sheet:

```
app:layout_behavior="com.google.android.material.bottomsheet.BottomSheetBehavior"
```

**Step 2 : set destiantion height of expandable image in Adapter** 

app/src/main/java/com/example/bottomsheetexample/BottomSheetAdapter.kt (line 44)

Value of height is in dp

```
val imgHeight = 100
```

**Step 3 : set Bottom Sheet settings in Your Fragment** 

app/src/main/java/com/example/bottomsheetexample/BottomSheetFragment.kt

First find RecyclerView and tell him to behave like Bottom Sheet (line 51)

```
bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheetRecycler)
```

React on slide up and down and change offset of image (line 63 and 64)

!! In this point You can change height of image without notyfying RecyclerView and do this only with actual visible childs! That give You smooth animation 
 !!

```
override fun onSlide(bottomSheet: View, slideOffset: Float) {
    myAdapter!!.offset = slideOffset
    changeViewHolderItemsHeight()
    }
```

Observe RecyclerView scrolling and also change childs height to actual position (line 69-72)

```
override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
    super.onScrolled(recyclerView, dx, dy)
    changeViewHolderItemsHeight()
    }
```
