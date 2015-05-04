QuickSand
=========

When showing a really enchanting explanatory animation to your users, but you know that after a while it'll get tedious and would stop users wanting to use your app. QuickSand is here to solve that problem.

Automatically manipulates the duration of animations depending on how many times the user has viewed them.

Simple Use
----------

This is the setup needed to define how you change the animation duration between views:

```java
Map<String, Viscosity> modifiers = new HashMap<>();
modifiers.put("MyAnimationSetKey", TwoStepViscosity.defaultInstance()); 
Quicksand.create(this, modifiers);
```

This is "trapping" an animation, meaning when this view is animated `Quicksand` will determine how many times it has been viewed and vary the animation duration according to the `Viscosity` set above: 

```java 
View myView = findViewById(R.id.my_view);
QuickSand.trap("MyAnimationSetKey", myView);
myView.animate().alpha();
```

Also works for multiple view animation:

```java
 View myView = findViewById(R.id.my_view);
 View myOtherView = findViewById(R.id.my_view);
 View myThirdView = findViewById(R.id.my_view);
 QuickSand.trap("MyAnimationSetKey", myView, myOtherView, myThirdView);
 myView.animate().alpha();
 myOtherView.animate().alpha();
 myThirdView.animate().alpha();
```

Some viscosities already defined [see the code here](https://github.com/blundell/QuickSand/tree/master/core/src/main/java/com/blundell/quicksand/viscosity):

- [AllOrNothing](https://github.com/blundell/QuickSand/blob/master/core/src/main/java/com/blundell/quicksand/viscosity/AllOrNothingViscosity.java) - you can run your animation for X seconds X times and then 0 seconds after that
- [LinearChange](https://github.com/blundell/QuickSand/blob/master/core/src/main/java/com/blundell/quicksand/viscosity/LinearChangeViscosity.java) - speed up the animation each time it is viewed until X views
- [TwoStep](https://github.com/blundell/QuickSand/blob/master/core/src/main/java/com/blundell/quicksand/viscosity/TwoStepViscosity.java) - run animation at X speed for X times then it will run at half X

See the [demo project](https://github.com/blundell/QuickSand/tree/master/demo) for more examples.

Adding to your project
--------

```groovy
dependencies {
    compile 'com.blundell:quicksand:1.0.1'
}
```


--------

See it to believe it
--------

|15 second gif below|  
|---|
Notice how the animation duration decreases each time it is ran until eventually it does not animate (only touch feedback), `Quicksand` integration is done with 2 lines of code.|
|![demo](demo/demo_z1c.gif)|

Download
--------

[Release Notes](https://github.com/blundell/QuickSand/blob/master/releases/RELEASE-NOTES.MD)
Get [1.0.1 here](https://github.com/blundell/QuickSand/raw/master/releases/quicksand-1.0.1.aar)

License
-------

[Apache 2](LICENSE.txt)

